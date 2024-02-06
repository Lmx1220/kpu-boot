package cn.lmx.kpu.msg.strategy.impl;


import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.service.MsgService;
import cn.lmx.kpu.msg.strategy.MsgStrategy;
import cn.lmx.kpu.msg.strategy.domain.MsgParam;
import cn.lmx.kpu.msg.strategy.domain.MsgResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author lmx
 * @date 2023/11/19  18:40
 */
public class TestMsgStrategyImpl implements MsgStrategy {
    private static final Logger log = LoggerFactory.getLogger(TestMsgStrategyImpl.class);

    @Resource
    private MsgService msgService;

    @Override
    public MsgResult exec(MsgParam msgParam) {
        System.out.println(" 请开始你的接口逻辑 ");

        Msg a = msgService.getById(msgParam.getMsg().getId());
        log.info("a {}", a);

        return MsgResult.builder().result("保存成功").build();
    }
}
