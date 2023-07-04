package cn.lmx.kpu.tenant.controller;

import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.tenant.dto.DatasourceConfigPageQuery;
import cn.lmx.kpu.tenant.dto.DatasourceConfigSaveDTO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigUpdateDTO;
import cn.lmx.kpu.tenant.entity.DatasourceConfig;
import cn.lmx.kpu.tenant.service.DatasourceConfigService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * 数据源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/datasourceConfig")
@Api(value = "DatasourceConfig", tags = "数据源")
@PreAuth(enabled = false)
@SysLog(enabled = false)
public class DatasourceConfigController extends SuperController<DatasourceConfigService, Long, DatasourceConfig, DatasourceConfigPageQuery, DatasourceConfigSaveDTO, DatasourceConfigUpdateDTO> {

}
