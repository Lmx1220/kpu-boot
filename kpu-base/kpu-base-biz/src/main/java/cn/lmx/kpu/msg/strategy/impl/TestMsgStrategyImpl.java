package cn.lmx.kpu.msg.strategy.impl;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.lmx.kpu.msg.entity.ExtendMsg;
import cn.lmx.kpu.msg.service.ExtendMsgService;
import cn.lmx.kpu.msg.strategy.MsgStrategy;
import cn.lmx.kpu.msg.strategy.domain.MsgParam;
import cn.lmx.kpu.msg.strategy.domain.MsgResult;

/**
 * @author lmx
 * @date 2024/02/07 0011 10:29
 */
public class TestMsgStrategyImpl implements MsgStrategy {
    private static final Logger log = LoggerFactory.getLogger(TestMsgStrategyImpl.class);

    @Resource
    private ExtendMsgService extendMsgService;

    @Override
    public MsgResult exec(MsgParam msgParam) {
        System.out.println(" 请开始你的接口逻辑 ");

        ExtendMsg a = extendMsgService.getById(msgParam.getExtendMsg().getId());
        log.info("a {}", a);

        return MsgResult.builder().result("保存成功").build();
    }
}
