package cn.lmx.kpu.oauth.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.annotation.log.WebLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.exception.BizException;
import cn.lmx.kpu.base.entity.user.BaseOrg;
import cn.lmx.kpu.model.entity.system.SysUser;
import cn.lmx.kpu.model.vo.result.UserQuery;
import cn.lmx.kpu.oauth.biz.OauthUserBiz;
import cn.lmx.kpu.oauth.enumeration.GrantType;
import cn.lmx.kpu.oauth.granter.TokenGranterBuilder;
import cn.lmx.kpu.oauth.service.CaptchaService;
import cn.lmx.kpu.oauth.service.UserInfoService;
import cn.lmx.kpu.oauth.vo.result.DefUserInfoResultVO;
import cn.lmx.kpu.oauth.vo.result.LoginResultVO;
import cn.lmx.kpu.oauth.vo.result.OrgResultVO;
import cn.lmx.kpu.system.service.tenant.DefUserService;
import cn.lmx.kpu.system.vo.update.tenant.DefUserAvatarUpdateVO;
import cn.lmx.kpu.system.vo.update.tenant.DefUserBaseInfoUpdateVO;
import cn.lmx.kpu.system.vo.update.tenant.DefUserEmailUpdateVO;
import cn.lmx.kpu.system.vo.update.tenant.DefUserMobileUpdateVO;
import cn.lmx.kpu.system.vo.update.tenant.DefUserPasswordUpdateVO;
import cn.lmx.kpu.userinfo.biz.EmployeeHelperBiz;

import java.util.List;

/**
 * 认证Controller
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Slf4j
@RestController
@RequestMapping("/anyone")
@AllArgsConstructor
@Tag(name = "用户基本信息")
public class UserInfoController {

    private final OauthUserBiz oauthUserBiz;
    private final TokenGranterBuilder tokenGranterBuilder;
    private final UserInfoService userInfoService;
    private final DefUserService defUserService;
    private final CaptchaService captchaService;
    private final EmployeeHelperBiz employeeHelperBiz;

    /**
     * 单体查询用户
     *
     * @param query 参数
     * @return 查询结果
     */
    @Operation(summary = "查询用户详细", description = "查询用户详细：@LoginUser 注解专用")
    @PostMapping(value = "/getSysUserById")
    @Hidden
    public R<SysUser> getSysUserById(@RequestBody UserQuery query) {
        return R.success(employeeHelperBiz.getSysUserById(query));
    }

    /**
     * 获取当前登录的用户信息
     */
    @Operation(summary = "获取当前登录的用户信息", description = "获取当前登录的用户信息：登录后，查询用户信息")
    @GetMapping(value = "/getUserInfoById")
    public R<DefUserInfoResultVO> getUserInfoById(@RequestParam(required = false) Long userId) throws BizException {
        if (userId == null) {
            userId = ContextUtil.getUserId();
        }
        return R.success(oauthUserBiz.getUserById(userId));
    }


    @Operation(summary = "切换企业")
    @PutMapping("/switchTenantAndOrg")
    public R<LoginResultVO> switchOrg(@RequestParam(required = false) Long companyId,
                                      @RequestParam(required = false) Long deptId) {
        return R.success(tokenGranterBuilder.getGranter(GrantType.PASSWORD).switchOrg(companyId, deptId));
    }


    /**
     * 修改头像
     *
     * @param data 用户头像信息
     * @return 用户
     */
    @Operation(summary = "修改头像", description = "修改头像")
    @PutMapping("/avatar")
    @WebLog("'修改头像:' + #data.id")
    public R<Boolean> avatar(@RequestBody @Validated DefUserAvatarUpdateVO data) {
        return R.success(defUserService.updateAvatar(data));
    }

    /**
     * 修改密码
     *
     * @param data 修改实体
     * @return 是否成功
     */
    @Operation(summary = "修改密码", description = "修改密码")
    @PutMapping("/password")
    @WebLog("'修改密码:' + #data.id")
    public R<Boolean> updatePassword(@RequestBody @Validated DefUserPasswordUpdateVO data) {
        return R.success(defUserService.updatePassword(data));
    }

    /**
     * 修改手机
     *
     * @param data 修改实体
     * @return 是否成功
     */
    @Operation(summary = "修改手机", description = "修改手机")
    @PutMapping("/mobile")
    @WebLog("'修改手机:' + #data.mobile")
    public R<Boolean> updateMobile(@RequestBody @Validated DefUserMobileUpdateVO data) {
        R<Boolean> r = captchaService.checkCaptcha(data.getMobile(), data.getTemplateCode(), data.getCode());
        if (!r.getIsSuccess()) {
            return r;
        }
        return R.success(defUserService.updateMobile(data));
    }

    /**
     * 修改邮箱
     *
     * @param data 修改实体
     * @return 是否成功
     */
    @Operation(summary = "修改邮箱", description = "修改邮箱")
    @PutMapping("/email")
    @WebLog("'修改邮箱:' + #data.email")
    public R<Boolean> updateEmail(@RequestBody @Validated DefUserEmailUpdateVO data) {
        R<Boolean> r = captchaService.checkCaptcha(data.getEmail(), data.getTemplateCode(), data.getCode());
        if (!r.getIsSuccess()) {
            return r;
        }
        return R.success(defUserService.updateEmail(data));
    }

    /**
     * 修改个人信息
     *
     * @param data 用户基础信息
     * @return 用户
     */
    @Operation(summary = "修改基础信息")
    @PutMapping("/baseInfo")
    @WebLog(value = "'修改基础信息:' + #data?.id", request = false)
    public R<Boolean> updateBaseInfo(@RequestBody @Validated DefUserBaseInfoUpdateVO data) {
        return R.success(defUserService.updateBaseInfo(data));
    }


    @Operation(summary = "查询单位和部门")
    @GetMapping("/findCompanyDept")
    @WebLog(value = "根据租户ID查询单位和部门")
    public R<OrgResultVO> findCompanyDept() {
        return R.success(userInfoService.findCompanyAndDept());
    }

    @Operation(summary = "根据单位查询部门")
    @WebLog(value = "根据单位查询部门")
    @GetMapping("/findDeptByCompany")
    public R<List<BaseOrg>> findDeptByCompany(@RequestParam Long companyId) {
        return R.success(userInfoService.findDeptByCompany(companyId));
    }


}
