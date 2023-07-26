package cn.lmx.kpu.authority.controller.auth;

import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.database.mybatis.conditions.query.QueryWrap;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.authority.dto.auth.*;
import cn.lmx.kpu.authority.entity.auth.Role;
import cn.lmx.kpu.authority.entity.auth.RoleAuthority;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.service.auth.RoleAuthorityService;
import cn.lmx.kpu.authority.service.auth.RoleService;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import cn.lmx.kpu.common.constant.BizConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 角色
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/role")
@Api(value = "Role", tags = "角色")
@PreAuth(replace = "authority:role:")
@RequiredArgsConstructor
public class RoleController extends SuperCacheController<RoleService, Long, Role, RoleSaveVO, RoleUpdateVo, RolePageQuery, RoleResultVO> {

    private final RoleAuthorityService roleAuthorityService;
    private final EchoService echoService;
    private final UserRoleService userRoleService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public IPage<Role> query(PageParams<RolePageQuery> params) {

        IPage<Role> page = params.buildPage(Role.class);
        RolePageQuery roleQuery = params.getModel();

        QueryWrap<Role> wrap = handlerWrapper(null, params);

        LbqWrapper<Role> wrapper = wrap.lambda();

        wrapper.like(Role::getName, roleQuery.getName())
                .like(Role::getCode, roleQuery.getCode())
                .eq(Role::getState, roleQuery.getState())
                .in(Role::getReadonly, roleQuery.getReadonly())
                .eq(Role::getCategory, roleQuery.getCategory());
        baseService.page(page, wrapper);
        return page;
    }

    @ApiOperation(value = "分页查询角色", notes = "分页查询角色")
    @PostMapping("/myPage")
    @SysLog(value = "'分页列表查询:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<IPage<RoleResultVO>> myPage(@RequestBody @Validated PageParams<RolePageQuery> params) {
        IPage<Role> page = params.buildPage(Role.class);
        RolePageQuery roleQuery = params.getModel();

        QueryWrap<Role> wrap = handlerWrapper(null, params);

        LbqWrapper<Role> wrapper = wrap.lambda();
        wrapper.like(Role::getName, roleQuery.getName())
                .like(Role::getCode, roleQuery.getCode())
                .eq(Role::getState, roleQuery.getState())
                .in(Role::getReadonly, roleQuery.getReadonly())
                .eq(Role::getCategory, roleQuery.getCategory());
        if (roleQuery.getScopeType() != null && StrUtil.equalsAny(roleQuery.getScopeType(), "1", "2")) {
            if (StrUtil.equalsAny(roleQuery.getScope(), BizConstant.SCOPE_BIND, BizConstant.SCOPE_UN_BIND)) {
                if (roleQuery.getScopeType().equals("1") && roleQuery.getUserId() != null) {
                    String sql = " select ura.role_id from c_user_role ura where ura.role_id = c_role.id \n" +
                            "  and ura.user_id =   " + roleQuery.getUserId();
                    if (BizConstant.SCOPE_BIND.equals(roleQuery.getScope())) {
                        wrapper.inSql(Role::getId, sql);
                    } else {
                        wrapper.notInSql(Role::getId, sql);
                    }
                }
                if (roleQuery.getScopeType().equals("2") && roleQuery.getOrgId() != null) {
                    String sql = " select roa.role_id from c_role_org roa where roa.role_id = c_role.id \n" +
                            "  and roa.org_id =   " + roleQuery.getOrgId();
                    if (BizConstant.SCOPE_BIND.equals(roleQuery.getScope())) {
                        wrapper.inSql(Role::getId, sql);
                    } else {
                        wrapper.notInSql(Role::getId, sql);
                    }
                }
            }
        }
        baseService.page(page, wrapper);
        IPage<RoleResultVO> voPage = BeanPlusUtil.toBeanPage(page, getResultVOClass());
        handlerResult(voPage);
        return R.success(voPage);
    }

