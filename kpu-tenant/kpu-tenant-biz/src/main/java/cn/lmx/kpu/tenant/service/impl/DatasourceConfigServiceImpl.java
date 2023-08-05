package cn.lmx.kpu.tenant.service.impl;


import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.tenant.dto.DatasourceConfigPageQuery;
import cn.lmx.kpu.tenant.dto.DatasourceConfigResultVO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigSaveVO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigUpdateVo;
import cn.lmx.kpu.tenant.entity.DatasourceConfig;
import cn.lmx.kpu.tenant.manager.DatasourceConfigManager;
import cn.lmx.kpu.tenant.service.DatasourceConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DatasourceConfigServiceImpl extends SuperServiceImpl<DatasourceConfigManager, Long, DatasourceConfig, DatasourceConfigSaveVO, DatasourceConfigUpdateVo, DatasourceConfigPageQuery, DatasourceConfigResultVO> implements DatasourceConfigService {


}
