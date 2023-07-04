package cn.lmx.kpu.authority.controller.common;


import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.authority.dto.common.ParameterPageQuery;
import cn.lmx.kpu.authority.dto.common.ParameterSaveDTO;
import cn.lmx.kpu.authority.dto.common.ParameterUpdateDTO;
import cn.lmx.kpu.authority.entity.common.Parameter;
import cn.lmx.kpu.authority.service.common.ParameterService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 参数配置
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/parameter")
@Api(value = "Parameter", tags = "参数配置")
@PreAuth(replace = "authority:parameter:")
public class ParameterController extends SuperController<ParameterService, Long, Parameter, ParameterPageQuery, ParameterSaveDTO, ParameterUpdateDTO> {

}
