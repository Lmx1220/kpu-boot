package cn.lmx.kpu.generator.config;

import cn.lmx.kpu.generator.rules.NamingStrategy;
import cn.lmx.kpu.generator.type.EntitySuperClass;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/22  12:00
 */
@Data
@NoArgsConstructor
public class EntityConfig {
    // 【实体、VO】时间类型对应策略  ONLY_DATE: java.util  SQL_PACK:java.sql  TIME_PACK: java.time
    private DateType dateType = DateType.TIME_PACK;
    // 【实体】Entity类的父类
    private EntitySuperClass entitySuperClass = EntitySuperClass.ENTITY;
    // 【实体】指定生成的主键的ID类型 (${superClass} == NONE 时，新生成的实体才生效)
    private IdType idType = IdType.INPUT;
    // 【实体、VO】表字段转换为实体字段的命名策略
    private NamingStrategy columnNaming = NamingStrategy.underline_to_camel;
    // 【实体、VO】生成实体类时，忽略的字段（字段名）
    private List<String> ignoreColumns = new ArrayList<>();
    // 【实体、VO】是否为链式模型
    private Boolean chain = true;
    // 【实体、VO】 是否为lombok模型
    private Boolean lombok = true;
    // 【实体】乐观锁字段名称
    private String versionColumnName;
    // 【实体】乐观锁属性名称
    private String versionPropertyName;
    // 【实体】逻辑删除字段名称
    private String logicDeleteColumnName;
    // 【实体】逻辑删除属性名称
    private String logicDeletePropertyName;
    // 【实体】生成的@TableField注解上需要填充的字段和填充类型
    private Map<String, FieldFill> fillColumnName = new HashMap<>();
    private Map<String, FieldFill> fillPropertyName = new HashMap<>();
    private List<String> fieldPrefix = new ArrayList<>();
    private List<String> fieldSuffix = new ArrayList<>();
    // 【VO】格式化SaveVO文件名称
    private String formatSaveVoFileName;
    // 【VO】格式化UpdateVO文件名称
    private String formatUpdateVoFileName;
    // 【VO】格式化ResultVO文件名称
    private String formatResultVoFileName;
    // 【VO】格式化 PageQuery 文件名称
    private String formatPageQueryFileName;

    private String formatEnumFileName;
}