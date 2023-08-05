package cn.lmx.kpu.authority.controller.auth;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.dozer.DozerUtils;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.basic.utils.TreeUtil;
import cn.lmx.kpu.authority.dto.auth.*;
import cn.lmx.kpu.authority.entity.auth.Resource;
import cn.lmx.kpu.authority.enumeration.auth.ResourceTypeEnum;
import cn.lmx.kpu.authority.service.auth.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 前端控制器
 * 菜单
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/resource")
@Api(value = "Resource", tags = "菜单")
@PreAuth(replace = "authority:resource:")
public class ResourceController extends SuperCacheController<ResourceService, Long, Resource, ResourceSaveVO, ResourceUpdateVo, Resource, ResourceResultVO> {
    private final DozerUtils dozer;
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public R<Resource> handlerSave(ResourceSaveVO resourceSaveVO) {
        List<String> codes = resourceSaveVO.getAuths().stream().map(AuthDto::getCode).collect(Collectors.toList());
        // codes 不能为空字符 为空抛出异常
        if (codes.stream().anyMatch(StrUtil::isEmpty)) {
            return fail("权限编码不能为空");
        }
        // codes 不能重复 重复抛出异常
        if (codes.size() != codes.stream().distinct().count()) {
            return fail("权限编码重复");
        }
        // 查询数据是否重复 抛出有重复的异常的编码
        if (codes != null && codes.size() > 0) {
            List<String> oldCodes = superService.list(Wraps.<Resource>lbQ().in(Resource::getCode, codes)).stream().map(Resource::getCode).collect(Collectors.toList());
            if (oldCodes.size() > 0) {
                return fail("权限编码重复:[%s]", oldCodes);
            }
        }


        Resource resource = BeanPlusUtil.toBean(resourceSaveVO, Resource.class);

        superService.saveWithCache(resource);
        BeanPlusUtil.toBeanList(resourceSaveVO.getAuths(), Resource.class);
        List<Resource> list = resourceSaveVO.getAuths().stream().map(item -> {
            Resource resourceResource = new Resource();
            CopyOptions copyOptions = new CopyOptions();
            copyOptions.setFieldMapping(MapUtil.of(Pair.of("name", "title")));
            BeanPlusUtil.copyProperties(item, resourceResource, copyOptions);
            resourceResource.setResourceType(ResourceTypeEnum.FUNCTION.getCode());
            resourceResource.setParentId(resource.getId());
            return resourceResource;
        }).collect(Collectors.toList());
        superService.saveBatchWithCache(list);
        return success(resource);
    }

    @Override
    public R<Resource> handlerUpdate(ResourceUpdateVo model) {
        List<String> codes = model.getAuths().stream().map(AuthDto::getCode).collect(Collectors.toList());
        //排除为空的id
        List<Long> ids = model.getAuths().stream().filter(item -> item.getId() != null).map(AuthDto::getId).collect(Collectors.toList());
        // codes 不能为空字符 为空抛出异常
        if (codes.stream().anyMatch(StrUtil::isEmpty)) {
            return fail("权限编码不能为空");
        }
        List<Long> oldIds = superService.findAuthByParentId(model.getId()).stream().map(AuthDto::getId).collect(Collectors.toList());
        // 删除权限
        List<Long> removeIds = oldIds.stream().filter(item -> !ids.contains(item)).collect(Collectors.toList());
        if (removeIds.size() > 0) {
            superService.removeByIdWithCache(removeIds);
        }
        // codes 不能重复 重复抛出异常
        if (codes.size() != codes.stream().distinct().count()) {
            return fail("权限编码重复");
        }
        if (codes != null && codes.size() > 0) {
            // 查询数据是否重复 抛出有重复的异常的编码 但是不包含自己
            List<String> oldCodes = superService.list(Wraps.<Resource>lbQ().in(Resource::getCode, codes).notIn(ids != null && ids.size() > 0, Resource::getId, ids)).stream().map(Resource::getCode).collect(Collectors.toList());
            if (oldCodes.size() > 0) {
                return fail("权限编码重复:%s", oldCodes);
            }
        }

        Resource resource = BeanPlusUtil.toBean(model, Resource.class);

        superService.updateWithCache(resource);
        BeanPlusUtil.toBeanList(model.getAuths(), Resource.class);
        List<Resource> list = model.getAuths().stream().map(item -> {
            Resource resourceResource = new Resource();
            CopyOptions copyOptions = new CopyOptions();
            copyOptions.setFieldMapping(MapUtil.of(Pair.of("name", "title")));
            BeanPlusUtil.copyProperties(item, resourceResource, copyOptions);
            resourceResource.setParentId(resource.getId());
            resourceResource.setResourceType(ResourceTypeEnum.FUNCTION.getCode());
            return resourceResource;
        }).collect(Collectors.toList());
        superService.saveOrUpdateBatchWithCache(list);
        return success(resource);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        superService.removeByIdWithCache(ids);
        return success();
    }

