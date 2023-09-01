package cn.lmx.kpu.authority.manager.auth;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.authority.entity.auth.Application;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/26  19:05
 */
public interface ApplicationManager extends SuperCacheManager<Application> {
    /**
     * 根据 clientId 和 clientSecret 查询
     *
     * @param clientId     客户端id
     * @param clientSecret 客户端密钥
     * @return 应用
     */
    Application getByClient(String clientId, String clientSecret);
}
