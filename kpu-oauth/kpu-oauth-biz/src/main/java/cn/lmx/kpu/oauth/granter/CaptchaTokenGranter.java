package cn.lmx.kpu.oauth.granter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import cn.lmx.basic.base.R;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.basic.utils.StrHelper;
import cn.lmx.kpu.oauth.event.LoginEvent;
import cn.lmx.kpu.oauth.event.model.LoginStatusDTO;
import cn.lmx.kpu.oauth.service.CaptchaService;
import cn.lmx.kpu.oauth.vo.param.LoginParamVO;
import cn.lmx.kpu.oauth.vo.result.LoginResultVO;
import cn.lmx.kpu.system.enumeration.system.LoginStatusEnum;

import static cn.lmx.kpu.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

/**
 * 验证码TokenGranter
 *
 * @author lmx
 */
@Component(GRANT_TYPE)
@Slf4j
@RequiredArgsConstructor
public class CaptchaTokenGranter extends PasswordTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "CAPTCHA";
    private final CaptchaService captchaService;

    @Override
    protected R<LoginResultVO> checkCaptcha(LoginParamVO loginParam) {
        if (systemProperties.getVerifyCaptcha()) {
            R<Boolean> check = captchaService.checkCaptcha(loginParam.getKey(), GRANT_TYPE, loginParam.getCode());
            if (!check.getIsSuccess()) {
                String msg = check.getMsg();
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getUsername(), LoginStatusEnum.CAPTCHA_ERROR, msg)));
                throw BizException.validFail(check.getMsg());
            }
        }
        return R.success(null);
    }

    @Override
    public R<LoginResultVO> checkParam(LoginParamVO loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StrHelper.isAnyBlank(username, password)) {
            return R.fail("请输入用户名或密码");
        }
        if (StrHelper.isAnyBlank(loginParam.getCode(), loginParam.getKey())) {
            return R.fail("请输入验证码");
        }

        return R.success(null);
    }

}
