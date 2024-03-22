package cn.lmx.kpu.oauth.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.jackson.JsonUtil;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.basic.utils.TreeUtil;
import cn.lmx.kpu.base.service.system.BaseRoleService;
import cn.lmx.kpu.base.vo.result.user.RouterMeta;
import cn.lmx.kpu.base.vo.result.user.VueRouter;
import cn.lmx.kpu.common.constant.BizConstant;
import cn.lmx.kpu.common.constant.RoleConstant;
import cn.lmx.kpu.model.enumeration.HttpMethod;
import cn.lmx.kpu.model.enumeration.system.ResourceTypeEnum;
import cn.lmx.kpu.system.entity.application.DefApplication;
import cn.lmx.kpu.system.entity.application.DefResource;
import cn.lmx.kpu.system.entity.application.DefResourceApi;
import cn.lmx.kpu.system.enumeration.tenant.ResourceOpenWithEnum;
import cn.lmx.kpu.system.service.application.DefApplicationService;
import cn.lmx.kpu.system.service.application.DefResourceService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 资源大业务
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ResourceBiz {
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    private final DefResourceService defResourceService;
    private final DefApplicationService defApplicationService;
    private final BaseRoleService baseRoleService;

    /**
     * 是否所有的子都是视图
     *
     * 若某个菜单的子集，全部都是视图，这需要标记为隐藏子集
     *
     */
    private static boolean hideChildrenInMenu(List<VueRouter> children) {
        if (CollUtil.isEmpty(children)) {
            return false;
        }

        // hidden： true - 视图   false - 菜单  null - 菜单

        return children.stream().allMatch(item -> item.getIsHidden() != null && item.getIsHidden());
    }

    /**
     * 查询当前用户可用的资源
     *
     * @param applicationId 应用id
     * @return 资源树
     */
    public List<String> findVisibleResource(Long employeeId, Long applicationId) {
        List<DefResource> list;
        boolean isAdmin = baseRoleService.checkRole(employeeId, RoleConstant.TENANT_ADMIN);
        List<String> resourceCodes = Collections.emptyList();
        if (isAdmin) {
            // 管理员 拥有所有权限，查询指定应用，指定类型的 所有资源
            list = defResourceService.findResourceListByApplicationId(applicationId != null ? Collections.singletonList(applicationId) : Collections.emptyList(), resourceCodes);
        } else {
            List<Long> resourceIdList = baseRoleService.findResourceIdByEmployeeId(applicationId, employeeId);
            if (resourceIdList.isEmpty()) {
                return Collections.emptyList();
            }

            list = defResourceService.findByIdsAndType(resourceIdList, resourceCodes);
        }
        return CollHelper.split(list, DefResource::getCode, StrPool.SEMICOLON);
    }

    public List<VueRouter> findAllVisibleRouter(Long employeeId, String subGroup) {
        List<DefResource> list;
        boolean isAdmin = baseRoleService.checkRole(employeeId, RoleConstant.TENANT_ADMIN);
        List<String> menuCodes = Collections.singletonList(ResourceTypeEnum.MENU.getCode());

        List<DefApplication> applicationList = defApplicationService.list(Wraps.<DefApplication>lbQ().orderByAsc(DefApplication::getSortValue));
        List<VueRouter> treeList = new ArrayList<>();
        for (DefApplication defApplication : applicationList) {
            if (isAdmin) {
                // 管理员 拥有所有权限，查询指定应用，指定类型的 所有路由
                list = defResourceService.findResourceListByApplicationId(Collections.singletonList(defApplication.getId()), menuCodes);
            } else {
                List<Long> resourceIdList = baseRoleService.findResourceIdByEmployeeId(defApplication.getId(), employeeId);
                if (resourceIdList.isEmpty()) {
                    continue;
                }

                list = defResourceService.findByIdsAndType(resourceIdList, menuCodes);
            }

            if (StrUtil.isNotEmpty(subGroup)) {
                list = list.stream().filter(item -> subGroup.equals(item.getSubGroup())).toList();
            }

            if (list.isEmpty()) {
                continue;
            }

            List<VueRouter> routers = BeanPlusUtil.copyToList(list, VueRouter.class);
            List<VueRouter> tree = TreeUtil.buildTree(routers);
            forEachTree(tree, 1);

            VueRouter applicationRouter = new VueRouter();
            applicationRouter.setName(defApplication.getName());
            applicationRouter.setPath("/" + defApplication.getId());
            applicationRouter.setComponent(BizConstant.LAYOUT);
            VueRouter childrenFirst = getChildrenFirst(tree);
            applicationRouter.setRedirect(StrUtil.isNotEmpty(defApplication.getRedirect()) ? defApplication.getRedirect() : (childrenFirst != null ? childrenFirst.getPath() : null));

            RouterMeta meta = new RouterMeta();
            meta.setTitle(defApplication.getName());
            meta.setSidebar(false);
            // 是否所有的子都是视图
            meta.setHideChildrenInMenu(false);

            applicationRouter.setMeta(meta);
            applicationRouter.setResourceType(ResourceTypeEnum.MENU.getCode());
            applicationRouter.setOpenWith(ResourceOpenWithEnum.INNER_COMPONENT.getCode());

            applicationRouter.setChildren(tree);
            treeList.add(applicationRouter);
        }

        return treeList;
    }

    private VueRouter getChildrenFirst(List<VueRouter> list) {
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        VueRouter vueRouter = list.get(0);
        if (CollUtil.isEmpty(vueRouter.getChildren())) {
            return vueRouter;
        }
        return getChildrenFirst(vueRouter.getChildren());
    }

    /**
     * 查询当前用户可用的 菜单 + 视图
     * <p>
     * 1. 租户管理员拥有指定应用的所有 资源
     * 2. 还没成为员工的用户，没有任何 资源
     *
     * @param applicationId 应用id
     * @return 资源树
     */
    public List<VueRouter> findVisibleRouter(Long applicationId, Long employeeId, String subGroup) {
        List<DefResource> list;
        boolean isAdmin = baseRoleService.checkRole(employeeId, RoleConstant.TENANT_ADMIN);
        List<String> menuCodes = Collections.singletonList(ResourceTypeEnum.MENU.getCode());
        if (isAdmin) {
            // 管理员 拥有所有权限，查询指定应用，指定类型的 所有路由
            list = defResourceService.findResourceListByApplicationId(applicationId != null ? Collections.singletonList(applicationId) : Collections.emptyList(), menuCodes);
        } else {
            List<Long> resourceIdList = baseRoleService.findResourceIdByEmployeeId(applicationId, employeeId);
            if (resourceIdList.isEmpty()) {
                return Collections.emptyList();
            }

            list = defResourceService.findByIdsAndType(resourceIdList, menuCodes);
        }

        if (StrUtil.isNotEmpty(subGroup)) {
            list = list.stream().filter(item -> subGroup.equals(item.getSubGroup())).toList();
        }

        List<VueRouter> routers = BeanPlusUtil.copyToList(list, VueRouter.class);
        List<VueRouter> tree = TreeUtil.buildTree(routers);
        forEachTree(tree, 1);
        return tree;
    }

    /**
     * 检查指定接口是否有访问权限
     *
     * @param path   请求路径
     * @param method 请求方法
     * @return 是否有权限
     */
    public Boolean checkUri(String path, String method) {
        Long employeeId = ContextUtil.getEmployeeId();
        Long applicationId = ContextUtil.getApplicationId();
        log.info("path={}, method={}, employeeId={}, applicationId={}", path, method, employeeId, applicationId);
        if (StrUtil.isEmpty(path) || StrUtil.isEmpty(method)) {
            return false;
        }
        boolean isAdmin = baseRoleService.checkRole(employeeId, RoleConstant.TENANT_ADMIN);
        List<DefResourceApi> apiList;
        if (isAdmin) {
            // 方案2 查db
            long queryResStart = System.currentTimeMillis();
            apiList = defResourceService.findResourceApi(applicationId != null ? Collections.singletonList(applicationId) : Collections.emptyList(), Collections.emptyList());
            long queryResEnd = System.currentTimeMillis();
            log.info("biz query 校验api权限:{} - {}  耗时:{}", path, method, (queryResEnd - queryResStart));
        } else {
            // 普通用户 需要校验 uri + method 的权限
            long queryResStart = System.currentTimeMillis();
            List<Long> resourceIdList = baseRoleService.findResourceIdByEmployeeId(applicationId, employeeId);
            if (resourceIdList.isEmpty()) {
                return false;
            }
            long queryResEnd = System.currentTimeMillis();
            apiList = defResourceService.findApiByResourceId(resourceIdList);
            long queryApiEnd = System.currentTimeMillis();
            log.info("biz query 校验api权限:{} - {}  耗时:{}, {}", path, method, (queryResEnd - queryResStart), (queryApiEnd - queryResEnd));
        }

        if (apiList.isEmpty()) {
            return false;
        }

        long apiStart = System.currentTimeMillis();
        boolean flag = apiList.parallelStream().distinct().anyMatch(item -> {
            String uri = item.getUri();
            /*
             * 若您确定只使用kpu-boot，而非kpu-cloud，请将def_resource_api表中uri的代理的前缀(/base、/system、/oauth)去除，即可 删除删除删除 if里面的代码！
             * 因为脚本数据是基于kpu-cloud配置的，所以uri地址会多一段gateway代理前缀。如
             * kpu-cloud 中地址为：/base/baseEmployee/page
             * 对应kpu-boot的地址为：/baseEmployee/page
             * 其中/base是因为使用了gateway增加的！
             */
            if (!StrUtil.startWithAny(uri, "/gateway")) {
                uri = StrUtil.subSuf(uri, StrUtil.indexOf(uri, '/', 1));
            }

            if (StrUtil.equalsIgnoreCase(uri, path)) {
                if (StrUtil.equalsIgnoreCase(method, item.getRequestMethod()) || HttpMethod.ALL.name().equalsIgnoreCase(item.getRequestMethod())) {
                    return true;
                }
            }

            boolean matchUri = PATH_MATCHER.match(StrUtil.trim(uri), StrUtil.trim(path));

            log.debug("path={}, uri={}, matchUri={}, method={} apiId={}", path, uri, matchUri, item.getRequestMethod(), item.getId());
            if (HttpMethod.ALL.name().equalsIgnoreCase(item.getRequestMethod())) {
                return matchUri;
            }
            return matchUri && StrUtil.equalsIgnoreCase(method, item.getRequestMethod());
        });
        long apiEnd = System.currentTimeMillis();
        log.info("biz list 校验api权限:{} - {}  耗时:{}", path, method, (apiEnd - apiStart));
        return flag;
    }

    private void forEachTree(List<VueRouter> tree, int level) {
        if (CollUtil.isEmpty(tree)) {
            return;
        }
        for (VueRouter item : tree) {
            log.debug("level={}, label={}", level, item.getName());
            RouterMeta meta = null;
            if (StrUtil.isNotEmpty(item.getMetaJson()) && !StrPool.BRACE.equals(item.getMetaJson())) {
                meta = JsonUtil.parse(item.getMetaJson(), RouterMeta.class);
            }
            if (meta == null) {
                meta = new RouterMeta();
            }
            if (StrUtil.isEmpty(meta.getTitle())) {
                meta.setTitle(item.getName());
            }
            meta.setIcon(item.getIcon());

            // 视图需要隐藏
            meta.setSidebar(item.getIsHidden() != null ? item.getIsHidden() : false);

            // 是否所有的子都是视图
            meta.setHideChildrenInMenu(hideChildrenInMenu(item.getChildren()));
            item.setMeta(meta);

            // 若当前菜单的 子菜单至少有一个菜单，将它设置为 LAYOUT
            if (CollUtil.isNotEmpty(item.getChildren()) && !hideChildrenInMenu(item.getChildren())) {
                item.setComponent(BizConstant.LAYOUT);
            }

            if (CollUtil.isNotEmpty(item.getChildren())) {
                forEachTree(item.getChildren(), level + 1);
            }
        }
    }

    /**
     * 检测员工是否拥有指定应用的权限
     *
     * @param applicationId 应用id
     * @param employeeId    员工id
     * @return 是否拥有指定应用的权限
     */
    public Boolean checkEmployeeHaveApplication(Long employeeId, Long applicationId) {
        boolean isAdmin = baseRoleService.checkRole(employeeId, RoleConstant.TENANT_ADMIN);
        if (isAdmin) {
            return true;
        }
        return CollUtil.isNotEmpty(baseRoleService.findResourceIdByEmployeeId(applicationId, employeeId));
    }

}
