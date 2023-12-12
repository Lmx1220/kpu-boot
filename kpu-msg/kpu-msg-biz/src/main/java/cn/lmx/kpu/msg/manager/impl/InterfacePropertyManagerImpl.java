package cn.lmx.kpu.msg.manager.impl;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.kpu.msg.entity.InterfaceProperty;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.msg.manager.InterfacePropertyManager;
import cn.lmx.kpu.msg.mapper.InterfacePropertyMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通用业务实现类
 * 接口属性
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class InterfacePropertyManagerImpl extends SuperManagerImpl<InterfacePropertyMapper, InterfaceProperty> implements InterfacePropertyManager {

    @Override
    public Map<String, Object> listByInterfaceId(Long id) {
        Map<String, Object> params = MapUtil.newHashMap();

        List<InterfaceProperty> interfaceProperties = getBaseMapper().selectList(new LbqWrapper<InterfaceProperty>().eq(InterfaceProperty::getInterfaceId, id).orderByAsc(InterfaceProperty::getSortValue));
        if (interfaceProperties == null || interfaceProperties.isEmpty()) {
            return params;
        } else {

            for (InterfaceProperty interfaceProperty : interfaceProperties) {
                params.put(interfaceProperty.getKey(), interfaceProperty.getValue());
            }
        }

        return params;
    }
}


