package cn.lmx.kpu.tenant.manager.impl;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.common.cache.tenant.TenantCacheKeyBuilder;
import cn.lmx.kpu.common.cache.tenant.TenantCodeCacheKeyBuilder;
import cn.lmx.kpu.file.service.AppendixService;
import cn.lmx.kpu.model.enumeration.system.TenantConnectTypeEnum;
import cn.lmx.kpu.model.enumeration.system.TenantStatusEnum;
import cn.lmx.kpu.model.enumeration.system.TenantTypeEnum;
import cn.lmx.kpu.tenant.dao.TenantMapper;
import cn.lmx.kpu.tenant.dto.TenantConnectDTO;
import cn.lmx.kpu.tenant.dto.TenantSaveVO;
import cn.lmx.kpu.tenant.dto.TenantUpdateVo;
import cn.lmx.kpu.tenant.entity.Tenant;
import cn.lmx.kpu.tenant.manager.TenantManager;
import cn.lmx.kpu.tenant.service.TenantService;
import cn.lmx.kpu.tenant.strategy.InitSystemContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

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
@RequiredArgsConstructor
public class TenantManagerImpl extends SuperCacheManagerImpl<TenantMapper, Tenant> implements TenantManager {

    private final InitSystemContext initSystemContext;
    private final AppendixService appendixService;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new TenantCacheKeyBuilder();
    }


    /**
     * tenant_name:{tenantCode} -> id 只存租户的id，然后根据id再次查询缓存，这样子的好处是，删除或者修改租户信息时，只需要根据id淘汰缓存即可
     * 缺点就是 每次查询，需要多查一次缓存
     *
     * @param tenant
     * @return
     */
    @Override
    public Tenant getByCode(String tenant) {
        Function<CacheKey, Object> loader = (k) ->
                getObj(Wraps.<Tenant>lbQ().select(Tenant::getId).eq(Tenant::getCode, tenant), Convert::toLong);
        CacheKey cacheKey = new TenantCodeCacheKeyBuilder().key(tenant);
        return getByKey(cacheKey, loader);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant save(TenantSaveVO data) {
        // defaults 库
        ArgumentAssert.isFalse(check(data.getCode()), "编码重复，请重新输入");

        // 1， 保存租户 (默认库)
        Tenant tenant = BeanPlusUtil.toBean(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.WAIT_INIT);
        tenant.setType(TenantTypeEnum.CREATE);
        tenant.setConnectType(TenantConnectTypeEnum.SYSTEM);
        // defaults 库
        save(tenant);
        appendixService.save(tenant.getId(), data.getLogos());

        CacheKey cacheKey = new TenantCodeCacheKeyBuilder().key(tenant.getCode());
        cacheOps.set(cacheKey, tenant.getId());
        return tenant;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant update(TenantUpdateVo model) {
        Tenant tenant = BeanPlusUtil.toBean(model, Tenant.class);
        super.updateById(tenant);
        appendixService.save(tenant.getId(), model.getLogos());
        return tenant;
    }

    @Override
    public boolean check(String tenantCode) {
        return super.count(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean connect(TenantConnectDTO tenantConnect) {
        return initSystemContext.initConnect(tenantConnect) && updateTenantStatus(tenantConnect);
    }

    private Boolean updateTenantStatus(TenantConnectDTO tenantConnect) {
        Boolean flag = this.update(Wraps.<Tenant>lbU()
                .set(Tenant::getStatus, TenantStatusEnum.NORMAL)
                .set(Tenant::getConnectType, tenantConnect.getConnectType())
                .eq(Tenant::getId, tenantConnect.getId()));
        delCache(tenantConnect.getId());
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<Long> ids) {
        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        appendixService.removeByBizId(ids);
        return removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAll(List<Long> ids) {
        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        appendixService.removeByBizId(ids);
        removeByIds(ids);
        return initSystemContext.delete(ids, tenantCodeList);
    }

    @Override
    public List<Tenant> find() {
        return list(Wraps.<Tenant>lbQ().eq(Tenant::getStatus, TenantStatusEnum.NORMAL));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStatus(List<Long> ids, TenantStatusEnum status) {
        boolean update = super.update(Wraps.<Tenant>lbU().set(Tenant::getStatus, status)
                .in(Tenant::getId, ids));

        delCache(ids);
        return update;
    }
}
