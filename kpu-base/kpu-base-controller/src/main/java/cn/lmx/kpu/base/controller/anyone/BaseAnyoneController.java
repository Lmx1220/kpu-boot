package cn.lmx.kpu.base.controller.anyone;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.R;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/anyone")
@Tag(name = "需要登录但无需验证uri权限的接口")
public class BaseAnyoneController {

    @GetMapping("/base/test")
    public R<Object> test(@RequestParam(required = false) Long id) throws InterruptedException {
        log.info("id={}", id);
        Thread.sleep(id);
        log.info("id={}", id);
        return R.success(id);
    }

}
