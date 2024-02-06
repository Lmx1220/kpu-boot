package cn.lmx.kpu.oauth.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.annotation.log.WebLog;
import cn.lmx.basic.annotation.response.IgnoreResponseBodyAdvice;
import cn.lmx.basic.base.R;
import cn.lmx.kpu.base.service.user.BaseOrgService;
import cn.lmx.kpu.base.service.user.BasePositionService;
import cn.lmx.kpu.oauth.service.DictService;
import cn.lmx.kpu.system.service.tenant.DefUserService;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 数据注入的查询实现类
 *
 * @author lmx
 * @date 2024/02/07  02:04 上午
 */
@Slf4j
@RestController
@AllArgsConstructor()
@RequestMapping("/echo")
@IgnoreResponseBodyAdvice
@Tag(name = "数据注入查询接口， 不建议前端调用")
@Hidden
public class EchoController {
    private final DictService dictService;
    private final BaseOrgService baseOrgService;
    private final DefUserService userService;
    private final BasePositionService basePositionService;

    @GetMapping("/anyTenant/test")
    @WebLog
    public R<Object> test(@RequestParam(required = false) Long id) {
        log.info("id={}", id);
        return R.success(id);
    }

    @Operation(summary = "根据id查询用户", description = "根据id查询用户")
    @PostMapping("/user/findByIds")
    public Map<Serializable, Object> findUserByIds(@RequestParam(value = "ids") Set<Serializable> ids) {
        return userService.findByIds(ids);
    }

    @PostMapping("/position/findByIds")
    public Map<Serializable, Object> findStationByIds(@RequestParam("ids") Set<Serializable> ids) {
        return basePositionService.findByIds(ids);
    }

    @PostMapping("/org/findByIds")
    public Map<Serializable, Object> findOrgByIds(@RequestParam("ids") Set<Serializable> ids) {
        return baseOrgService.findByIds(ids);
    }

    @Operation(summary = "查询字典项", description = "根据字典编码查询字典项")
    @PostMapping("/dict/findByIds")
    public Map<Serializable, Object> findDictByIds(@RequestParam("ids") Set<Serializable> ids) {
        return this.dictService.findByIds(ids);
    }

}
