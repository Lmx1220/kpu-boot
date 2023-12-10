package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.InterfaceLog;
import cn.lmx.kpu.msg.vo.save.InterfaceLogSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceLogUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceLogResultVO;
import cn.lmx.kpu.msg.vo.query.InterfaceLogPageQuery;


/**
 * <p>
 * 业务接口
 * 接口执行日志
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface InterfaceLogService extends SuperService<Long, InterfaceLog, InterfaceLogSaveVO,
    InterfaceLogUpdateVO, InterfaceLogPageQuery, InterfaceLogResultVO> {

}


