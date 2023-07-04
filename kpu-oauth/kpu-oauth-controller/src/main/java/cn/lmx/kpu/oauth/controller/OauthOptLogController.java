package cn.lmx.kpu.oauth.controller;


import cn.lmx.basic.base.R;
import cn.lmx.basic.model.log.OptLogDTO;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.authority.service.common.OptLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
@AllArgsConstructor
@ApiIgnore
public class OauthOptLogController {

    private final OptLogService optLogService;

    /**
     * 保存系统日志
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @PostMapping
    @ApiOperation(value = "保存系统日志", notes = "保存系统日志不为空的字段")
    public R<OptLogDTO> save(@RequestBody OptLogDTO data) {
        optLogService.save(data);
        return R.success(BeanPlusUtil.toBean(data, OptLogDTO.class));
    }

}
