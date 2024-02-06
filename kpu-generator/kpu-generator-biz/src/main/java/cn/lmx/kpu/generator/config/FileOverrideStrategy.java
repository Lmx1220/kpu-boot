package cn.lmx.kpu.generator.config;

import lombok.Getter;
import lombok.Setter;
import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 AM
 * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
 */
@Setter
@Getter
public class FileOverrideStrategy {
    /**
     * [实体类、VO、Enum]覆盖文件策略
     */
    private FileOverrideStrategyEnum entityFileOverride = FileOverrideStrategyEnum.OVERRIDE;
    /**
     * [SQL]覆盖文件策略
     */
    private FileOverrideStrategyEnum sqlFileOverride = FileOverrideStrategyEnum.OVERRIDE;
    /**
     * mapper 覆盖文件策略
     */
    private FileOverrideStrategyEnum mapperFileOverride = FileOverrideStrategyEnum.EXIST_IGNORE;
    /**
     * xml 覆盖文件策略
     */
    private FileOverrideStrategyEnum xmlFileOverride = FileOverrideStrategyEnum.OVERRIDE;

    /**
     * Manageer覆盖文件策略
     */
    private FileOverrideStrategyEnum managerFileOverride = FileOverrideStrategyEnum.EXIST_IGNORE;

    /**
     * Service 覆盖文件策略
     */
    private FileOverrideStrategyEnum serviceFileOverride = FileOverrideStrategyEnum.EXIST_IGNORE;
    /**
     * Controller 覆盖文件策略
     */
    private FileOverrideStrategyEnum controllerFileOverride = FileOverrideStrategyEnum.EXIST_IGNORE;


    /**
     * [Api.ts Model.ts]覆盖文件策略
     */
    private FileOverrideStrategyEnum apiModelFileOverride = FileOverrideStrategyEnum.OVERRIDE;
    /**
     * [lang.en.ts lang.zh.ts]覆盖文件策略
     */
    private FileOverrideStrategyEnum langFileOverride = FileOverrideStrategyEnum.OVERRIDE;
    /**
     * [index.vue Edit.vue Tree.vue]覆盖文件策略
     */
    private FileOverrideStrategyEnum indexEditTreeFileOverride = FileOverrideStrategyEnum.EXIST_IGNORE;
    /**
     * [data.tsx]覆盖文件策略
     */
    private FileOverrideStrategyEnum dataFileOverride = FileOverrideStrategyEnum.EXIST_IGNORE;
}
