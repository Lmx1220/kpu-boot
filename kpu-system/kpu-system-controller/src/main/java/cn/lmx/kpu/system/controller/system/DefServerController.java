package cn.lmx.kpu.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.base.R;
import cn.lmx.kpu.system.controller.system.domain.Server;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 PM
 * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defServer")
@Tag(name = "服务监控")
public class DefServerController {
    @GetMapping()
    public R<Server> server() {
        Server server = new Server();
        server.copyTo();
        return R.success(server);
    }

}
