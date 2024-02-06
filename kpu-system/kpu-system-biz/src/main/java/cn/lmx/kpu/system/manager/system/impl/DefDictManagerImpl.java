package cn.lmx.kpu.system.manager.system.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.cache.redis2.CacheResult;
import cn.lmx.basic.cache.repository.CachePlusOps;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbQueryWrap;
import cn.lmx.basic.echo.properties.EchoProperties;
import cn.lmx.basic.model.cache.CacheHashKey;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.kpu.common.cache.tenant.base.DictCacheKeyBuilder;
import cn.lmx.kpu.common.constant.DefValConstants;

import cn.lmx.kpu.system.entity.system.DefDict;
import cn.lmx.kpu.system.manager.system.DefDictManager;
import cn.lmx.kpu.system.mapper.system.DefDictMapper;
import cn.lmx.kpu.system.vo.result.system.DefDictItemResultVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@RequiredArgsConstructor
@Service
public class DefDictManagerImpl extends SuperManagerImpl<DefDictMapper, DefDict> implements DefDictManager {

    private final DefDictMapper defDictMapper;
    private final CachePlusOps cachePlusOps;
    private final EchoProperties ips;

    @Override
    
    public Map<Serializable, Object> findByIds(Set<Serializable> dictKeys) {
        if (dictKeys.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Serializable, Object> codeValueMap = MapUtil.newHashMap();
        dictKeys.forEach(dictKey -> {
            Function<CacheHashKey, Map<String, String>> fun = ck -> {
                LbQueryWrap<DefDict> wrap = Wraps.<DefDict>lbQ().eq(DefDict::getParentKey, dictKey);
                List<DefDict> list = defDictMapper.selectList(wrap);

                if (CollUtil.isNotEmpty(list)) {
                    return CollHelper.uniqueIndex(list, DefDict::getKey, DefDict::getName);
                } else {
                    return MapBuilder.<String, String>create().put(DefValConstants.DICT_NULL_VAL_KEY, "无数据").build();
                }
            };
            Map<String, CacheResult<String>> map = cachePlusOps.hGetAll(DictCacheKeyBuilder.builder(dictKey), fun);
            map.forEach((itemKey, itemName) -> {
                if (!DefValConstants.DICT_NULL_VAL_KEY.equals(itemKey)) {
                    codeValueMap.put(StrUtil.join(ips.getDictSeparator(), dictKey, itemKey), itemName.getValue());
                }
            });
        });
        return codeValueMap;
    }


    @Override
    
    public Map<String, List<DefDictItemResultVO>> findDictMapItemListByKey(List<String> dictKeys) {
        if (CollUtil.isEmpty(dictKeys)) {
            return Collections.emptyMap();
        }
        LbQueryWrap<DefDict> query = Wraps.<DefDict>lbQ().in(DefDict::getParentKey, dictKeys).eq(DefDict::getState, true)
                .orderByAsc(DefDict::getSortValue);
        List<DefDict> list = super.list(query);
        List<DefDictItemResultVO> voList = BeanUtil.copyToList(list, DefDictItemResultVO.class);

        //key 是类型
        return voList.stream().collect(groupingBy(DefDictItemResultVO::getParentKey, LinkedHashMap::new, toList()));
    }

    @Override
    public boolean removeItemByIds(Collection<Long> idList) {
        if (CollUtil.isEmpty(idList)) {
            return false;
        }
        List<DefDict> list = listByIds(idList);
        if (CollUtil.isEmpty(list)) {
            return false;
        }
        boolean flag = removeByIds(idList);

        CacheHashKey[] hashKeys = list.stream().map(model -> DictCacheKeyBuilder.builder(model.getParentKey(), model.getKey())).toArray(CacheHashKey[]::new);
        cachePlusOps.del(hashKeys);
        return flag;
    }
}
