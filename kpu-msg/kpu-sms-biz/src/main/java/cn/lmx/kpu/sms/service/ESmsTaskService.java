package cn.lmx.kpu.sms.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.sms.entity.ESmsTask;
import cn.lmx.kpu.sms.vo.query.ESmsTaskPageQuery;
import cn.lmx.kpu.sms.vo.result.ESmsTaskResultVO;
import cn.lmx.kpu.sms.vo.save.ESmsTaskSaveVO;
import cn.lmx.kpu.sms.vo.update.ESmsTaskUpdateVO;


/**
 * <p>
 * 业务接口
 * 发送任务
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:01
 * @create [2023-11-14 11:08:01] [lmx] [代码生成器生成]
 */
public interface ESmsTaskService extends SuperService<Long, ESmsTask, ESmsTaskSaveVO,
        ESmsTaskUpdateVO, ESmsTaskPageQuery, ESmsTaskResultVO> {

    ESmsTask saveTask(ESmsTaskSaveVO smsTask);
}


