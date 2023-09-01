package cn.lmx.kpu.generator.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.generator.config.EntityConfig;
import cn.lmx.kpu.generator.config.GeneratorConfig;
import cn.lmx.kpu.generator.converts.ITypeConvert;
import cn.lmx.kpu.generator.converts.TypeConverts;
import cn.lmx.kpu.generator.entity.Column;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.enumeration.SqlConditionEnum;
import cn.lmx.kpu.generator.rules.ColumnType;
import cn.lmx.kpu.generator.rules.DbColumnType;
import cn.lmx.kpu.generator.rules.NamingStrategy;
import cn.lmx.kpu.generator.utils.inner.CommentUtils;
import cn.lmx.kpu.generator.utils.inner.EchoType;
import cn.lmx.kpu.generator.utils.inner.EnumType;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  09:20
 */
@Slf4j
public class GenUtils {
    public static GenTableColumn initColumnField(GeneratorConfig generatorConfig, DbType dbType, GenTable genTable, Column column) {
        EntityConfig entityConfig = generatorConfig.getEntityConfig();
        if (entityConfig.getIgnoreColumns().contains(column.getName())) {
            log.info("已经忽略字段:{}.{}", genTable.getName(), column.getName());
            return null;
        }
        GenTableColumn tableColumn = new GenTableColumn();
        String name = column.getName();
        tableColumn.setJavaField(processName(name, entityConfig.getColumnNaming(), entityConfig.getFieldPrefix(), entityConfig.getFieldSuffix()));
        tableColumn.setComment(column.getComment());
        tableColumn.setSwaggerComment(getSwaggerComment(column.getComment()));
        EchoType echoType = CommentUtils.getEchoType(column.getComment());
        if (echoType != null) {
            // Echo
            tableColumn.setEchoStr(echoType.getEchoStr());
            // 字典类型
            tableColumn.setDictType(echoType.getDictType());
        }
        // 解析注释中的枚举类型
        EnumType enumType = CommentUtils.getEnumStr(genTable.getEntityName(), tableColumn.getJavaField(), entityConfig.getFormatEnumFileName(),
                tableColumn.getSwaggerComment(), column.getComment());
        if (enumType != null) {
            tableColumn.setEnumStr(enumType.getEnumStr());
            tableColumn.setJavaType(enumType.getEnumName());
            // 枚举类型的回显注解固定值
            tableColumn.setEchoStr("@Echo(api = Echo.ENUM_API)");
            tableColumn.setTsType("string");

//            tableColumn.setComponent(ComponentEnum.PLUS_API_RADIO_GROUP.getValue());
//            tableColumn.setVxeComponent(VxeComponentEnum.$RADIO.getValue());
            tableColumn.setQueryType(SqlConditionEnum.EQUAL);
        } else {
            ITypeConvert typeConvert = TypeConverts.getTypeConvert(dbType);
            ColumnType columnType = typeConvert.processTypeConvert(entityConfig.getDateType(), column.getTypeName(), column.getSize(), column.getDigits());
            tableColumn.setJavaType(columnType.getType());
            tableColumn.setTsType(columnType.getTsType());
            if (columnType == DbColumnType.STRING) {
                tableColumn.setQueryType(SqlConditionEnum.LIKE);
            } else {
                tableColumn.setQueryType(SqlConditionEnum.EQUAL);
            }

            // #TODO 前端组件 未完成

        }
        tableColumn.setTableId(genTable.getId());
        tableColumn.setName(name);
        tableColumn.setType(column.getTypeName());
        tableColumn.setSize(column.getSize());
        tableColumn.setIsPk(column.isPk());
        tableColumn.setIsRequired(!column.isNullable());
        tableColumn.setIsIncrement(column.isAutoIncrement());
        String versionPropertyName = entityConfig.getVersionPropertyName();
        String versionColumnName = entityConfig.getVersionColumnName();
        tableColumn.setIsVersionField(StringUtils.isNotBlank(versionPropertyName)
                && StringUtils.equals(versionPropertyName, tableColumn.getJavaField())
                || StringUtils.isNotBlank(versionColumnName)
                && StringUtils.equalsIgnoreCase(versionColumnName, tableColumn.getName()));
        String logicDeleteColumnName = entityConfig.getLogicDeleteColumnName();
        String logicDeletePropertyName = entityConfig.getLogicDeletePropertyName();
        tableColumn.setIsLogicDeleteField(StringUtils.isNotBlank(logicDeletePropertyName)
                && StringUtils.equals(logicDeletePropertyName, tableColumn.getJavaField())
                || StringUtils.isNotBlank(logicDeleteColumnName)
                && StringUtils.equalsIgnoreCase(logicDeleteColumnName, tableColumn.getName()));
        Map<String, FieldFill> fillColumnName = entityConfig.getFillColumnName();
        Map<String, FieldFill> fillPropertyName = entityConfig.getFillPropertyName();
        if (CollUtil.isNotEmpty(fillPropertyName) && fillPropertyName.containsKey(tableColumn.getJavaField())) {
            tableColumn.setFill(fillPropertyName.get(tableColumn.getJavaField()));
        }
        if (CollUtil.isNotEmpty(fillColumnName) && fillColumnName.containsKey(tableColumn.getName())) {
            tableColumn.setFill(fillColumnName.get(tableColumn.getName()));
        }
        // 编辑字段
        if (!ArrayUtil.contains(GenCodeConstant.NOT_EDIT, name) && !column.isPk() && !tableColumn.getIsLogicDeleteField()) {
            tableColumn.setIsEdit(true);
        }
        // 列表字段
        if (!ArrayUtil.contains(GenCodeConstant.NOT_LIST, name) && !column.isPk() && !tableColumn.getIsLogicDeleteField()) {
            tableColumn.setIsList(true);
        }
        // 查询字段
        if (!ArrayUtil.contains(GenCodeConstant.NOT_QUERY, name) && !column.isPk() && !tableColumn.getIsLogicDeleteField()) {
            tableColumn.setIsQuery(true);
        }
        tableColumn.setEditDefValue(column.getColumnDef());
        return tableColumn;
    }

