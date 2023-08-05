package cn.lmx.kpu.tenant.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.tenant.dto.DatasourceConfigPageQuery;
import cn.lmx.kpu.tenant.dto.DatasourceConfigResultVO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigSaveVO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigUpdateVo;
import cn.lmx.kpu.tenant.entity.DatasourceConfig;
import cn.lmx.kpu.tenant.manager.DatasourceConfigManager;

/**
 * <p>
 * 业务接口
 * 数据源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface DatasourceConfigService extends SuperService<Long, DatasourceConfig, DatasourceConfigSaveVO, DatasourceConfigUpdateVo, DatasourceConfigPageQuery, DatasourceConfigResultVO> {


}
