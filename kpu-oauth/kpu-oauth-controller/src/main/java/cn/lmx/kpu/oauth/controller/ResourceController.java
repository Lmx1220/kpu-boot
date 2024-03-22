package cn.lmx.kpu.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.annotation.user.LoginUser;
import cn.lmx.basic.base.R;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.kpu.base.vo.result.user.VueRouter;
import cn.lmx.kpu.common.properties.IgnoreProperties;
import cn.lmx.kpu.model.entity.system.SysUser;
import cn.lmx.kpu.oauth.biz.ResourceBiz;
import cn.lmx.kpu.oauth.vo.result.VisibleResourceVO;

import java.util.Collections;
import java.util.List;

import static cn.lmx.kpu.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static cn.lmx.kpu.common.constant.SwaggerConstants.DATA_TYPE_STRING;


/**
 * <p>
 * 前端控制器
 * 资源 角色 菜单
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@RestController
@RequestMapping("/anyone")
@AllArgsConstructor
@Tag(name = "资源-菜单-应用")
@EnableConfigurationProperties({IgnoreProperties.class})
public class ResourceController {
    private final IgnoreProperties ignoreProperties;
    private final ResourceBiz oauthResourceBiz;


    /**
     * 查询用户可用的所有资源
     *
     * @param applicationId 应用id
     * @param employeeId    当前登录人id
     */
    @Operation(summary = "查询用户可用的所有资源", description = "根据员工ID和应用ID查询员工在某个应用下可用的资源")
    @GetMapping("/visible/resource")
    public R<VisibleResourceVO> visible(@Parameter(hidden = true) @LoginUser SysUser sysUser,
//                                        @RequestParam(value = "type", required = false) ClientTypeEnum type,
                                        @RequestParam(value = "employeeId", required = false) Long employeeId,
                                        @RequestParam(value = "applicationId", required = false) Long applicationId,
                                        @RequestParam(value = "subGroup", required = false) String subGroup){
        if (employeeId == null || employeeId <= 0) {
            employeeId = sysUser.getEmployeeId();
        }
        return R.success(VisibleResourceVO.builder()
                .enabled(ignoreProperties.getAuthEnabled())
                .caseSensitive(ignoreProperties.getCaseSensitive())
                .roleList(Collections.singletonList("PT_ADMIN"))
                .resourceList(oauthResourceBiz.findVisibleResource(employeeId, applicationId))
                .routerList(
                        applicationId == null ? oauthResourceBiz.findAllVisibleRouter(employeeId, subGroup) : oauthResourceBiz.findVisibleRouter(applicationId, employeeId, subGroup)
                )
                .build());
    }

    @Operation(summary = "查询用户可用的所有资源", description = "查询用户可用的所有资源")
    @GetMapping("/findVisibleResource")
    public R<List<String>> visibleResource(@RequestParam(value = "employeeId") Long employeeId,
                                           @RequestParam(value = "applicationId", required = false) Long applicationId) {
        return R.success(oauthResourceBiz.findVisibleResource(employeeId, applicationId));
    }

    /**
     * 检查员工是否有指定uri的访问权限
     *
     * @param path   请求路径
     * @param method 请求方法
     */
    @Operation(summary = "检查员工是否有指定uri的访问权限", description = "检查员工是否有指定uri的访问权限")
    @GetMapping("/checkUri")
    public R<Boolean> checkUri(@RequestParam String path, @RequestParam String method) {
        long apiStart = System.currentTimeMillis();
        Boolean check = oauthResourceBiz.checkUri(path, method);
        long apiEnd = System.currentTimeMillis();
        log.info("controller 校验api权限:{} - {}  耗时:{}", path, method, (apiEnd - apiStart));
        return R.success(check);
    }

    /**
     * 检测员工是否拥有指定应用的权限
     *
     * @param applicationId 应用id
     */
    @Operation(summary = "检测员工是否拥有指定应用的权限", description = "检测员工是否拥有指定应用的权限")
    @GetMapping("/checkEmployeeHaveApplication")
    public R<Boolean> checkEmployeeHaveApplication(@RequestParam Long applicationId) {
        return R.success(oauthResourceBiz.checkEmployeeHaveApplication(ContextUtil.getEmployeeId(), applicationId));
    }

    @Parameters({
            @Parameter(name = "subGroup", description = "菜单组", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
            @Parameter(name = "employeeId", description = "模板编码: 在「运营平台」-「消息模板」-「模板标识」配置一个邮件模板", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
            @Parameter(name = "applicationId", description = "应用id", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
    })
    @Operation(summary = "查询用户可用的所有菜单路由树", description = "根据员工ID和应用ID查询员工在某个应用下可用的菜单路由树")
    @GetMapping("/visible/router")
    public R<List<VueRouter>> myRouter(@RequestParam Long applicationId,
                                       @RequestParam(value = "subGroup", required = false) String subGroup,
                                       @RequestParam(value = "employeeId", required = false) Long employeeId,
                                       @Parameter(hidden = true) @LoginUser SysUser sysUser) {
        if (employeeId == null || employeeId <= 0) {
            employeeId = sysUser.getEmployeeId();
        }
        return R.success(oauthResourceBiz.findVisibleRouter(applicationId, employeeId, subGroup));
    }

    @Parameters({
            @Parameter(name = "subGroup", description = "菜单组", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
            @Parameter(name = "employeeId", description = "模板编码: 在「运营平台」-「消息模板」-「模板标识」配置一个邮件模板", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
    })
    @Operation(summary = "查询用户所有应用的可用路由树", description = "查询用户所有应用的可用路由树")
    @GetMapping("/visible/allRouter")
    public R<List<VueRouter>> allRouter(@RequestParam(value = "subGroup", required = false) String subGroup,
                                        @RequestParam(value = "employeeId", required = false) Long employeeId,
                                        @Parameter(hidden = true) @LoginUser SysUser sysUser) {
        if (employeeId == null || employeeId <= 0) {
            employeeId = sysUser.getEmployeeId();
        }
        return R.success(oauthResourceBiz.findAllVisibleRouter(employeeId, subGroup));
    }

}