    /**
     * @param comment 列描述
     */
    private static String getSwaggerComment(String comment) {
        String swaggerComment = StrUtil.isBlank(comment) ? StrUtil.EMPTY : StrUtil.trim(comment);
        if (swaggerComment.contains(StrPool.SEMICOLON)) {
            swaggerComment = StrUtil.subBefore(swaggerComment, StrPool.SEMICOLON, false);
        }
        // 若含有换行符，替换为空格
        // \n 是mysql注释中的换行符，其他数据库的换行符需要自己改这里代码适配下
        swaggerComment = StrUtil.replace(swaggerComment, "\n", " ");
        return swaggerComment;
    }

    /**
     * @param name     列名称
     * @param strategy 策略
     * @param prefix   前缀
     * @param suffix   后缀
     * @return java.lang.String
     * @date 2023/4/16 10:45 PM
     */
    private static String processName(String name, NamingStrategy strategy, List<String> prefix, List<String> suffix) {
        String propertyName = name;
        // 删除前缀
        if (prefix.size() > 0) {
            propertyName = NamingStrategy.removePrefix(propertyName, prefix);
        }
        // 删除后缀
        if (suffix.size() > 0) {
            propertyName = NamingStrategy.removeSuffix(propertyName, suffix);
        }
        if (StringUtils.isBlank(propertyName)) {
            throw new RuntimeException(String.format("%s 的名称转换结果为空，请检查是否配置问题", name));
        }
        // 下划线转驼峰
        if (NamingStrategy.underline_to_camel.equals(strategy)) {
            return NamingStrategy.underlineToCamel(propertyName);
        }
        return propertyName;
    }


}
