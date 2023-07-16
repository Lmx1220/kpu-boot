package cn.lmx.kpu.authority.service.common.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.base.service.SuperServiceImpl;
import cn.lmx.basic.cache.repository.CachePlusOps;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.echo.properties.EchoProperties;
import cn.lmx.basic.model.cache.CacheHashKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.kpu.authority.dao.common.DictMapper;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.authority.service.common.DictService;
import cn.lmx.kpu.common.cache.common.DictItemCacheKeyBuilder;
import cn.lmx.kpu.common.constant.DefValConstants;
import cn.lmx.kpu.model.vo.query.CodeQueryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 业务实现类
 * 字典类型
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends SuperServiceImpl<DictMapper, Dict> implements DictService {

    private final EchoProperties ips;
    private final CachePlusOps cachePlusOps;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Dict model) {
        if (model.getParentId().equals(DefValConstants.PARENT_ID)) {
            long count = count(Wraps.<Dict>lbQ().eq(Dict::getKey, model.getKey()).eq(Dict::getKey, model.getKey()));
            ArgumentAssert.isFalse(count > 0, StrUtil.format("字典[{}]已经存在，请勿重复创建", model.getKey()));
        } else {
            Dict parent = getById(model.getParentId());
            ArgumentAssert.notNull(parent, StrUtil.format("字典[{}]不存在", model.getParentId()));
            long count = count(Wraps.<Dict>lbQ().eq(Dict::getParentId, parent.getId()).eq(Dict::getKey, model.getKey()));
            ArgumentAssert.isFalse(count > 0, StrUtil.format("字典项[{}]已经存在，请勿重复创建", model.getKey()));
            model.setParentKey(parent.getKey());
        }

        boolean bool = super.save(model);

        CacheHashKey typeKey = new DictItemCacheKeyBuilder().hashFieldKey(model.getKey(), model.getKey());
        cachePlusOps.hSet(typeKey, model.getName());
        return bool;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllById(Dict model) {
        return update(model, super::updateAllById);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Dict model) {
        return update(model, super::updateById);
    }

    private boolean update(Dict model, Function<Dict, Boolean> function) {
        if (model.getParentId().equals(DefValConstants.PARENT_ID)) {
            long count = count(Wraps.<Dict>lbQ().eq(Dict::getKey, model.getKey())
                    .eq(Dict::getKey, model.getKey()).ne(Dict::getId, model.getId()));
            ArgumentAssert.isFalse(count > 0, StrUtil.format("字典[{}]已经存在，请勿重复创建", model.getKey()));
        } else {
            Dict parent = getById(model.getParentId());
            ArgumentAssert.notNull(parent, StrUtil.format("字典[{}]不存在", model.getParentId()));
            long count = count(Wraps.<Dict>lbQ().eq(Dict::getParentId, parent.getId()).eq(Dict::getKey, model.getKey()).ne(Dict::getId, model.getId()));
            ArgumentAssert.isFalse(count > 0, StrUtil.format("字典项[{}]已经存在，请勿重复创建", model.getKey()));
            model.setParentKey(parent.getKey());
        }
        Dict old = getById(model.getId());
        ArgumentAssert.notNull(old, "字典不存在或已被删除！");
        boolean bool = function.apply(model);

        CacheHashKey typeKey = new DictItemCacheKeyBuilder().hashKey(model.getKey());
        cachePlusOps.del(typeKey);
        return bool;
    }

    @Override
    public boolean removeById(Serializable id) {
        Dict model = getById(id);
        ArgumentAssert.notNull(model, "字典项不存在");
        boolean remove = super.removeById(id);

        CacheHashKey typeKey = new DictItemCacheKeyBuilder().hashFieldKey(model.getKey(), model.getParentId().equals(DefValConstants.PARENT_ID) ? model.getKey() : model.getParentKey());
        cachePlusOps.hDel(typeKey);
        return remove;
    }

    @Override
    public boolean removeByIds(Collection<?> idList) {
        if (idList.isEmpty()) {
            return true;
        }
        List<Dict> list = listByIds((Collection<Long>) idList);
        boolean remove = super.removeByIds(idList);
        CacheHashKey[] hashKeys = list.stream().map(model -> new DictItemCacheKeyBuilder().hashKey(model.getParentId().equals(DefValConstants.PARENT_ID) ? model.getKey() : model.getParentKey())).toArray(CacheHashKey[]::new);
        cachePlusOps.del(hashKeys);
        return remove;
    }


    @Override
    public Map<String, List<Dict>> listByItem(CodeQueryVO[] types) {
        if (ArrayUtil.isEmpty(types)) {
            return Collections.emptyMap();
        }
        List<Dict> list = new ArrayList<>();
        for (CodeQueryVO type : types) {
            LbqWrapper<Dict> query = Wraps.<Dict>lbQ()
                    .eq(Dict::getParentKey, type.getType())
                    .eq(Dict::getState, true)
                    .orderByAsc(Dict::getSortValue);
            if (ArrayUtil.isNotEmpty(type.getExcludes())) {
                query.notIn(Dict::getKey, type.getExcludes());
            }
            list.addAll(super.list(query));
        }


        //key 是类型
        return list.stream().collect(groupingBy(Dict::getParentKey, LinkedHashMap::new, toList()));
    }


    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> types) {
        if (types.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Serializable, Object> codeValueMap = new HashMap<>();
        types.forEach(type -> {
            Function<CacheHashKey, Map<String, String>> fun = (ck) -> {
                LbqWrapper<Dict> wrap = Wraps.<Dict>lbQ().eq(Dict::getParentKey, type);
                List<Dict> list = list(wrap);
                return CollHelper.uniqueIndex(list, Dict::getKey, Dict::getName);
            };
            Map<String, String> map = cachePlusOps.hGetAll(new DictItemCacheKeyBuilder().hashKey(type), fun);
            map.forEach((value, txt) -> codeValueMap.put(StrUtil.join(ips.getDictSeparator(), type, value), txt));
        });
        return codeValueMap;
    }

}
