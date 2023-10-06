package cn.lmx.kpu.tenant.service.impl;

import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.common.constant.DsConstants;
import cn.lmx.kpu.model.enumeration.system.TenantStatusEnum;
import cn.lmx.kpu.tenant.dto.*;
import cn.lmx.kpu.tenant.entity.Tenant;
import cn.lmx.kpu.tenant.manager.TenantManager;
import cn.lmx.kpu.tenant.service.TenantService;
import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 企业
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service
@DS(DsConstants.DEFAULTS)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TenantServiceImpl extends SuperCacheServiceImpl<TenantManager, Long, Tenant, TenantSaveVO,
        TenantUpdateVo, TenantPageQuery, TenantResultVO> implements TenantService {
    /**
     * tenant_name:{tenantCode} -> id 只存租户的id，然后根据id再次查询缓存，这样子的好处是，删除或者修改租户信息时，只需要根据id淘汰缓存即可
     * 缺点就是 每次查询，需要多查一次缓存
     *
     * @param tenant
     * @return
     */
    @Override
    public Tenant getByCode(String tenant) {
        return superManager.getByCode(tenant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant save(TenantSaveVO data) {
        return superManager.save(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant update(TenantUpdateVo model) {
        return superManager.update(model);
    }

    @Override
    public boolean check(String tenantCode) {
        return superManager.check(tenantCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean connect(TenantConnectDTO tenantConnect) {
        return superManager.connect(tenantConnect);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<Long> ids) {
        return superManager.delete(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAll(List<Long> ids) {
        return superManager.deleteAll(ids);
    }

    @Override
    public List<Tenant> find() {
        return list(Wraps.<Tenant>lbQ().eq(Tenant::getStatus, TenantStatusEnum.NORMAL));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStatus(List<Long> ids, TenantStatusEnum status) {

        return superManager.updateStatus(ids, status);
    }
}
