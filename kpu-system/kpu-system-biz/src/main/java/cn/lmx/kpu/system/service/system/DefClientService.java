package cn.lmx.kpu.system.service.system;

import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.kpu.system.entity.system.DefClient;

/**
 * <p>
 * 业务接口
 * 客户端
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefClientService extends SuperCacheService<Long, DefClient> {

    /**
     * 根据 客户端id 和 客户端秘钥查询应用
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    DefClient getClient(String clientId, String clientSecret);
}
