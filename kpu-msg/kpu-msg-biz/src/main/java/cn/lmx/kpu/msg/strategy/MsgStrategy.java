package cn.lmx.kpu.msg.strategy;


import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.jackson.JsonUtil;
import cn.lmx.basic.model.Kv;
import cn.lmx.kpu.msg.strategy.domain.MsgParam;
import cn.lmx.kpu.msg.strategy.domain.MsgResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象策略类: 发送短信
 * <p>
 * 每个短信 服务商都有自己的 发送短信策略(sdk)
 *
 * @author zuihou
 * @date 2019-05-15
 */
public interface MsgStrategy {
    /**
     * 发送短信
     *
     * @param msgParam 短信任务
     * @return 任务id
     */
    MsgResult exec(MsgParam msgParam);

    default Map<String, String> parseParam(String param) {
        Map<String, String> map = new LinkedHashMap<>();
        if (StrUtil.isNotEmpty(param)) {
            List<Kv> list = JsonUtil.parseArray(param, Kv.class);
            for (Kv kv : list) {
                map.put(kv.getKey(), kv.getValue());
            }
        }
        return map;
    }
}
