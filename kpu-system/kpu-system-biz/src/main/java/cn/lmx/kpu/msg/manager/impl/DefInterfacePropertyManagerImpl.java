package cn.lmx.kpu.msg.manager.impl;

import cn.hutool.core.map.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.msg.entity.DefInterfaceProperty;
import cn.lmx.kpu.msg.manager.DefInterfacePropertyManager;
import cn.lmx.kpu.msg.mapper.DefInterfacePropertyMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通用业务实现类
 * 接口属性
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DefInterfacePropertyManagerImpl extends SuperManagerImpl<DefInterfacePropertyMapper, DefInterfaceProperty> implements DefInterfacePropertyManager {
    @Override
    public Map<String, Object> listByInterfaceId(Long id) {
        List<DefInterfaceProperty> propertyList = list(Wraps.<DefInterfaceProperty>lbQ().eq(DefInterfaceProperty::getInterfaceId, id));
        Map<String, Object> param = MapUtil.newHashMap();
        propertyList.forEach(item -> param.put(item.getKey(), item.getValue()));
        return param;
    }
}


