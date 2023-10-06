package cn.lmx.kpu.tenant.manager;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.kpu.model.enumeration.system.TenantStatusEnum;
import cn.lmx.kpu.tenant.dto.TenantConnectDTO;
import cn.lmx.kpu.tenant.dto.TenantSaveVO;
import cn.lmx.kpu.tenant.dto.TenantUpdateVo;
import cn.lmx.kpu.tenant.entity.Tenant;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 企业
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface TenantManager extends SuperCacheManager<Tenant> {
    /**
     * 检测 租户编码是否存在
     *
     * @param tenantCode 租户编码
     * @return 是否存在
     */
    boolean check(String tenantCode);

    /**
     * 保存
     *
     * @param data 租户保存数据
     * @return 租户
     */
    Tenant save(TenantSaveVO data);

    /**
     * 修改
     *
     * @param model 租户保存数据
     * @return 租户
     */
    Tenant update(TenantUpdateVo model);

    /**
     * 根据编码获取
     *
     * @param tenant 租户编码
     * @return 租户
     */
    Tenant getByCode(String tenant);

    /**
     * 通知所有服务链接数据源
     *
     * @param tenantConnect 链接信息
     * @return 是否链接成功
     */
    Boolean connect(TenantConnectDTO tenantConnect);


    /**
     * 删除租户数据
     *
     * @param ids id
     * @return 是否成功
     */
    Boolean delete(List<Long> ids);

    /**
     * 删除租户和基础数据
     *
     * @param ids id
     * @return 是否成功
     */
    Boolean deleteAll(List<Long> ids);

    /**
     * 查询所有可用的租户
     *
     * @return 租户信息
     */
    List<Tenant> find();

    /**
     * 修改租户状态
     *
     * @param ids
     * @param status
     * @return
     */
    Boolean updateStatus(List<Long> ids, TenantStatusEnum status);
}
