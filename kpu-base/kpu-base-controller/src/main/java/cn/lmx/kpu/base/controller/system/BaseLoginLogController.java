package cn.lmx.kpu.base.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.annotation.log.WebLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.system.entity.system.DefLoginLog;
import cn.lmx.kpu.system.service.system.DefLoginLogService;
import cn.lmx.kpu.system.vo.query.system.DefLoginLogPageQuery;
import cn.lmx.kpu.system.vo.result.system.DefLoginLogResultVO;
import cn.lmx.kpu.system.vo.save.system.DefLoginLogSaveVO;
import cn.lmx.kpu.system.vo.update.system.DefLoginLogUpdateVO;

import java.time.LocalDateTime;


/**
 * <p>
 * 前端控制器
 * 登录日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/baseLoginLog")
@Tag(name = "登录日志")
public class BaseLoginLogController extends SuperController<DefLoginLogService, Long, DefLoginLog, DefLoginLogSaveVO,
        DefLoginLogUpdateVO, DefLoginLogPageQuery, DefLoginLogResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @PostMapping(value = "/anyone/page")
    public R<IPage<DefLoginLogResultVO>> anyOnePage(@RequestBody PageParams<DefLoginLogPageQuery> params) {
        return super.page(params);
    }


    @Operation(summary = "清空日志")
    @DeleteMapping("clear")
    @WebLog("清空日志")
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

        return success(superService.clearLog(clearBeforeTime, clearBeforeNum));
    }
}
