package cn.lmx.kpu.tenant.controller;

import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.tenant.dto.DatasourceConfigPageQuery;
import cn.lmx.kpu.tenant.dto.DatasourceConfigResultVO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigSaveVO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigUpdateVo;
import cn.lmx.kpu.tenant.entity.DatasourceConfig;
import cn.lmx.kpu.tenant.service.DatasourceConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class DatasourceConfigController extends SuperController<DatasourceConfigService, Long, DatasourceConfig, DatasourceConfigSaveVO,
        DatasourceConfigUpdateVo, DatasourceConfigPageQuery, DatasourceConfigResultVO> {

    @PostMapping("testConnect")
    @ApiOperation(value = "测试数据库链接", notes = "测试数据库链接")
    @SysLog(value = "'测试数据库链接")
    public R<Boolean> testConnect(@RequestParam Long id) {
        return R.success(superService.testConnect(id));
    }

}
