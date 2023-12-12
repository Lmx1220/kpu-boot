package cn.lmx.kpu.msg.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.msg.service.MsgTemplateService;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.msg.manager.MsgTemplateManager;
import cn.lmx.kpu.msg.entity.MsgTemplate;
import cn.lmx.kpu.msg.vo.save.MsgTemplateSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgTemplateUpdateVO;
import cn.lmx.kpu.msg.vo.result.MsgTemplateResultVO;
import cn.lmx.kpu.msg.vo.query.MsgTemplatePageQuery;

/**
 * <p>
 * 业务实现类
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
@Transactional(readOnly = true)
public class MsgTemplateServiceImpl extends SuperServiceImpl<MsgTemplateManager, Long, MsgTemplate, MsgTemplateSaveVO,
    MsgTemplateUpdateVO, MsgTemplatePageQuery, MsgTemplateResultVO> implements MsgTemplateService {


    @Override
    public MsgTemplate getByCode(String code) {
        return getSuperManager().getByCode(code);
    }
}


