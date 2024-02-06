package cn.lmx.kpu.msg.strategy;


import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.jackson.JsonUtil;
import cn.lmx.basic.model.Kv;
import cn.lmx.kpu.common.utils.FreeMarkerUtil;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.glue.GlueFactory;
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
     * 解析参数
     * @param param param
     * @return
     */
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

    /**
     * 替换变量
     * @param extendMsg extendMsg
     * @param extendMsgTemplate extendMsgTemplate
     * @return
     */
    default MsgResult replaceVariable(Msg extendMsg, MsgTemplate extendMsgTemplate) {
        String script = extendMsgTemplate.getScript();
        String templateContent = extendMsgTemplate.getContent();
        String templateTitle = extendMsgTemplate.getTitle();
        Map<String, Object> params = new LinkedHashMap<>();
        if (StrUtil.isNotEmpty(extendMsg.getParam())) {
            List<Kv> list = JsonUtil.parseArray(extendMsg.getParam(), Kv.class);
            for (Kv kv : list) {
                params.put(kv.getKey(), kv.getValue());
            }
        }
        Map<String, Object> resultParams = params;
        String title = templateTitle;
        String content = templateContent;
        if (StrUtil.isNotEmpty(script)) {
            resultParams = (Map<String, Object>) GlueFactory.getInstance().exeGroovyScript(script, params);
        }
        if (StrUtil.isNotEmpty(templateTitle)) {
            title = FreeMarkerUtil.generateString(templateTitle, resultParams);
        }
        if (StrUtil.isNotEmpty(templateContent)) {
            content = FreeMarkerUtil.generateString(templateContent, resultParams);
        }

        return MsgResult.builder().title(title).content(content).build();
    }

    /**
     * 执行发送
     * @param msgParam
     * @return
     * @throws Exception
     */
    MsgResult exec(MsgParam msgParam) throws Exception;

    /**
     * 是否执行成功
     *
     * @param result 执行函数的返回值
     * @return
     */
    default boolean isSuccess(MsgResult result) {
        return true;
    }
}
