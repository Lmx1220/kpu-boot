package cn.lmx.kpu.boot.interceptor;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.context.ContextConstants;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.database.properties.MultiTenantType;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.exception.ForbiddenException;
import cn.lmx.basic.exception.UnauthorizedException;
import cn.lmx.basic.jwt.TokenUtil;
import cn.lmx.basic.jwt.model.AuthInfo;
import cn.lmx.basic.jwt.utils.JwtUtil;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.common.cache.common.TokenUserIdCacheKeyBuilder;
import cn.lmx.kpu.common.constant.BizConstant;
import cn.lmx.kpu.common.properties.IgnoreProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.lmx.basic.context.ContextConstants.*;
import static cn.lmx.basic.exception.code.ExceptionCode.JWT_NOT_LOGIN;
import static cn.lmx.basic.exception.code.ExceptionCode.JWT_OFFLINE;


/**
 * 网关：
 * 获取token，并解析，然后将所有的用户、应用信息封装到请求头
 * <p>
 * 拦截器：
 * 解析请求头数据， 将用户信息、应用信息封装到BaseContextHandler
 * 考虑请求来源是否网关（ip等）
 * <p>
 * Created by lmx on 2023/7/4 14:27
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
public class TokenHandlerInterceptor implements AsyncHandlerInterceptor {

    private final String profiles;
    private final IgnoreProperties ignoreTokenProperties;
    private final DatabaseProperties databaseProperties;
    private final TokenUtil tokenUtil;
    private final CacheOps cacheOps;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            log.info("not exec!!! url={}", request.getRequestURL());
            return true;
        }
        ContextUtil.setBoot(true);
        String traceId = IdUtil.fastSimpleUUID();
        ContextUtil.setPath(getHeader(ContextConstants.PATH_HEADER, request));
        MDC.put(ContextConstants.LOG_TRACE_ID, traceId);
        try {
            //1, 解码 请求头中的租户信息
            parseTenant(request);

            // 2,解码 Authorization 后面完善
            parseClient(request);

            // 3，解析token
            parseToken(request);
        } catch (UnauthorizedException | ForbiddenException e) {
            log.error("request={}", request.getRequestURL(), e);
            throw e;
        } catch (BizException e) {
            log.error("request={}", request.getRequestURL(), e);
            throw UnauthorizedException.wrap(e.getMessage());
        } catch (Exception e) {
            log.error("request={}", request.getRequestURL(), e);
            throw UnauthorizedException.wrap("验证token出错");
        }

        return true;
    }

    private boolean parseToken(HttpServletRequest request) {
        String uri = request.getRequestURI();
        // 忽略 token 认证的接口
        if (isIgnoreToken(uri)) {
            log.debug("access filter not execute");
            return true;
        }

        //获取token， 解析，然后想信息放入 heade
        //3, 获取token
        String token = getHeader(BEARER_HEADER_KEY, request);

        AuthInfo authInfo;
        //添加测试环境的特殊token
        if (isDev(token)) {
            authInfo = new AuthInfo().setAccount("kpu").setUserId(2L).setTokenType(BEARER_HEADER_KEY).setName("平台管理员");
        } else {
            authInfo = tokenUtil.getAuthInfo(token);

            // 5，验证 是否在其他设备登录或被挤下线
            String newToken = JwtUtil.getToken(token);
            CacheKey cacheKey = new TokenUserIdCacheKeyBuilder().key(newToken);
            String tokenCache = cacheOps.get(cacheKey);

            if (StrUtil.isEmpty(tokenCache)) {
                log.error("token is empty");
                throw UnauthorizedException.wrap(JWT_NOT_LOGIN.getMsg());
            } else if (StrUtil.equals(BizConstant.LOGIN_STATUS, tokenCache)) {
                log.error("您被踢了");
                throw UnauthorizedException.wrap(JWT_OFFLINE.getMsg());
            }
        }

        //6, 转换，将 token 解析出来的用户身份 和 解码后的tenant、Authorization 重新封装到请求头
        if (authInfo != null) {
            ContextUtil.setUserId(authInfo.getUserId());
            ContextUtil.setAccount(authInfo.getAccount());
            ContextUtil.setName(authInfo.getName());
            MDC.put(JWT_KEY_USER_ID, String.valueOf(authInfo.getUserId()));
        }
        return false;
    }

    private void parseClient(HttpServletRequest request) {
        String base64Authorization = getHeader(BASIC_HEADER_KEY, request);
        if (StrUtil.isNotEmpty(base64Authorization)) {
            String[] client = JwtUtil.getClient(base64Authorization);
            ContextUtil.setClientId(client[0]);
        }
    }

    /**
     * 忽略 租户编码
     *
     * @return
     */
    protected boolean isIgnoreTenant(String path) {
        return ignoreTokenProperties.isIgnoreTenant(path);
    }

    private void parseTenant(HttpServletRequest request) {
        if (MultiTenantType.NONE.eq(databaseProperties.getMultiTenantType())) {
            ContextUtil.setTenant(StrPool.EMPTY);
            MDC.put(JWT_KEY_TENANT, StrPool.EMPTY);
            return;
        }
        if (isIgnoreTenant(request.getRequestURI())) {
            return;
        }
        String base64Tenant = getHeader(JWT_KEY_TENANT, request);
        if (StrUtil.isNotEmpty(base64Tenant)) {
            String tenant = JwtUtil.base64Decoder(base64Tenant);
            ContextUtil.setTenant(tenant);
            MDC.put(JWT_KEY_TENANT, ContextUtil.getTenant());
        }
    }

    private String getHeader(String name, HttpServletRequest request) {
        String value = request.getHeader(name);
        if (StrUtil.isEmpty(value)) {
            value = request.getParameter(name);
        }
        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return URLUtil.decode(value);
    }


    protected boolean isDev(String token) {
        return !StrPool.PROD.equalsIgnoreCase(profiles) && (StrPool.TEST_TOKEN.equalsIgnoreCase(token) || StrPool.TEST.equalsIgnoreCase(token));
    }

    /**
     * 忽略应用级token
     *
     * @return
     */
    protected boolean isIgnoreToken(String uri) {
        return ignoreTokenProperties.isIgnoreToken(uri);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextUtil.remove();
    }

}
