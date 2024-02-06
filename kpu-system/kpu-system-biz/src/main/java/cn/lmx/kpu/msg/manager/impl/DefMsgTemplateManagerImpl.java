package cn.lmx.kpu.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.msg.entity.DefMsgTemplate;
import cn.lmx.kpu.msg.manager.DefMsgTemplateManager;
import cn.lmx.kpu.msg.mapper.DefMsgTemplateMapper;

/**
 * <p>
 * 通用业务实现类
 * 消息模板
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DefMsgTemplateManagerImpl extends SuperManagerImpl<DefMsgTemplateMapper, DefMsgTemplate> implements DefMsgTemplateManager {
    @Override
    public DefMsgTemplate getByCode(String code) {
        return getOne(Wraps.<DefMsgTemplate>lbQ().eq(DefMsgTemplate::getCode, code));
    }
}


