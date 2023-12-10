package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.Notice;
import cn.lmx.kpu.msg.vo.save.NoticeSaveVO;
import cn.lmx.kpu.msg.vo.update.NoticeUpdateVO;
import cn.lmx.kpu.msg.vo.result.NoticeResultVO;
import cn.lmx.kpu.msg.vo.query.NoticePageQuery;


/**
 * <p>
 * 业务接口
 * 通知表
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface NoticeService extends SuperService<Long, Notice, NoticeSaveVO,
    NoticeUpdateVO, NoticePageQuery, NoticeResultVO> {

}


