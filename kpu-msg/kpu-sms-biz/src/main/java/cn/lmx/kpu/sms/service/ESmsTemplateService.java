package cn.lmx.kpu.sms.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.basic.interfaces.echo.LoadService;
import cn.lmx.kpu.sms.entity.ESmsTemplate;
import cn.lmx.kpu.sms.vo.query.ESmsTemplatePageQuery;
import cn.lmx.kpu.sms.vo.result.ESmsTemplateResultVO;
import cn.lmx.kpu.sms.vo.save.ESmsTemplateSaveVO;
import cn.lmx.kpu.sms.vo.update.ESmsTemplateUpdateVO;


/**
 * <p>
 * 业务接口
 * 短信模板
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 * @create [2023-11-14 11:08:02] [lmx] [代码生成器生成]
 */
public interface ESmsTemplateService extends SuperService<Long, ESmsTemplate, ESmsTemplateSaveVO,
        ESmsTemplateUpdateVO, ESmsTemplatePageQuery, ESmsTemplateResultVO> , LoadService {

}


