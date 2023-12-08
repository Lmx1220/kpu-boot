package cn.lmx.kpu.sms.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.sms.entity.ESmsSendStatus;
import cn.lmx.kpu.sms.vo.query.ESmsSendStatusPageQuery;
import cn.lmx.kpu.sms.vo.result.ESmsSendStatusResultVO;
import cn.lmx.kpu.sms.vo.save.ESmsSendStatusSaveVO;
import cn.lmx.kpu.sms.vo.update.ESmsSendStatusUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 短信发送状态
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 * @create [2023-11-14 11:08:02] [lmx] [代码生成器生成]
 */
public interface ESmsSendStatusService extends SuperService<Long, ESmsSendStatus, ESmsSendStatusSaveVO,
        ESmsSendStatusUpdateVO, ESmsSendStatusPageQuery, ESmsSendStatusResultVO> {
    /**
     * 根据任务id查询待发送数据
     *
     * @param id 任务id
     */
    List<ESmsSendStatus> listByTaskId(Long id);
}


