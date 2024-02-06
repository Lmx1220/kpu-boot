package cn.lmx.kpu.oauth.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.R;
import cn.lmx.basic.model.log.OptLogDTO;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.base.service.system.BaseOperationLogService;
import cn.lmx.kpu.base.vo.save.system.BaseOperationLogSaveVO;

/**
 * <p>
 * 前端控制器
 * 系统日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Tag(name = "系统访问日志")
@AllArgsConstructor
@Hidden
public class WebLogController {

    private final BaseOperationLogService baseOperationLogService;

    /**
     * 保存系统日志
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @PostMapping
    @Operation(summary = "保存系统日志", description = "保存系统日志不为空的字段")
    public R<Boolean> save(@RequestBody OptLogDTO data) {
        baseOperationLogService.save(BeanPlusUtil.toBean(data, BaseOperationLogSaveVO.class));
        return R.success(true);
    }

}
