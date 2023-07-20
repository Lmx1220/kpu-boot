package cn.lmx.kpu.oauth.event.model;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.log.util.AddressUtil;
import cn.lmx.kpu.authority.dto.auth.Online;
import cn.lmx.kpu.model.enumeration.base.LoginStatusEnum;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 登录状态DTO
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class LoginStatusDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    /***
     * 用户id
     */
    private Long id;
    /**
     * 账号
     */
    private String username;
    /**
     * 租户编码
     */
    private String tenant;
    /**
     * 登录类型
     */
    private LoginStatusEnum status;
    /**
     * 登录描述
     */
    private String description;

    /**
     * 登录浏览器
     */
    private String ua;
    /**
     * 登录IP
     */
    private String ip;
    /**
     * 登录地址
     */
    private String location;

    private Online online;

    public static LoginStatusDTO success(Long id, Online online) {
        LoginStatusDTO loginStatus = LoginStatusDTO.builder()
                .id(id).tenant(ContextUtil.getTenant())
                .status(LoginStatusEnum.SUCCESS).description("登录成功")
                .build().setInfo();
        online.setLoginIp(loginStatus.getIp());
        online.setLocation(loginStatus.getLocation());
        loginStatus.setOnline(online);
        return loginStatus;
    }

    public static LoginStatusDTO accountError(Long id, String description) {
        return LoginStatusDTO.builder()
                .id(id).tenant(ContextUtil.getTenant())
                .status(LoginStatusEnum.ACCOUNT_ERROR).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO captchaError(String username, String description) {
        return LoginStatusDTO.builder()
                .username(username).tenant(ContextUtil.getTenant())
                .status(LoginStatusEnum.CAPTCHA_ERROR).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO pwdError(Long id, String description) {
        return LoginStatusDTO.builder()
                .id(id).tenant(ContextUtil.getTenant())
                .status(LoginStatusEnum.PWD_ERROR).description(description)
                .build().setInfo();
    }

    private LoginStatusDTO setInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return this;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if (request == null) {
            return this;
        }
        String tempUa = StrUtil.sub(request.getHeader("user-agent"), 0, 500);
        String tempIp = ServletUtil.getClientIP(request);
        String tempLocation = AddressUtil.getRegion(tempIp);
        this.ua = tempUa;
        this.ip = tempIp;
        this.location = tempLocation;
        return this;
    }

}
