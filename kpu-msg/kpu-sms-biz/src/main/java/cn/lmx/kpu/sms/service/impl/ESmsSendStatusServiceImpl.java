package cn.lmx.kpu.sms.service.impl;

import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.sms.entity.ESmsSendStatus;
import cn.lmx.kpu.sms.manager.ESmsSendStatusManager;
import cn.lmx.kpu.sms.service.ESmsSendStatusService;
import cn.lmx.kpu.sms.vo.query.ESmsSendStatusPageQuery;
import cn.lmx.kpu.sms.vo.result.ESmsSendStatusResultVO;
import cn.lmx.kpu.sms.vo.save.ESmsSendStatusSaveVO;
import cn.lmx.kpu.sms.vo.update.ESmsSendStatusUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 短信发送状态
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 * @create [2023-11-14 11:08:02] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ESmsSendStatusServiceImpl extends SuperServiceImpl<ESmsSendStatusManager, Long, ESmsSendStatus, ESmsSendStatusSaveVO,
        ESmsSendStatusUpdateVO, ESmsSendStatusPageQuery, ESmsSendStatusResultVO> implements ESmsSendStatusService {


    @Override
    @Transactional(readOnly = true)
    public List<ESmsSendStatus> listByTaskId(Long id) {
        return list(Wraps.<ESmsSendStatus>lbQ().eq(ESmsSendStatus::getTaskId, id));
    }
}