    /**
     * 查询角色
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询角色", notes = "查询角色")
    @GetMapping("/details")
    @SysLog("查询角色")
    public R<RoleQueryDTO> getDetails(@RequestParam Long id) {
        Role role = baseService.getByIdCache(id);
        RoleQueryDTO query = BeanPlusUtil.toBean(role, RoleQueryDTO.class);
        return success(query);
    }

    @ApiOperation(value = "检测角色编码", notes = "检测角色编码")
    @GetMapping("/check")
    @SysLog("检测角色编码")
    public R<Boolean> check(@RequestParam String code) {
        return success(baseService.check(code));
    }


    @Override
    public R<Role> handlerSave(RoleSaveVO data) {
        baseService.saveRole(data, getUserId());
        return success(BeanPlusUtil.toBean(data, Role.class));
    }

    @Override
    public R<Role> handlerUpdate(RoleUpdateVo data) {
        baseService.updateRole(data, getUserId());
        return success(BeanPlusUtil.toBean(data, Role.class));
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return success(baseService.removeByIdWithCache(ids));
    }


    /**
     * 查询角色的用户
     *
     * @param roleId 角色id
     * @return 新增结果
     */
    @ApiOperation(value = "查询角色的用户", notes = "查询角色的用户")
    @GetMapping("/userList")
    @SysLog("查询角色的用户")
    @PreAuth("hasAnyPermission('{}view')")
    public R<List<Long>> findUserIdByRoleId(@RequestParam Long roleId) {
        List<UserRole> list = userRoleService.list(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
        return success(list.stream().mapToLong(UserRole::getUserId).boxed().collect(Collectors.toList()));
    }

    /**
     * 给用户分配角色给角色绑定用户
     *
     * @param roleUser 用户角色授权对象
     * @return 新增结果
     */
    @ApiOperation(value = "给角色绑定用户", notes = "给角色绑定用户")
    @PostMapping("/saveRoleUser")
    @SysLog("给角色绑定用户")
    @PreAuth("hasAnyPermission('{}config')")
    public R<List<Long>> saveUserRole(@RequestBody RoleUserSaveVO roleUser) {
        return success(roleAuthorityService.saveRoleUser(roleUser));
    }

    /**
     * 查询角色拥有的资源id
     *
     * @param roleId 角色id
     * @return 新增结果
     */
    @ApiOperation(value = "查询角色拥有的资源id集合", notes = "查询角色拥有的资源id集合")
    @GetMapping("/resourceList")
    @SysLog("查询角色拥有的资源")
    @PreAuth("hasAnyPermission('{}view')")
    public R<List<Long>> findResourceListByRoleId(@RequestParam Long roleId) {
        List<RoleAuthority> list = roleAuthorityService.list(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, roleId));
        List<Long> menuIdList = list.stream().mapToLong(RoleAuthority::getAuthorityId).boxed().collect(Collectors.toList());

        return success(menuIdList);
    }


    /**
     * 给角色配置权限
     *
     * @param roleResourceSaveVO 角色权限授权对象
     * @return 新增结果
     */
    @ApiOperation(value = "给角色配置权限", notes = "给角色配置权限")
    @PostMapping("/saveResource")
    @SysLog("给角色配置权限")
    @PreAuth("hasAnyPermission('{}auth')")
    public R<Boolean> saveRoleAuthority(@RequestBody RoleResourceSaveVO roleResourceSaveVO) {
        return success(roleAuthorityService.saveRoleAuthority(roleResourceSaveVO));
    }


    /**
     * 根据角色编码查询用户ID
     *
     * @param codes 编码集合
     * @return 查询结果
     */
    @ApiOperation(value = "根据角色编码查询用户ID", notes = "根据角色编码查询用户ID")
    @GetMapping("/codes")
    @SysLog("根据角色编码查询用户ID")
    public R<List<Long>> findUserIdByCode(@RequestParam(value = "codes") String[] codes) {
        return success(baseService.findUserIdByCode(codes));
    }

}
