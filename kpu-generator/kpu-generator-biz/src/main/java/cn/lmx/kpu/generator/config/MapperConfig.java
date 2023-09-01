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
public class MapperConfig {
    // 格式化Mapper文件名称
    private String formatMapperFileName;
    // 格式化Xml文件名称
    private String formatXmlFileName;
    // 是否添加 @Mapper 注解（默认 false）
    private Boolean mapperAnnotation = false;
    // mapper类上是否添加忽略表结构 @InterceptorIgnore
//        private String columnAnnotationTablePrefix:
//                - 表的前缀
    // 生成的xml中，是否包含BaseResultMap（默认 false）
    private Boolean baseResultMap = false;
}
