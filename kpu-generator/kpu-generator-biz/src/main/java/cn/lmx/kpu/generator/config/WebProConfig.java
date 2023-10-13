package cn.lmx.kpu.generator.config;

import cn.lmx.kpu.generator.enumeration.TplEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * web pro 配置
 *
 * @author lmx
 * @date 2023/10/13 14:27
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
