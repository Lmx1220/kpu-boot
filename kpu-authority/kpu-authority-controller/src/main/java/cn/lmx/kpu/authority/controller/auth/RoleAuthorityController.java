package cn.lmx.kpu.authority.controller.auth;


import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.authority.entity.auth.RoleAuthority;
import cn.lmx.kpu.authority.service.auth.RoleAuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 角色的资源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roleAuthority")
@Api(value = "RoleAuthority", tags = "角色的资源")
@RequiredArgsConstructor
public class RoleAuthorityController {

    private final RoleAuthorityService roleAuthorityService;

    /**
     * 查询指定角色关联的菜单和资源
     *
     * @param roleId 角色id
     * @return 查询结果
     */
    @ApiOperation(value = "查询指定角色关联的菜单和资源", notes = "查询指定角色关联的菜单和资源")
    @GetMapping("/{roleId}")
    @SysLog(value = "'查询指定角色关联的菜单和资源", response = false)
    public R<List<RoleAuthority>> queryByRoleId(@PathVariable Long roleId) {
        return R.success(roleAuthorityService.list(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, roleId)));
    }


}
