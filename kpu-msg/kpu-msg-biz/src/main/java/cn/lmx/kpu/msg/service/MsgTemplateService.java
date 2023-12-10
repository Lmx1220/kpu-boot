package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.vo.save.MsgTemplateSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgTemplateUpdateVO;
import cn.lmx.kpu.msg.vo.result.MsgTemplateResultVO;
import cn.lmx.kpu.msg.vo.query.MsgTemplatePageQuery;


/**
 * <p>
 * 业务接口
 * 消息模板
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface MsgTemplateService extends SuperService<Long, MsgTemplate, MsgTemplateSaveVO,
    MsgTemplateUpdateVO, MsgTemplatePageQuery, MsgTemplateResultVO> {

}


