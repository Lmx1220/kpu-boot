package cn.lmx.kpu.authority.service.auth;

import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.kpu.authority.dto.auth.ApplicationPageQuery;
import cn.lmx.kpu.authority.dto.auth.ApplicationResultVO;
import cn.lmx.kpu.authority.dto.auth.ApplicationSaveVO;
import cn.lmx.kpu.authority.dto.auth.ApplicationUpdateVo;
import cn.lmx.kpu.authority.entity.auth.Application;

/**
 * <p>
 * 业务接口
 * 应用
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface ApplicationService extends SuperCacheService<Long, Application, ApplicationSaveVO, ApplicationUpdateVo, ApplicationPageQuery, ApplicationResultVO> {
    /**
     * 根据 clientId 和 clientSecret 查询
     *
     * @param clientId 客户端id
     * @param clientSecret 客户端密钥
     * @return 应用
     */
    Application getByClient(String clientId, String clientSecret);
}
