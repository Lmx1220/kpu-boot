package cn.lmx.kpu.generator.manager.impl;

import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.generator.dao.GenTableColumnMapper;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.manager.GenTableColumnManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  15:28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GenTableColumnManagerImpl extends SuperManagerImpl<GenTableColumnMapper, GenTableColumn> implements GenTableColumnManager {

}
