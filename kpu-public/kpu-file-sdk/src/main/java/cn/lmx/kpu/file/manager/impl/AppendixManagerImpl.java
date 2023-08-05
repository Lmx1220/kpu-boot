package cn.lmx.kpu.file.manager.impl;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.file.entity.Appendix;
import cn.lmx.kpu.file.manager.AppendixManager;
import cn.lmx.kpu.file.mapper.AppendixMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppendixManagerImpl extends SuperManagerImpl<AppendixMapper, Appendix> implements AppendixManager {
}
