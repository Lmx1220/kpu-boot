package cn.lmx.kpu.security.aspect;


import cn.hutool.core.collection.CollUtil;
import cn.lmx.basic.base.R;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.security.constant.RoleConstant;
import cn.lmx.kpu.security.permission.AuthorizingRealm;
import cn.lmx.kpu.security.properties.SecurityProperties;
import cn.lmx.kpu.userinfo.feign.UserQuery;
import cn.lmx.kpu.userinfo.feign.UserResolverService;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限判断
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public class VerifyAuthFunction {
    private final UserResolverService userResolverService;
    private final SecurityProperties securityProperties;

    public VerifyAuthFunction(UserResolverService userResolverService, SecurityProperties securityProperties) {
        this.userResolverService = userResolverService;
        this.securityProperties = securityProperties;
    }

    /**
     * 放行请求
     *
     * @return {boolean}
     */
    public boolean permit() {
        return true;
    }

    /**
     * 只有超管角色才可访问
     *
     * @return {boolean}
     */
    public boolean denyAll() {
        return hasAnyRole(RoleConstant.SUPER_ADMIN);
    }

    /**
     * 判断是否有任意 资源
     * <p>
     * 等价于前端自定义指令：v-hasAnyPermission、v-has-any-permission
     *
     * @param permit 多个资源编码
     * @return {boolean}
     */
    public boolean hasAnyPermission(String... permit) {
        // 查询当前用户拥有的所有资源
        Set<String> resources = getAllResources();
        // 判断是否包含所需的角色
        return AuthorizingRealm.hasAnyPermission(resources, permit, securityProperties.getCaseSensitive());
    }

    /**
     * 判断是否有任意 资源
     * <p>
     * 等价于前端自定义指令： v-hasNoPermission、v-has-no-permission
     *
     * @param permit 多个资源编码
     * @return {boolean}
     */
    public boolean hasNoPermission(String... permit) {
        // 查询当前用户拥有的所有资源
        Set<String> resources = getAllResources();
        // 判断是否包含所需的角色
        return AuthorizingRealm.hasNoPermission(resources, permit, securityProperties.getCaseSensitive());
    }

    /**
     * 判断是否包含所有资源
     * <p>
     * 等价于前端自定义指令： v-hasPermission、v-has-permission
     *
     * @param permit 多个资源编码
     * @return {boolean}
     */
    public boolean hasPermission(String... permit) {
        Set<String> resources = getAllResources();
        return AuthorizingRealm.hasAllPermission(resources, permit, securityProperties.getCaseSensitive());
    }

    /**
     * 远程查询当前登录的用户拥有那些权限
     *
     * @return
     */
    @NonNull
    private Set<String> getAllResources() {
        // 查询当前用户拥有的所有资源
        Set<String> resources = new HashSet<>();

        R<SysUser> result = userResolverService.getById(UserQuery.buildResource().setUserId(ContextUtil.getUserId()));
        if (result.getIsSuccess() && result.getData() != null && result.getData() != null) {
            SysUser sysUser = result.getData();
            resources = new HashSet<>(sysUser.getResources());
        }
        return resources;
    }

    /**
     * 判断是否包含任意角色
     *
     * @param role 角色集合
     * @return {boolean}
     */
    public boolean hasAnyRole(String... role) {
        Set<String> roles = getAllRoles();

        // 判断是否包含所需的角色
        return CollUtil.containsAny(roles, CollUtil.newHashSet(role));
    }

    /**
     * 判断是否拥有所有的角色编码
     *
     * @param role 角色集合
     * @return {boolean}
     */
    public boolean hasRole(String... role) {
        Set<String> roles = getAllRoles();

        // 判断是否包含所需的角色
        return CollUtil.containsAll(roles, CollUtil.newHashSet(role));
    }

    /**
     * 判断是否没有所有的角色编码
     *
     * @param role 角色集合
     * @return 是否不含所有角色
     */
    public boolean hasNoRole(String... role) {
        return !hasRole(role);
    }

    @NonNull
    private Set<String> getAllRoles() {
        // 查询当前用户拥有的所有角色
        Set<String> roles = new HashSet<>();

        R<SysUser> result = userResolverService.getById(UserQuery.buildRoles().setUserId(ContextUtil.getUserId()));
        if (result.getIsSuccess() && result.getData() != null && result.getData().getRoles() != null) {
            SysUser sysUser = result.getData();
            roles = sysUser.getRoles().stream().collect(Collectors.toSet());
        }
        return roles;
    }
}
