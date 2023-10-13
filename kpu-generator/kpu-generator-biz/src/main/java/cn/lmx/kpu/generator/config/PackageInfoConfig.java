package cn.lmx.kpu.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包配置
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Data
@NoArgsConstructor
public class PackageInfoConfig {

    /**
     * kpu项目 生成代码位于 src/main/java 下的基础包
     */
    private String parent = "cn.lmx.kpu";

    /**
     * kpu-util 基础包
     */
    private String utilParent = "cn.lmx.basic";

    /**
     * 枚举 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String enumeration = "enumeration";
    /**
     * saveVO 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String saveVo = "vo.save";
    /**
     * updateVO 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String updateVo = "vo.update";
    /**
     * query 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String pageQuery = "vo.query";
    /**
     * resultVo src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String resultVo = "vo.result";

    /**
     * 实体 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String entity = "entity";
    /**
     * manager 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String manager = "manager";
    /**
     * managerImpl 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String managerImpl = "manager.{}impl";

    /**
     * service 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String service = "service";

    /**
     * serviceImpl 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String serviceImpl = "service.{}impl";

    /**
     * controller 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String controller = "controller";

    /**
     * mapper 位于 src/main/java/{parent}/{moduleName} 下的 包名
     */
    private String mapper = "mapper";

    /**
     * Mapper XML 位于 src/main/resource 下的 包名
     */
    private String xml = "mapper_{}/base";
}
