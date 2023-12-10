package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.InterfaceLogging;
import cn.lmx.kpu.msg.vo.save.InterfaceLoggingSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceLoggingUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceLoggingResultVO;
import cn.lmx.kpu.msg.vo.query.InterfaceLoggingPageQuery;


/**
 * <p>
 * 业务接口
 * 接口执行日志记录
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface InterfaceLoggingService extends SuperService<Long, InterfaceLogging, InterfaceLoggingSaveVO,
    InterfaceLoggingUpdateVO, InterfaceLoggingPageQuery, InterfaceLoggingResultVO> {

}


