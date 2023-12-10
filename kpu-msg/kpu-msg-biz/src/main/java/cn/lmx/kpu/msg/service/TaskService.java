package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.Task;
import cn.lmx.kpu.msg.vo.query.TaskPageQuery;
import cn.lmx.kpu.msg.vo.result.TaskResultVO;
import cn.lmx.kpu.msg.vo.save.TaskSaveVO;
import cn.lmx.kpu.msg.vo.update.TaskUpdateVO;


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
public interface TaskService extends SuperService<Long, Task, TaskSaveVO,
        TaskUpdateVO, TaskPageQuery, TaskResultVO> {

    Task saveTask(TaskSaveVO smsTask);
}


