package cn.lmx.kpu.authority.controller.common;


import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.DeleteController;
import cn.lmx.basic.base.controller.PoiController;
import cn.lmx.basic.base.controller.SuperSimpleController;
import cn.lmx.kpu.authority.dto.common.OptLogResult;
import cn.lmx.kpu.authority.entity.common.OptLog;
import cn.lmx.kpu.authority.service.common.OptLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static cn.lmx.kpu.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static cn.lmx.kpu.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;

/**
 * <p>
 * 前端控制器
 * 系统日志
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Api(value = "OptLog", tags = "系统日志")
@PreAuth(replace = "authority:optLog:")
public class OptLogController extends SuperSimpleController<OptLogService, Long, OptLog, OptLog, OptLog, OptLog, OptLog>
        implements DeleteController<Long, OptLog, OptLog, OptLog, OptLog, OptLog>, PoiController<Long, OptLog, OptLog, OptLog, OptLog, OptLog> {

    /**
     * 查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "单体查询", notes = "单体查询")
    @GetMapping("/get")
    @PreAuth("hasAnyPermission('{}view')")
    public R<OptLogResult> get(@RequestParam Long id) {
        return success(baseService.getOptLogResultById(id));
    }


    @ApiOperation("清空日志")
    @DeleteMapping("clear")
    @SysLog("清空日志")
    public R<Boolean> clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = null;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            // 清理一千条以前日志数据
            clearBeforeNum = 1000;
        } else if (type == 6) {
            // 清理一万条以前日志数据
            clearBeforeNum = 10000;
        } else if (type == 7) {
            // 清理三万条以前日志数据
            clearBeforeNum = 30000;
        } else if (type == 8) {
            // 清理十万条以前日志数据
            clearBeforeNum = 100000;
        }
        return success(baseService.clearLog(clearBeforeTime, clearBeforeNum));
    }

}
