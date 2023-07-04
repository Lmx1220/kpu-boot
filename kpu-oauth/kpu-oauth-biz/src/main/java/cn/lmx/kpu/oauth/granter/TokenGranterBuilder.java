package cn.lmx.kpu.oauth.granter;

import cn.lmx.basic.exception.BizException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TokenGranterBuilder
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Component
public class TokenGranterBuilder {

    private final Map<String, TokenGranter> granterPool = new ConcurrentHashMap<>();

    public TokenGranterBuilder(Map<String, TokenGranter> granterPool) {
        granterPool.forEach(this.granterPool::put);
    }

    /**
     * 获取TokenGranter
     *
     * @param grantType 授权类型
     * @return ITokenGranter
     */
    public TokenGranter getGranter(String grantType) {
        TokenGranter tokenGranter = granterPool.get(grantType);
        if (tokenGranter == null) {
            throw new BizException("grantType 不支持，请传递正确的 grantType 参数");
        }
        return tokenGranter;
    }

}
