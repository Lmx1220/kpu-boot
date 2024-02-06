package cn.lmx.kpu.system.controller.tenant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.system.entity.tenant.DefDatasourceConfig;
import cn.lmx.kpu.system.service.tenant.DefDatasourceConfigService;
import cn.lmx.kpu.system.vo.query.tenant.DefDatasourceConfigPageQuery;
import cn.lmx.kpu.system.vo.result.tenant.DefDatasourceConfigResultVO;
import cn.lmx.kpu.system.vo.save.tenant.DefDatasourceConfigSaveVO;
import cn.lmx.kpu.system.vo.update.tenant.DefDatasourceConfigUpdateVO;


/**
 * <p>
 * 前端控制器
 * 数据源
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defDatasourceConfig")
@Tag(name = "数据源")
public class DefDatasourceConfigController extends SuperController<DefDatasourceConfigService, Long, DefDatasourceConfig, DefDatasourceConfigSaveVO, DefDatasourceConfigUpdateVO, DefDatasourceConfigPageQuery, DefDatasourceConfigResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Operation(summary = "测试数据库链接")
    @PostMapping("/testConnect")
    public R<Boolean> testConnect(@RequestParam Long id) {
        return R.success(superService.testConnection(id));
    }
}
