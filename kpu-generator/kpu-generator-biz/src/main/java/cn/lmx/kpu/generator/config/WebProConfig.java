package cn.lmx.kpu.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.lmx.kpu.generator.enumeration.TplEnum;

/**
 * web pro 配置
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Data
@NoArgsConstructor
public class WebProConfig {
    /**
     * 格式化菜单文件名称
     */
    private String formatMenuName = "{}维护";

    /**
     * 前端生成页面样式模板
     */
    private TplEnum tpl = TplEnum.SIMPLE;

}
