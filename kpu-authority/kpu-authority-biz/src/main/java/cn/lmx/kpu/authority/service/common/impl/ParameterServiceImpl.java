package cn.lmx.kpu.authority.service.common.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.base.service.SuperServiceImpl;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.kpu.authority.dao.common.ParameterMapper;
import cn.lmx.kpu.authority.entity.common.Parameter;
import cn.lmx.kpu.authority.event.ParameterUpdateEvent;
import cn.lmx.kpu.authority.event.model.ParameterUpdate;
import cn.lmx.kpu.authority.service.common.ParameterService;
import cn.lmx.kpu.common.cache.common.ParameterKeyCacheKeyBuilder;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

/**
 * <p>
 * 业务实现类
 * 参数配置
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class ParameterServiceImpl extends SuperServiceImpl<ParameterMapper, Parameter> implements ParameterService {

    private final CacheOps cacheOps;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Parameter model) {
        ArgumentAssert.isFalse(check(model.getKey()), "参数key重复");

        boolean bool = SqlHelper.retBool(baseMapper.insert(model));
        if (bool) {
            CacheKey cacheKey = new ParameterKeyCacheKeyBuilder().key(model.getKey());
            cacheOps.set(cacheKey, model.getValue());
        }
        return bool;
    }

    @Transactional(readOnly = true)
    public boolean check(String key) {
        return count(Wraps.<Parameter>lbQ().eq(Parameter::getKey, key)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Parameter model) {
        long count = count(Wraps.<Parameter>lbQ().eq(Parameter::getKey, model.getKey()).ne(Parameter::getId, model.getId()));
        ArgumentAssert.isFalse(count > 0, StrUtil.format("参数key[{}]已经存在，请勿重复创建", model.getKey()));

        boolean bool = SqlHelper.retBool(getBaseMapper().updateById(model));
        if (bool) {

            CacheKey cacheKey = new ParameterKeyCacheKeyBuilder().key(model.getKey());
            cacheOps.set(cacheKey, model.getValue());

            SpringUtils.publishEvent(new ParameterUpdateEvent(
                    new ParameterUpdate(model.getKey(), model.getValue(), null, ContextUtil.getTenant())
            ));
        }
        return bool;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<?> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return true;
        }
        List<Parameter> parameterList = super.listByIds((Collection<Long>) idList);
        if (parameterList.isEmpty()) {
            return true;
        }
        boolean bool = SqlHelper.retBool(getBaseMapper().deleteBatchIds(idList));
        CacheKey[] keys = parameterList.stream()
                .map(item -> new ParameterKeyCacheKeyBuilder().key(item.getKey()))
                .toArray(CacheKey[]::new);
        cacheOps.del(keys);

        parameterList.forEach(model ->
                SpringUtils.publishEvent(new ParameterUpdateEvent(
                        new ParameterUpdate(model.getKey(), model.getValue(), null, ContextUtil.getTenant())
                ))
        );
        return bool;
    }

    @Override
    public String getValue(String key, String defVal) {
        if (StrUtil.isEmpty(key)) {
            return defVal;
        }

        Function<CacheKey, String> loader = k -> {
            Parameter parameter = getOne(Wraps.<Parameter>lbQ().eq(Parameter::getKey, key));
            return parameter == null ? defVal : parameter.getValue();
        };
        CacheKey cacheKey = new ParameterKeyCacheKeyBuilder().key(key);
        return cacheOps.get(cacheKey, loader);
    }

    @Override
    public Map<String, String> findParams(List<String> keys) {
        if (CollUtil.isEmpty(keys)) {
            return Collections.emptyMap();
        }
        List<Parameter> list = list(Wraps.<Parameter>lbQ().in(Parameter::getKey, keys));
        Map<String, String> map = new HashMap<>();
        list.forEach(item -> map.put(item.getKey(), item.getValue()));
        return map;
    }
}
