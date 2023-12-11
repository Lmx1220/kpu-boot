package cn.lmx.kpu.msg.manager.impl;

import cn.lmx.basic.database.mybatis.conditions.Wraps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.msg.manager.MsgTemplateManager;
import cn.lmx.kpu.msg.mapper.MsgTemplateMapper;

/**
 * <p>
 * 通用业务实现类
 * 消息模板
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MsgTemplateManagerImpl extends SuperManagerImpl<MsgTemplateMapper, MsgTemplate> implements MsgTemplateManager {

    @Override
    public MsgTemplate getByCode(String templateCode) {

        return getSuperMapper().selectOne( Wraps.<MsgTemplate>lbQ().eq(MsgTemplate::getTemplateCode, templateCode).last("limit 1"));
    }
}


