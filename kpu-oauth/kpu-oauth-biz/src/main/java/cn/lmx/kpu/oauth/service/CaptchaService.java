package cn.lmx.kpu.oauth.service;

import jakarta.servlet.http.HttpServletResponse;
import cn.lmx.basic.base.R;

import java.io.IOException;


/**
 * 验证码
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @param key      验证码 uuid
     * @param response HttpServletResponse
     * @throws IOException 异常
     */
    void createImg(String key, HttpServletResponse response) throws IOException;

    /**
     * 校验验证码
     *
     * @param key          邮件、手机号、唯一字符串(图片验证码) 等
     * @param templateCode 模板
     *                     key为邮件、手机号时，templateCode = 在「运营平台」-「消息模板」-「模板标识」配置一个模板
     *                     key为唯一字符串，templateCode = CAPTCHA
     * @param value        前端上送待校验值
     * @return 是否成功
     */
    R<Boolean> checkCaptcha(String key, String templateCode, String value);

    /**
     * 发送短信验证码
     *
     * @param mobile       手机号
     * @param templateCode 模板标识
     *                     需要在「运营平台」-「消息模板」-「模板标识」配置一个短信模板
     * @return cn.lmx.basic.base.R<java.lang.Boolean>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    R<Boolean> sendSmsCode(String mobile, String templateCode);

    /**
     * 发送邮箱验证码
     *
     * @param email        邮箱
     * @param templateCode 模板标识
     *                     需要在「运营平台」-「消息模板」-「模板标识」配置一个短信模板
     * @return cn.lmx.basic.base.R<java.lang.Boolean>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    R<Boolean> sendEmailCode(String email, String templateCode);

}
