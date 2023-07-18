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
import cn.lmx.kpu.authority.entity.auth.Menu;
import cn.lmx.kpu.authority.enumeration.auth.ResourceTypeEnum;
import cn.lmx.kpu.authority.service.auth.MenuService;
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
@RequestMapping("/menu")
@Api(value = "Menu", tags = "菜单")
@PreAuth(replace = "authority:menu:")
public class MenuController extends SuperCacheController<MenuService, Long, Menu, MenuSaveVO, MenuUpdateVo, Menu, MenuResultVO> {
    private final DozerUtils dozer;
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public R<Menu> handlerSave(MenuSaveVO menuSaveVO) {
        List<String> codes = menuSaveVO.getAuths().stream().map(AuthsDto::getCode).collect(Collectors.toList());
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
            List<String> oldCodes = baseService.list(Wraps.<Menu>lbQ().in(Menu::getCode, codes)).stream().map(Menu::getCode).collect(Collectors.toList());
            if (oldCodes.size() > 0) {
                return fail("权限编码重复:[%s]", oldCodes);
            }
        }


        Menu menu = BeanPlusUtil.toBean(menuSaveVO, Menu.class);

        baseService.saveWithCache(menu);
        BeanPlusUtil.toBeanList(menuSaveVO.getAuths(), Menu.class);
        List<Menu> list = menuSaveVO.getAuths().stream().map(item -> {
            Menu menuResource = new Menu();
            CopyOptions copyOptions = new CopyOptions();
            copyOptions.setFieldMapping(MapUtil.of(Pair.of("name", "title")));
            BeanPlusUtil.copyProperties(item, menuResource, copyOptions);
            menuResource.setResourceType(ResourceTypeEnum.FUNCTION.getCode());
            menuResource.setParentId(menu.getId());
            return menuResource;
        }).collect(Collectors.toList());
        baseService.saveBatchWithCache(list);
        return success(menu);
    }

    @Override
    public R<Menu> handlerUpdate(MenuUpdateVo model) {
        List<String> codes = model.getAuths().stream().map(AuthsDto::getCode).collect(Collectors.toList());
        //排除为空的id
        List<Long> ids = model.getAuths().stream().filter(item -> item.getId() != null).map(AuthsDto::getId).collect(Collectors.toList());
        // codes 不能为空字符 为空抛出异常
        if (codes.stream().anyMatch(StrUtil::isEmpty)) {
            return fail("权限编码不能为空");
        }
        List<Long> oldIds = baseService.findAuthByParentId(model.getId()).stream().map(AuthsDto::getId).collect(Collectors.toList());
        // 删除权限
        List<Long> removeIds = oldIds.stream().filter(item -> !ids.contains(item)).collect(Collectors.toList());
        if (removeIds.size() > 0) {
            baseService.removeByIdWithCache(removeIds);
        }
        // codes 不能重复 重复抛出异常
        if (codes.size() != codes.stream().distinct().count()) {
            return fail("权限编码重复");
        }
        // 查询数据是否重复 抛出有重复的异常的编码 但是不包含自己
        List<String> oldCodes = baseService.list(Wraps.<Menu>lbQ().in(Menu::getCode, codes).notIn(Menu::getId, ids)).stream().map(Menu::getCode).collect(Collectors.toList());
        if (oldCodes.size() > 0) {
            return fail("权限编码重复:%s", oldCodes);
        }

        Menu menu = BeanPlusUtil.toBean(model, Menu.class);

        baseService.updateWithCache(menu);
        BeanPlusUtil.toBeanList(model.getAuths(), Menu.class);
        List<Menu> list = model.getAuths().stream().map(item -> {
            Menu menuResource = new Menu();
            CopyOptions copyOptions = new CopyOptions();
            copyOptions.setFieldMapping(MapUtil.of(Pair.of("name", "title")));
            BeanPlusUtil.copyProperties(item, menuResource, copyOptions);
            menuResource.setParentId(menu.getId());
            menuResource.setResourceType(ResourceTypeEnum.FUNCTION.getCode());
            return menuResource;
        }).collect(Collectors.toList());
        baseService.saveOrUpdateBatchWithCache(list);
        return success(menu);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        baseService.removeByIdWithCache(ids);
        return success();
    }

    @Override
    @SysLog("'查询:' + #id")
    public R<MenuResultVO> get(@PathVariable Long id) {
        MenuResultVO resultVO = BeanPlusUtil.toBean(baseService.getByIdCache(id), getResultVOClass());
        resultVO.setAuths(baseService.findAuthByParentId(resultVO.getId()));
        return R.success(resultVO);
    }

    /**
     * 查询系统中所有的的菜单树结构， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @ApiOperation(value = "查询系统所有的菜单和视图", notes = "查询系统所有的菜单和视图")
    @PostMapping("/treeMenuAndView")
    @SysLog("查询系统所有的菜单和视图")
    public R<List<MenuViewTreeVO>> allTree() {
        List<Menu> list = baseService.list(Wraps.<Menu>lbQ().in(Menu::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()).orderByAsc(Menu::getSortValue));
        List<MenuViewTreeVO> viewTreeVOList = dozer.mapList(list, MenuViewTreeVO.class);
        return success(TreeUtil.buildTree(viewTreeVOList));
    }

    /**
     * 查询系统所有的菜单和资源树， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @ApiOperation(value = "查询系统所有的菜单和资源树", notes = "查询系统所有的菜单和资源树")
    @PostMapping("/menuResourceTree")
    @SysLog("查询系统所有的菜单和资源树")
    public R<List<MenuResourceTreeVO>> menuResourceTree(Boolean menuOnly) {
        List<MenuResourceTreeVO> menuResourceTree = baseService.findMenuResource(menuOnly);
        echoService.action(menuResourceTree);
        return success(TreeUtil.buildTree(menuResourceTree));
    }

    /**
     * 查询系统所有的数据权限  不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @ApiOperation(value = "查询系统所有的数据权限", notes = "查询系统所有的数据权限")
    @PostMapping("/findMenuDataScopeTree")
    @SysLog("查询系统所有的数据权限")
    public R<List<MenuResourceTreeVO>> findMenuDataScopeTree() {
        return success(baseService.findMenuDataScopeTree());
    }

    @ApiOperation(value = "上移", notes = "上移")
    @PostMapping("/moveUp/{id}")
    @SysLog("'上移:' + #id")
    public R<Boolean> moveUp(@PathVariable Long id) {
        return R.success(baseService.moveUp(id));
    }

    @ApiOperation(value = "下移", notes = "下移")
    @PostMapping("/moveDown/{id}")
    @SysLog("'下移:' + #id")
    public R<Boolean> moveDown(@PathVariable Long id) {
        return R.success(baseService.moveDown(id));
    }

    @ApiOperation(value = "移动", notes = "移动")
    @PostMapping("/move/{currentId}/{targetId}")
    @SysLog("'移动:' + #id + '到' + #targetId")
    public R<Boolean> move(@PathVariable Long currentId, @PathVariable Long targetId) {
        return R.success(baseService.move(currentId, targetId));
    }

}
