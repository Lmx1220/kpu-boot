package cn.lmx.kpu.oauth.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.lmx.basic.annotation.user.LoginUser;
import cn.lmx.basic.base.R;
import cn.lmx.basic.dozer.DozerUtils;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.basic.utils.TreeUtil;
import cn.lmx.kpu.authority.dto.auth.AuthorityResourceDTO;
import cn.lmx.kpu.authority.dto.auth.RouterMeta;
import cn.lmx.kpu.authority.dto.auth.VueRouter;
import cn.lmx.kpu.authority.entity.auth.Resource;
import cn.lmx.kpu.authority.entity.auth.Role;
import cn.lmx.kpu.authority.service.auth.ResourceService;
import cn.lmx.kpu.authority.service.auth.RoleService;
import cn.lmx.kpu.model.entity.base.SysResource;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.model.vo.query.ResourceQueryDTO;
import cn.lmx.kpu.security.properties.SecurityProperties;
import cn.lmx.kpu.userinfo.service.ResourceHelperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.lmx.kpu.common.constant.SwaggerConstants.*;


/**
 * <p>
 * 前端控制器
 * 资源 角色 菜单
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(value = "OauthResourceResource", tags = "资源")
public class OauthResourceResourceController {
    private final DozerUtils dozer;
    private final ResourceHelperService menuHelperService;
    private final ResourceService resourceService;
    private final RoleService roleService;
    private final SecurityProperties securityProperties;

    /**
     * 查询用户可用的所有资源
     *
     * @param resource <br>
     *                 menuId 菜单 <br>
     *                 userId 当前登录人id
     */
    @ApiOperation(value = "查询用户可用的所有资源", notes = "查询用户可用的所有资源")
    @GetMapping("/resource/visible")
    public R<AuthorityResourceDTO> visible(ResourceQueryDTO resource, @ApiIgnore @LoginUser SysUser sysUser) {
        if (resource == null) {
            resource = new ResourceQueryDTO();
        }

        if (resource.getUserId() == null) {
            resource.setUserId(sysUser.getId());
        }
        List<SysResource> resourceList = menuHelperService.findVisibleAuth(resource);
        List<Role> roleList = roleService.findRoleByUserId(resource.getUserId());
        return R.success(AuthorityResourceDTO.builder()
                .roleList(roleList.parallelStream().filter(ObjectUtil::isNotEmpty).map(Role::getCode).distinct().collect(Collectors.toList()))
                .resourceList(CollHelper.split(resourceList, SysResource::getCode, StrPool.SEMICOLON))
                .caseSensitive(securityProperties.getCaseSensitive())
                .enabled(securityProperties.getEnabled())
                .build());
    }

    /**
     * 查询用户可用的所有菜单
     *
     * @param group  分组 <br>
     * @param userId 指定用户id
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "菜单组", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "查询用户可用的所有菜单", notes = "查询用户可用的所有菜单")
    @GetMapping("/menu/menus")
    public R<List<Resource>> myResources(@RequestParam(value = "group", required = false) String group,
                                         @RequestParam(value = "userId", required = false) Long userId,
                                         @ApiIgnore @LoginUser SysUser sysUser) {
        if (userId == null || userId <= 0) {
            userId = sysUser.getId();
        }
        List<Resource> list = resourceService.findVisibleResource(group, userId);
        log.info("list={}", list.size());
        List<Resource> tree = TreeUtil.buildTree(list);
        return R.success(tree);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "菜单组", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "查询用户可用的所有菜单路由树", notes = "查询用户可用的所有菜单路由树")
    @GetMapping("/menu/routerInit")
    public R<List<VueRouter>> myRouter(@RequestParam(value = "group", required = false) String group,
                                       @RequestParam(value = "userId", required = false) Long userId,
                                       @ApiIgnore @LoginUser SysUser sysUser) {
        if (userId == null || userId <= 0) {
            userId = sysUser.getId();
        }
        List<Resource> list = resourceService.findVisibleResource(group, userId);
        List<VueRouter> treeList = dozer.mapList(list, VueRouter.class);
        return R.success(TreeUtil.buildTree(treeList));
    }

    private List<VueRouter> test() {
        List<VueRouter> list = new ArrayList<>();
        VueRouter parent1 = new VueRouter();
        parent1.setName("用户管理");
        parent1.setComponent("kpu/org/user/index");
        parent1.setPath("/org/user");
        RouterMeta rm = new RouterMeta();
        rm.setTitle(parent1.getName());
        parent1.setMeta(rm);
        list.add(parent1);

        parent1 = new VueRouter();
        parent1.setName("用户管理2");
        parent1.setComponent("kpu/org/user/index");
        parent1.setPath("/org/user2");
        rm = new RouterMeta();
        rm.setTitle(parent1.getName());
        parent1.setMeta(rm);
        List<VueRouter> childrens = new ArrayList<>();
        VueRouter children = new VueRouter();
        children.setName("我是用户管理2的隐藏菜单");
        children.setComponent("kpu/org/user/index");
        children.setPath("/org/user2/hideResource");
        rm = new RouterMeta();
        rm.setTitle(children.getName());
        children.setMeta(rm);
        childrens.add(children);
        parent1.setChildren(childrens);
        list.add(parent1);


        parent1 = new VueRouter();
        parent1.setName("系统管理");
        parent1.setComponent("Layout");
        parent1.setPath("/system");
        rm = new RouterMeta();
        rm.setTitle(parent1.getName());
        parent1.setMeta(rm);

        childrens = new ArrayList<>();
        children = new VueRouter();
        children.setName("角色管理");
        children.setComponent("kpu/system/role/index");
        children.setPath("/system/role");
        rm = new RouterMeta();
        rm.setTitle(children.getName());
        children.setMeta(rm);

        VueRouter roleAdd = new VueRouter();
        roleAdd.setName("角色新增");
        roleAdd.setComponent("kpu/system/role/RoleResource");
        roleAdd.setPath("/system/role/add");
        rm = new RouterMeta();
        rm.setTitle(roleAdd.getName());
        roleAdd.setMeta(rm);
        List<VueRouter> roleAddChi = new ArrayList<>();
        roleAddChi.add(roleAdd);
        children.setChildren(roleAddChi);

        childrens.add(children);

        children = new VueRouter();
        children.setName("菜单管理");
        children.setComponent("kpu/system/menu/index");
        children.setPath("/system/menu");
        rm = new RouterMeta();
        rm.setTitle(children.getName());
        children.setMeta(rm);
        childrens.add(children);


        children = new VueRouter();
        children.setName("隐藏菜单");
        children.setComponent("kpu/system/parameter/index");
        children.setPath("/system/parameter");
        rm = new RouterMeta();
        rm.setTitle(children.getName());
        // 如果是隐藏菜单，一定要配置CurrentActiveResource
        children.setMeta(rm);

        childrens.add(children);

        parent1.setChildren(childrens);
        list.add(parent1);


        return list;
    }

}
