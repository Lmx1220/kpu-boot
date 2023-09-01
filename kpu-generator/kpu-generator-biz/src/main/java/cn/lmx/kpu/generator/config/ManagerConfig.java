package cn.lmx.kpu.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/22  12:54
 */
@Data
@NoArgsConstructor
public class ManagerConfig {
    // 继承的Manager类全称，带包名
    private String superManagerClass = "cn.lmx.basic.base.manager.SuperManager";
    // 继承的ManagerImpl类全称，带包名
    private String superManagerImplClass = "cn.lmx.basic.base.manager.impl.SuperManagerImpl";
    // 格式化Manager接口文件名称
    private String formatManagerFileName;
    // 格式化Manager实现类文件名称
    private String formatManagerImplFileName;
}
