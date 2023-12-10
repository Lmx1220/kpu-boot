package cn.lmx.kpu.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.lmx.kpu.msg.entity.Notice;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.msg.manager.NoticeManager;
import cn.lmx.kpu.msg.mapper.NoticeMapper;

/**
 * <p>
 * 通用业务实现类
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
public class NoticeManagerImpl extends SuperManagerImpl<NoticeMapper, Notice> implements NoticeManager {

}


