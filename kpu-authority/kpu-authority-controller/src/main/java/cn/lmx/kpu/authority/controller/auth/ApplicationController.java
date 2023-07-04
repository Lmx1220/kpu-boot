package cn.lmx.kpu.authority.controller.auth;


import cn.hutool.core.util.RandomUtil;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.kpu.authority.dto.auth.ApplicationPageQuery;
import cn.lmx.kpu.authority.dto.auth.ApplicationSaveDTO;
import cn.lmx.kpu.authority.dto.auth.ApplicationUpdateDTO;
import cn.lmx.kpu.authority.entity.auth.Application;
import cn.lmx.kpu.authority.service.auth.ApplicationService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 应用
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/application")
@Api(value = "Application", tags = "应用")
@ApiSupport(author = "lmx")
@PreAuth(replace = "authority:application:")
public class ApplicationController extends SuperCacheController<ApplicationService, Long, Application, ApplicationPageQuery, ApplicationSaveDTO, ApplicationUpdateDTO> {

    @Override
    public R<Application> handlerSave(ApplicationSaveDTO applicationSaveDTO) {
        applicationSaveDTO.setClientId(RandomUtil.randomString(24));
        applicationSaveDTO.setClientSecret(RandomUtil.randomString(32));
        return super.handlerSave(applicationSaveDTO);
    }

}
