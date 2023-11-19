package cn.lmx.kpu.authority.manager.auth.impl;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.*;
import cn.lmx.kpu.authority.dao.auth.ResourceMapper;
import cn.lmx.kpu.authority.dto.auth.AuthDto;
import cn.lmx.kpu.authority.dto.auth.ResourceTreeVO;
import cn.lmx.kpu.authority.entity.auth.Resource;
import cn.lmx.kpu.authority.enumeration.auth.ResourceTypeEnum;
import cn.lmx.kpu.authority.manager.auth.ResourceManager;
import cn.lmx.kpu.authority.service.auth.UserService;
import cn.lmx.kpu.common.cache.auth.ResourceCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserResourceCacheKeyBuilder;
import cn.lmx.kpu.common.constant.DefValConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.lmx.basic.utils.StrPool.DEF_PARENT_ID;


/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/26  19:06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ResourceManagerImpl extends SuperCacheManagerImpl<ResourceMapper, Resource> implements ResourceManager {

    private final UserService userService;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ResourceCacheKeyBuilder();
    }

    /**
     * 查询用户可用菜单
     * 1，查询缓存中存放的当前用户拥有的所有菜单列表 [menuId,menuId...]
     * 2，缓存&DB为空则返回
     * 3，缓存总用户菜单列表 为空，但db存在，则将list便利set到缓存，并直接返回
     * 4，缓存存在用户菜单列表，则根据菜单id遍历去缓存查询菜单。
     * 5，过滤group后，返回
     *
     * <p>
     * 注意：什么地方需要清除 USER_MENU 缓存
     * 给用户重新分配角色时， 角色重新分配资源/菜单时
     *
     * @param group  分组
     * @param userId 用户id
     * @return 指定用户的拥有的菜单
     */
    @Override
    public List<Resource> findVisibleResource(String group, Long userId) {
        CacheKey userResourceKey = new UserResourceCacheKeyBuilder().key(userId);
        List<Resource> visibleResource = new ArrayList<>();
        if (userId.equals(StrPool.ADMIN_ID)) {
            visibleResource.addAll(baseMapper.selectList(Wraps.<Resource>lbQ().in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()).orderByAsc(Resource::getSortValue)));
            return menuListFilterGroup(group, visibleResource);
        }
        List<Long> list = cacheOps.get(userResourceKey, k -> {
            log.debug("userResourceKey={}", userResourceKey.getKey());
            visibleResource.addAll(baseMapper.findVisibleResource(userId));
            return visibleResource.stream().map(Resource::getId).collect(Collectors.toList());
        });
        log.debug("visibleResource={}", visibleResource.size());
        if (!visibleResource.isEmpty()) {
            visibleResource.forEach(this::setCache);
        } else {
            log.debug("list={}", list.size());
            visibleResource.addAll(findByIds(list, this::listByIds));
        }
        log.debug("visibleResource2={}", visibleResource.size());
        return menuListFilterGroup(group, visibleResource);
    }

    private List<Resource> menuListFilterGroup(String group, List<Resource> visibleResource) {
        if (StrUtil.isEmpty(group)) {
            return visibleResource;
        }
        return visibleResource.stream().filter(menu -> group.equals(menu.getGroup())).collect(Collectors.toList());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        boolean result = this.removeByIds(ids);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateWithCache(Resource menu) {
        if (StrUtil.containsAny(menu.getResourceType(), ResourceTypeEnum.VIEW.getCode())) {
//            ArgumentAssert.notEmpty(menu.getPath(), "【地址栏路径】不能为空");
            ArgumentAssert.notEmpty(menu.getComponent(), "【页面路径】不能为空");
            ArgumentAssert.isFalse(checkName(menu.getId(), menu.getName()), "【名称】:{}重复", menu.getName());
//            if (!ValidatorUtil.isUrl(menu.getPath())) {
//                ArgumentAssert.isFalse(checkPath(menu.getId(), menu.getPath()), "【地址栏路径】:{}重复", menu.getPath());
//            }
        }
        Resource old = getById(menu);
        ArgumentAssert.notNull(old, "您修改的菜单已不存在");
        checkGeneral(menu, true);

        fill(menu);
        Boolean oldIsPublic = DefValueHelper.getOrDef(old.getIsGeneral(), false);
        Boolean newIsPublic = DefValueHelper.getOrDef(menu.getIsGeneral(), false);
        if (!oldIsPublic.equals(newIsPublic)) {
            List<Long> userIds = userService.findAllUserId();
            cacheOps.del(userIds.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }

        return this.updateById(menu);
    }

    public Boolean checkPath(Long id, String path) {
        return baseMapper.selectCount(Wraps.<Resource>lbQ().ne(Resource::getId, id).eq(Resource::getPath, path)) > 0;
    }

    public Boolean checkName(Long id, String name) {
        return baseMapper.selectCount(Wraps.<Resource>lbQ().ne(Resource::getId, id)
                .in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode()).eq(Resource::getName, name)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveWithCache(Resource menu) {
        if (StrUtil.containsAny(menu.getResourceType(), ResourceTypeEnum.VIEW.getCode())) {
//            ArgumentAssert.notEmpty(menu.getPath(), "请填写【地址栏路径】");
            ArgumentAssert.notEmpty(menu.getComponent(), "请填写【页面路径】");
            ArgumentAssert.isFalse(checkName(null, menu.getName()), "【名称】:{}重复", menu.getName());
//            if (!ValidatorUtil.isUrl(menu.getPath())) {
//                ArgumentAssert.isFalse(checkPath(null, menu.getPath()), "【地址栏路径】:{}重复", menu.getPath());
//            }
        }

        fill(menu);
        menu.setState(Convert.toBool(menu.getState(), true));
        menu.setIsGeneral(Convert.toBool(menu.getIsGeneral(), false));
        menu.setParentId(Convert.toLong(menu.getParentId(), DEF_PARENT_ID));

        checkGeneral(menu, false);

        save(menu);

        if (menu.getIsGeneral()) {
            List<Long> userIds = userService.findAllUserId();
            cacheOps.del(userIds.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }
        return true;
    }

    private void checkGeneral(Resource data, boolean isUpdate) {
        if (data.getIsGeneral() == null) {
            return;
        }

        if (data.getIsGeneral()) {
            // isGeneral 子节点 改成是，父节点全是
            if (!TreeUtil.isRoot(data.getParentId())) {
                Resource parent = getById(data.getParentId());
                ArgumentAssert.notNull(parent, "父节点不存在");
                ArgumentAssert.isTrue(parent.getIsGeneral(), "请先将父节点{} “公共资源”字段修改为：“是”，在修改本节点", parent.getName());
            }
            return;
        }

        if (isUpdate) {
            // isGeneral 父节点 改成否，子节点全否
            List<Resource> childrenList = findChildrenByParentId(data.getId());
            if (CollUtil.isNotEmpty(childrenList)) {
                childrenList.forEach(item -> {
                    ArgumentAssert.isFalse(item.getIsGeneral(), "请先将子节点{} “公共资源”字段修改为：“否”，在修改本节点", item.getName());
                });
            }
        }
    }

    public List<Resource> findChildrenByParentId(Long parentId) {
        ArgumentAssert.notNull(parentId, "parentId 不能为空");
        return list(Wraps.<Resource>lbQ().in(Resource::getParentId, parentId).orderByAsc(Resource::getSortValue));
    }

    private void fill(Resource resource) {
        if (resource.getParentId() == null || resource.getParentId() <= 0) {
            resource.setParentId(DefValConstants.PARENT_ID);
            resource.setTreePath(DefValConstants.ROOT_PATH);
            resource.setTreeGrade(DefValConstants.TREE_GRADE);
        } else {
            Resource parent = getByIdCache(resource.getParentId());
            ArgumentAssert.notNull(parent, "请正确填写父级");
            resource.setTreeGrade(parent.getTreeGrade() + 1);
            resource.setTreePath(TreeUtil.getTreePath(parent.getTreePath(), parent.getId()));
        }
    }

    @Override
    public List<ResourceTreeVO> findResourceResource(Boolean menuOnly) {
        if (menuOnly == null) {
            menuOnly = false;
        }
        List<Resource> menus;
        if (menuOnly) {
            // 只查询菜单
            menus = super.list(Wraps.<Resource>lbQ().in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()));
        } else {
            // 查询所有
            menus = super.list(Wraps.<Resource>lbQ().in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode(), ResourceTypeEnum.FUNCTION.getCode()));
        }

        List<ResourceTreeVO> list = menus.stream().map(item -> {
            ResourceTreeVO menu = new ResourceTreeVO();
            CopyOptions copyOptions = new CopyOptions();
            copyOptions.setFieldMapping(MapUtil.of(Pair.of("title", "label")));
            BeanPlusUtil.copyProperties(item, menu, copyOptions);
            return menu;
        }).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<ResourceTreeVO> findResourceDataScopeTree() {
        List<Resource> datas = super.list(Wraps.<Resource>lbQ().eq(Resource::getResourceType, ResourceTypeEnum.DATA.getCode()));

        // 将id和treePath截取后 合并成list，其中treePath存放的是该节点的所有父节点ID
        Stream<Long> dataScopeIdStream = datas.parallelStream().map(Resource::getId);
        Stream<Long> parentIdStream = datas.parallelStream()
                .map(item -> StrUtil.splitToArray(item.getTreePath(), DefValConstants.ROOT_PATH)) // 将父节点路径截取为父ID数组
                .flatMap(Arrays::stream) // 数组流 转 字符串流
                .filter(ObjectUtil::isNotEmpty) // 去除空数据
                .map(Convert::toLong) // 类型转换
                ;
        // 合并 数据权限ID 和 父ID
        List<Long> resourceIdList = Stream.concat(dataScopeIdStream, parentIdStream).distinct().collect(Collectors.toList());
        if (CollUtil.isEmpty(resourceIdList)) {
            return Collections.emptyList();
        }
        List<Resource> menus = listByIds(resourceIdList);

        List<ResourceTreeVO> menuList = menus.stream().map(item -> {
            ResourceTreeVO menu = new ResourceTreeVO();
            BeanPlusUtil.copyProperties(item, menu);
            return menu;
        }).collect(Collectors.toList());

        return TreeUtil.buildTree(menuList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatchWithCache(List<Resource> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        list.forEach(this::fill);
        saveBatch(list);
        List<Long> userIds = userService.findAllUserId();
        list.forEach(item -> {
            if (item.getResourceType().equals(ResourceTypeEnum.MENU.getCode()) || item.getResourceType().equals(ResourceTypeEnum.VIEW.getCode())) {
                cacheOps.del(userIds.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            } else {
                cacheOps.del(userIds.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            }
        });
        cacheOps.del(userIds.stream().map(new ResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
    }

    @Override
    public List<AuthDto> findAuthByParentId(Serializable parentId) {
        List<Resource> menuList = list(Wraps.<Resource>lbQ().eq(Resource::getParentId, parentId).eq(Resource::getResourceType, ResourceTypeEnum.FUNCTION.getCode()).orderByAsc(Resource::getSortValue));
        List<AuthDto> list = menuList.stream().map(item -> {
            AuthDto authDto = new AuthDto();
            CopyOptions copyOptions = new CopyOptions();
            copyOptions.setFieldMapping(MapUtil.of(Pair.of("title", "name")));
            BeanPlusUtil.copyProperties(item, authDto, copyOptions);
            authDto.setName(item.getTitle());
            return authDto;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateBatchWithCache(List<Resource> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        list.forEach(this::fill);
        saveOrUpdateBatch(list);
        List<Long> userIds = userService.findAllUserId();
        list.forEach(item -> {
            if (item.getResourceType().equals(ResourceTypeEnum.MENU.getCode()) || item.getResourceType().equals(ResourceTypeEnum.VIEW.getCode())) {
                cacheOps.del(userIds.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            } else {
                cacheOps.del(userIds.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            }
        });
        cacheOps.del(userIds.stream().map(new ResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
    }

    @Override
    public Boolean moveUp(Long id) {
        // 查询菜单是否能上移 不上移的情况：1.没有父节点 2.已经是第一个 否则就报错
        Resource menu = getById(id);
        ArgumentAssert.notNull(menu, "菜单不存在");
        if (menu.getParentId() == null) {
            throw BizException.wrap("根节点不能移动");
        }
        Resource parent = getById(menu.getParentId());
        ArgumentAssert.notNull(parent, "请正确填写父级");
        List<Resource> list = list(Wraps.<Resource>lbQ().eq(Resource::getParentId, parent.getId()).in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()).orderByAsc(Resource::getSortValue));
        if (list.size() <= 1) {
            throw BizException.wrap("已经是第一个了");
        }
        // 交换位置
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            Resource item = list.get(i);
            if (item.getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            throw BizException.wrap("已经是第一个了");
        }
        Resource pre = list.get(index - 1);
        Resource current = list.get(index);
        Integer temp = pre.getSortValue();
        pre.setSortValue(current.getSortValue());
        current.setSortValue(temp);
        updateById(pre);
        updateById(current);
        return true;
    }

    @Override
    public Boolean moveDown(Long id) {
        // 查询菜单是否能下移 不下移的情况：1.没有父节点 2.已经是最后一个 否则就报错
        Resource menu = getById(id);
        ArgumentAssert.notNull(menu, "菜单不存在");
        if (menu.getParentId() == null) {
            throw BizException.wrap("根节点不能移动");
        }
        Resource parent = getById(menu.getParentId());
        ArgumentAssert.notNull(parent, "请正确填写父级");
        List<Resource> list = list(Wraps.<Resource>lbQ().eq(Resource::getParentId, parent.getId()).in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()).orderByAsc(Resource::getSortValue));
        if (list.size() <= 1) {
            throw BizException.wrap("已经是最后一个了");
        }
        // 交换位置
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            Resource item = list.get(i);
            if (item.getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index == list.size() - 1) {
            throw BizException.wrap("已经是最后一个了");
        }
        Resource next = list.get(index + 1);
        Resource current = list.get(index);
        Integer temp = next.getSortValue();
        next.setSortValue(current.getSortValue());
        current.setSortValue(temp);
        updateById(next);
        updateById(current);
        return true;
    }

    @Override
    public Boolean move(Long currentId, Long targetId) {
        if (targetId.equals(DefValConstants.PARENT_ID)) {
            //移动到根节点
            return moveRoot(currentId);
        }
        // 不能移动到当前节点的所有子和孙节点下 不能移动到自己的父节点下 sort_value 当前节点和目标节点重新排序 重新计算层级 重新计算路径
        Resource current = getById(currentId);
        ArgumentAssert.notNull(current, "当前菜单不存在");
        Resource target = getById(targetId);
        ArgumentAssert.notNull(target, "目前菜单不存在");
        if (current.getParentId() == null) {
            throw BizException.wrap("根节点不能移动");
        }
        if (current.getId().equals(target.getId())) {
            throw BizException.wrap("不能移动到自己下面");
        }
        if (current.getParentId().equals(target.getId())) {
            throw BizException.wrap("不能移动到自己的子节点下");
        }
        // 查询当前节点的所有子节点
        List<Resource> list = list(Wraps.<Resource>lbQ().likeLeft(Resource::getTreePath, TreeUtil.getTreePath(current.getTreePath(), current.getId())).orderByAsc(Resource::getResourceType, Resource::getSortValue));
        List<Long> ids = list.stream().map(Resource::getId).collect(Collectors.toList());
        if (ids.contains(target.getId())) {
            throw BizException.wrap("不能移动到自己的子节点下");
        }
        //重排当前父节点下的所有子节点 sort_value 如果是最大sort_value 就不用重排
        List<Resource> parentList = list(Wraps.<Resource>lbQ().eq(Resource::getParentId, current.getParentId()).in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()).orderByAsc(Resource::getSortValue));
        if (parentList.size() > 1 && !parentList.get(parentList.size() - 1).getId().equals(current.getId())) {
            // 删除要移除的节点
            parentList.removeIf(item -> item.getId().equals(current.getId()));
            for (int i = 0; i < parentList.size(); i++) {
                Resource item = parentList.get(i);
                item.setSortValue(i + 1);
            }
            updateBatchById(parentList);
        }

        // 移动到目标节点下
        current.setParentId(target.getId());
        //查询目标节点下当前最大的sort_value
        Integer sortValue = baseMapper.selectMaxSortValue(target.getId());
        current.setSortValue(sortValue == null ? 1 : sortValue + 1);
        // 重新计算层级
        ArgumentAssert.notNull(target, "请正确填写父级");
        current.setTreeGrade(target.getTreeGrade() + 1);
        current.setTreePath(TreeUtil.getTreePath(target.getTreePath(), target.getId()));
        updateById(current);
        // 重新计算路径
        for (Resource menu : list) {
            fill(menu);
            updateById(menu);
        }


        return true;
    }

    private Boolean moveRoot(Long currentId) {
        Resource current = getById(currentId);
        List<Resource> parentList = list(Wraps.<Resource>lbQ().eq(Resource::getParentId, current.getParentId()).in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()).orderByAsc(Resource::getSortValue));
        if (parentList.size() > 1 && !parentList.get(parentList.size() - 1).getId().equals(current.getId())) {
            // 删除要移除的节点
            parentList.removeIf(item -> item.getId().equals(current.getId()));
            for (int i = 0; i < parentList.size(); i++) {
                Resource item = parentList.get(i);
                item.setSortValue(i + 1);
            }
            updateBatchById(parentList);
        }
        List<Resource> list = list(Wraps.<Resource>lbQ().likeLeft(Resource::getTreePath, TreeUtil.getTreePath(current.getTreePath(), current.getId())).orderByAsc(Resource::getResourceType, Resource::getSortValue));
        // 移动到目标节点下
        current.setParentId(DefValConstants.PARENT_ID);
        Integer sortValue = baseMapper.selectMaxSortValue(DefValConstants.PARENT_ID);
        current.setSortValue(sortValue == null ? 1 : sortValue + 1);
        current.setTreePath(DefValConstants.ROOT_PATH);
        current.setTreeGrade(DefValConstants.TREE_GRADE);

        updateById(current);
        // 重新计算路径
        for (Resource menu : list) {
            fill(menu);
            updateById(menu);
        }
        return true;
    }

}

