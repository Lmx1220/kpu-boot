package cn.lmx.kpu.model.vo;

import cn.hutool.core.collection.CollUtil;
import cn.lmx.basic.context.ContextUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2024/02/05  19:34
 */
@Data
@Accessors(chain = true)
public class BaseEventVO {
    private Map<String, String> map;

    /**
     * 将线程变量副 暂存到map
     * 适用于：异步调用前
     * @return cn.lmx.kpu.model.vo.BaseEventVO
     * @author lmx
     * @date 2024/2/5 19:34
     */
    public BaseEventVO copy() {
        if (map == null) {
            map = new HashMap<>();
        }
        map.clear();
        map.putAll(ContextUtil.getLocalMap());
        return this;
    }

    /**
     * 将map写入线程变量
     * 适用于：异步执行一开始
     * @return cn.lmx.kpu.model.vo.BaseEventVO
     * @author lmx
     * @date 2024/2/5 19:35
     */
    public BaseEventVO write() {
        if (CollUtil.isNotEmpty(map)) {
            ContextUtil.setLocalMap(map);
        }
        return this;
    }

}
