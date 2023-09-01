package cn.lmx.kpu.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/22  12:53
 */
@Data
@NoArgsConstructor
public class ServiceConfig {
    // 继承的Service类全称，带包名
    private Class<?> superServiceClass = cn.lmx.basic.base.service.SuperService.class;
    // 继承的ServiceImpl类全称，带包名
    private Class<?> superServiceImplClass = cn.lmx.basic.base.service.impl.SuperServiceImpl.class;
    // 格式化service接口文件名称
    private String formatServiceFileName;
    // 格式化service实现类文件名称
    private String formatServiceImplFileName;
    // 匹配到此前缀的表，生成的 serviceImpl 类上会自动添加切换数据源注解 @DS
//        dsTablePrefix:
//                -xxx
}