    @Override
    @SysLog("'查询:' + #id")
    public R<ResourceResultVO> get(@PathVariable Long id) {
        ResourceResultVO resultVO = BeanPlusUtil.toBean(superService.getByIdCache(id), getResultVOClass());
        resultVO.setAuths(superService.findAuthByParentId(resultVO.getId()));
        return R.success(resultVO);
    }

    /**
     * 查询系统中所有的的菜单树结构， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @ApiOperation(value = "查询系统所有的菜单和视图", notes = "查询系统所有的菜单和视图")
    @PostMapping("/treeResourceAndView")
    @SysLog(value = "查询系统所有的菜单和视图", response = false)
    public R<List<ResourceViewTreeVO>> allTree() {
        List<Resource> list = superService.list(Wraps.<Resource>lbQ().in(Resource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()).orderByAsc(Resource::getSortValue));
        List<ResourceViewTreeVO> viewTreeVOList = dozer.mapList(list, ResourceViewTreeVO.class);
        return success(TreeUtil.buildTree(viewTreeVOList));
    }

    /**
     * 查询系统所有的菜单和资源树， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @ApiOperation(value = "查询系统所有的菜单和资源树", notes = "查询系统所有的菜单和资源树")
    @PostMapping("/resourceResourceTree")
    @SysLog(value = "查询系统所有的菜单和资源树", response = false)
    public R<List<ResourceTreeVO>> resourceResourceTree(Boolean resourceOnly) {
        List<ResourceTreeVO> resourceResourceTree = superService.findResourceResource(resourceOnly);
        echoService.action(resourceResourceTree);
        return success(TreeUtil.buildTree(resourceResourceTree));
    }

    /**
     * 查询系统所有的数据权限  不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @ApiOperation(value = "查询系统所有的数据权限", notes = "查询系统所有的数据权限")
    @PostMapping("/findResourceDataScopeTree")
    @SysLog(value = "查询系统所有的数据权限", response = false)
    public R<List<ResourceTreeVO>> findResourceDataScopeTree() {
        return success(superService.findResourceDataScopeTree());
    }

    @ApiOperation(value = "上移", notes = "上移")
    @PostMapping("/moveUp/{id}")
    @SysLog("'上移:' + #id")
    public R<Boolean> moveUp(@PathVariable Long id) {
        return R.success(superService.moveUp(id));
    }

    @ApiOperation(value = "下移", notes = "下移")
    @PostMapping("/moveDown/{id}")
    @SysLog("'下移:' + #id")
    public R<Boolean> moveDown(@PathVariable Long id) {
        return R.success(superService.moveDown(id));
    }

    @ApiOperation(value = "移动", notes = "移动")
    @PostMapping("/move/{currentId}/{targetId}")
    @SysLog("'移动:' + #id + '到' + #targetId")
    public R<Boolean> move(@PathVariable Long currentId, @PathVariable Long targetId) {
        return R.success(superService.move(currentId, targetId));
    }

}
