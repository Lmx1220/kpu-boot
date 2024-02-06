package cn.lmx.kpu.system.manager.system;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.system.entity.system.DefClient;

/**
 * <p>
 * 通用业务接口
 * 客户端
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefClientManager extends SuperCacheManager<DefClient> {
    /**
     * 根据 客户端id 和 客户端秘钥查询应用
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    DefClient getClient(String clientId, String clientSecret);
}
