package cn.lmx.kpu.msg.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.msg.service.NoticeService;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.msg.manager.NoticeManager;
import cn.lmx.kpu.msg.entity.Notice;
import cn.lmx.kpu.msg.vo.save.NoticeSaveVO;
import cn.lmx.kpu.msg.vo.update.NoticeUpdateVO;
import cn.lmx.kpu.msg.vo.result.NoticeResultVO;
import cn.lmx.kpu.msg.vo.query.NoticePageQuery;

/**
 * <p>
 * 业务实现类
 * 通知表
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
public class NoticeServiceImpl extends SuperServiceImpl<NoticeManager, Long, Notice, NoticeSaveVO,
    NoticeUpdateVO, NoticePageQuery, NoticeResultVO> implements NoticeService {


}


