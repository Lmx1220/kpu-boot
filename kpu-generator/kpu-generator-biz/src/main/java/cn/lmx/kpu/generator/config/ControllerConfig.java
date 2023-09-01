package cn.lmx.kpu.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/22  12:57
 */
@Data
@NoArgsConstructor
public class ControllerConfig {
    // 生成 @RestController 控制器（默认 false）
    private Boolean restStyle = false;
    // 格式化文件名称
    private String formatFileName;
    // 驼峰转连字符
    private Boolean hyphenStyle = false;
    // 继承的Controller类全称，带包名
    private String superClass = "cn.lmx.basic.base.controller.SuperController";
}
