package cn.lmx.kpu.authority.controller.auth;

import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.dozer.DozerUtils;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
public class MenuController extends SuperCacheController<MenuService, Long, Menu, Menu, MenuSaveDTO, MenuUpdateDTO> {
    private final DozerUtils dozer;

    @Override
    public R<Menu> handlerSave(MenuSaveDTO menuSaveDTO) {
        Menu menu = BeanPlusUtil.toBean(menuSaveDTO, Menu.class);
        baseService.saveWithCache(menu);
        return success(menu);
    }

    @Override
    public R<Menu> handlerUpdate(MenuUpdateDTO model) {
        Menu menu = BeanPlusUtil.toBean(model, Menu.class);
        baseService.updateWithCache(menu);
        return success(menu);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        baseService.removeByIdWithCache(ids);
        return success();
    }

    /**
     * 查询系统中所有的的菜单树结构， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     *
     */
    @ApiOperation(value = "查询系统所有的菜单和视图", notes = "查询系统所有的菜单和视图")
    @PostMapping("/treeMenuAndView")
    @SysLog("查询系统所有的菜单和视图")
    public R<List<MenuViewTreeVO>> allTree() {
        List<Menu> list = baseService.list(Wraps.<Menu>lbQ().in(Menu::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode()).orderByAsc(Menu::getSortValue));
        List<MenuViewTreeVO> viewTreeVOList= dozer.mapList(list, MenuViewTreeVO.class);
        return success(TreeUtil.buildTree(viewTreeVOList));
    }

    /**
     * 查询系统所有的菜单和资源树， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @ApiOperation(value = "查询系统所有的菜单和资源树", notes = "查询系统所有的菜单和资源树")
    @PostMapping("/menuResourceTree")
    @SysLog("查询系统所有的菜单和资源树")
    public R<List<MenuResourceTreeVO>> menuResourceTree() {
        return success(baseService.findMenuResourceTree());
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
}
