package cn.lmx.kpu.msg.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.msg.entity.ExtendNotice;
import cn.lmx.kpu.msg.manager.ExtendNoticeManager;
import cn.lmx.kpu.msg.service.ExtendNoticeService;
import cn.lmx.kpu.msg.vo.query.ExtendNoticePageQuery;
import cn.lmx.kpu.msg.vo.result.ExtendNoticeResultVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 业务实现类
 * 通知表
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExtendNoticeServiceImpl extends SuperServiceImpl<ExtendNoticeManager, Long, ExtendNotice> implements ExtendNoticeService {
    @Override
    public IPage<ExtendNoticeResultVO> page(IPage<ExtendNoticeResultVO> page, PageParams<ExtendNoticePageQuery> params) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean mark(List<Long> noticeIds, Long employeeId) {
        if (CollectionUtil.isEmpty(noticeIds) || employeeId == null) {
            return true;
        }

        return superManager.update(
                Wraps.<ExtendNotice>lbU().eq(ExtendNotice::getRecipientId, employeeId)
                        .in(ExtendNotice::getId, noticeIds)
                        .set(ExtendNotice::getIsRead, true)
                        .set(ExtendNotice::getReadTime, LocalDateTime.now())
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteMyNotice(List<Long> noticeIds) {
        ArgumentAssert.notEmpty(noticeIds, "请选择需要删除的消息");
        return superManager.removeByIds(noticeIds);
    }
}


