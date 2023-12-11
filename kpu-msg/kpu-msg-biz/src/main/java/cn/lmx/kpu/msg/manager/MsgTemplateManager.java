package cn.lmx.kpu.msg.manager;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.msg.entity.MsgTemplate;

/**
 * <p>
 * 通用业务接口
 * 消息模板
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface MsgTemplateManager extends SuperManager<MsgTemplate> {

    MsgTemplate getByCode(String templateCode);
}


