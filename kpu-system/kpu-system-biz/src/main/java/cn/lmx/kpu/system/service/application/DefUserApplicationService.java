package cn.lmx.kpu.system.service.application;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.system.entity.application.DefUserApplication;

/**
 * <p>
 * 业务接口
 * 用户的默认应用
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefUserApplicationService extends SuperService<Long, DefUserApplication> {

    /**
     * 查询用户设置的默认应用
     *
     * @param userId 用户id
     * @return
     */
    Long getMyDefAppByUserId(Long userId);
}
