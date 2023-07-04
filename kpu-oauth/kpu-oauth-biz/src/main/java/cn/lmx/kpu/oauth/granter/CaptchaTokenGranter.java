package cn.lmx.kpu.oauth.granter;

import cn.lmx.basic.base.R;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.jwt.TokenUtil;
import cn.lmx.basic.jwt.model.AuthInfo;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.kpu.authority.dto.auth.LoginParamDTO;
import cn.lmx.kpu.authority.service.auth.ApplicationService;
import cn.lmx.kpu.authority.service.auth.OnlineService;
import cn.lmx.kpu.authority.service.auth.UserService;
import cn.lmx.kpu.common.properties.SystemProperties;
import cn.lmx.kpu.file.service.AppendixService;
import cn.lmx.kpu.oauth.event.LoginEvent;
import cn.lmx.kpu.oauth.event.model.LoginStatusDTO;
import cn.lmx.kpu.oauth.service.ValidateCodeService;
import cn.lmx.kpu.tenant.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static cn.lmx.kpu.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

/**
 * 验证码TokenGranter
 *
 * @author lmx
 */
@Component(GRANT_TYPE)
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "captcha";
    private final ValidateCodeService validateCodeService;

    public CaptchaTokenGranter(TokenUtil tokenUtil, UserService userService, AppendixService appendixService,
                               TenantService tenantService, ApplicationService applicationService,
                               DatabaseProperties databaseProperties, ValidateCodeService validateCodeService,
                               OnlineService onlineService, SystemProperties systemProperties) {
        super(tokenUtil, userService, tenantService, applicationService, databaseProperties,
                onlineService, systemProperties, appendixService);
        this.validateCodeService = validateCodeService;
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        if (systemProperties.getVerifyCaptcha()) {
            R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
            if (!check.getIsSuccess()) {
                String msg = check.getMsg();
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
                throw BizException.validFail(check.getMsg());
            }
        }

        return login(loginParam);
    }

}
