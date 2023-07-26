package cn.lmx.kpu.authority.controller.core;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.basic.utils.TreeUtil;
import cn.lmx.kpu.authority.dto.auth.RoleOrgSaveVO;
import cn.lmx.kpu.authority.dto.core.OrgPageQuery;
import cn.lmx.kpu.authority.dto.core.OrgPageResultVO;
import cn.lmx.kpu.authority.dto.core.OrgSaveVO;
import cn.lmx.kpu.authority.dto.core.OrgUpdateVo;
import cn.lmx.kpu.authority.entity.core.Org;
import cn.lmx.kpu.authority.service.auth.RoleOrgService;
import cn.lmx.kpu.authority.service.core.OrgService;
import cn.lmx.kpu.common.constant.DefValConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.lmx.basic.utils.StrPool.DEF_PARENT_ID;
import static cn.lmx.basic.utils.StrPool.EMPTY;
import static cn.lmx.kpu.common.constant.SwaggerConstants.*;


/**
 * <p>
 * 前端控制器
 * 组织
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RestController
@RequestMapping("/org")
@Api(value = "Org", tags = "组织")
@PreAuth(replace = "authority:org:")
@RequiredArgsConstructor
public class OrgController extends SuperCacheController<OrgService, Long, Org, OrgSaveVO, OrgUpdateVo, OrgPageQuery, OrgPageResultVO> {
    private final EchoService echoService;
    private final RoleOrgService roleOrgService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "name", value = "名称", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "检测名称是否可用", notes = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam String name) {
        return success(baseService.check(id, name));
    }


    @Override
    public R<Org> handlerSave(OrgSaveVO model) {
        Org org = BeanPlusUtil.toBean(model, Org.class);
        fillOrg(org);
        this.baseService.save(org);
        return success(org);
    }

    @Override
    public R<Org> handlerUpdate(OrgUpdateVo model) {
        Org org = BeanPlusUtil.toBean(model, Org.class);
        fillOrg(org);
        this.baseService.updateAllById(org);
        return success(org);
    }

    private void fillOrg(Org org) {
        if (org.getParentId() == null || org.getParentId() <= 0) {
            org.setParentId(DEF_PARENT_ID);
            org.setTreePath(DefValConstants.ROOT_PATH);
        } else {
            Org parent = this.baseService.getByIdCache(org.getParentId());
            ArgumentAssert.notNull(parent, "请正确填写父级组织");

            org.setTreePath(TreeUtil.getTreePath(parent.getTreePath(), parent.getId()));
        }
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return this.success(baseService.remove(ids));
    }


    /**
     * 查询系统所有的组织树
     *
     * @param state 状态
     * @author lmx
     * @date 2023/7/4 14:27
     */
    @ApiOperation(value = "查询系统所有的组织树", notes = "查询系统所有的组织树")
    @GetMapping("/tree")
    @PreAuth("hasAnyPermission('{}view')")
    @SysLog("查询系统所有的组织树")
    public R<List<Org>> tree(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "state", required = false) Boolean state) {
        List<Org> list = this.baseService.list(Wraps.<Org>lbQ()
                .like(Org::getName, name).eq(Org::getState, state).orderByAsc(Org::getSortValue));
        echoService.action(list);
        return this.success(TreeUtil.buildTree(list));
    }


    @Override
    public R<Boolean> handlerImport(List<Map<String, String>> list) {
        List<Org> userList = list.stream().map((map) -> {
            Org item = new Org();
            item.setRemarks(map.getOrDefault("描述", EMPTY));
            item.setName(map.getOrDefault("名称", EMPTY));
            item.setAbbreviation(map.getOrDefault("简称", EMPTY));
            item.setState(Convert.toBool(map.getOrDefault("状态", EMPTY)));
            return item;
        }).collect(Collectors.toList());

        return R.success(baseService.saveBatch(userList));
    }

    /**
     * 给机构分配角色
     *
     * @param roleUser 角色分配对象
     * @return 新增结果
     */
    @ApiOperation(value = "给机构分配角色", notes = "给机构分配角色")
    @PostMapping("/saveOrgRole")
    @SysLog("给机构分配角色")
    @PreAuth("hasAnyPermission('{}config')")
    public R<List<Long>> saveUserRole(@RequestBody RoleOrgSaveVO roleUser) {
        return success(roleOrgService.saveRoleOrg(roleUser));
    }

    /**
     * 查询机构的角色
     *
     * @param orgId 角色id
     * @return 查询结果
     */
    @ApiOperation(value = "查询机构的角色", notes = "查询机构的角色")
    @GetMapping("/findOrgRoleByOrgId")
    @SysLog("查询机构的角色")
    @PreAuth("hasAnyPermission('{}view')")
    public R<List<Long>> findRoleByOrgId(@RequestParam Long orgId) {
        return success(roleOrgService.findRoleByOrgId(orgId));
    }

}
