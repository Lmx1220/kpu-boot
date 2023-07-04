package cn.lmx.kpu.oauth.service;

import cn.lmx.basic.base.R;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 验证码
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface ValidateCodeService {

    /**
     * 生成验证码
     *
     * @param key      验证码 uuid
     * @param response HttpServletResponse
     * @throws IOException 异常
     */
    void create(String key, HttpServletResponse response) throws IOException;

    /**
     * 校验验证码
     *
     * @param key   前端上送 key
     * @param value 前端上送待校验值
     * @return 是否成功
     */
    R<Boolean> check(String key, String value);
}
