package cn.lmx.kpu.generator.entity;

import cn.lmx.basic.base.entity.Entity;
import cn.lmx.kpu.generator.enumeration.SqlConditionEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import static cn.lmx.kpu.model.constant.Condition.LIKE;
import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  09:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("c_gen_table_column")
public class GenTableColumn extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 所属表ID
     */
    @TableField(value = "table_id", condition = EQUAL)
    private Long tableId;
    /**
     * 列名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 列描述
     */
    @TableField(value = "comment_", condition = LIKE)
    private String comment;
    /**
     * 文档描述
     */
    @TableField(value = "swagger_comment", condition = LIKE)
    private String swaggerComment;
    /**
     * 列类型
     */
    @TableField(value = "type", condition = LIKE)
    private String type;
    /**
     * JAVA类型
     */
    @TableField(value = "java_type", condition = LIKE)
    private String javaType;
    /**
     * JAVA字段名
     */
    @TableField(value = "java_field", condition = LIKE)
    private String javaField;
    /**
     * TS类型
     */
    @TableField(value = "ts_type", condition = LIKE)
    private String tsType;
    /**
     * 长度
     */
    @TableField(value = "size_", condition = EQUAL)
    private Long size;
    /**
     * 小数位数
     */
    @TableField(value = "digit", condition = EQUAL)
    private Integer digit;
    /**
     * 主键
     */
    @TableField(value = "is_pk", condition = EQUAL)
    private Boolean isPk;
    /**
     * 自增
     */
    @TableField(value = "is_increment", condition = EQUAL)
    private Boolean isIncrement;
    /**
     * 必填
     */
    @TableField(value = "is_required", condition = EQUAL)
    private Boolean isRequired;
    /**
     * 逻辑删除
     */
    @TableField(value = "is_logic_delete_field", condition = EQUAL)
    private Boolean isLogicDeleteField;
    /**
     * 乐观锁
     */
    @TableField(value = "is_version_field", condition = EQUAL)
    private Boolean isVersionField;
    /**
     * 填充类型;
     * #FieldFill{INSERT:1}
     */
    @TableField(value = "fill", condition = EQUAL)
    private FieldFill fill;
    /**
     * 编辑
     */
    @TableField(value = "is_edit", condition = EQUAL)
    private Boolean isEdit;
    /**
     * 列表
     */
    @TableField(value = "is_list", condition = EQUAL)
    private Boolean isList;
    /**
     * 查询
     */
    @TableField(value = "is_query", condition = EQUAL)
    private Boolean isQuery;
    /**
     * 宽度
     */
    @TableField(value = "width", condition = LIKE)
    private String width;
    /**
     * 查询方式;
     * #SqlConditionEnum{EQUAL:01}
     * （等于、不等于、大于、小于、范围）
     */
    @TableField(value = "query_type", condition = EQUAL)
    private SqlConditionEnum queryType;
    /**
     * 组件;（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @TableField(value = "component", condition = LIKE)
    private String component;
    /**
     * Vxe组件;（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @TableField(value = "vxe_component", condition = LIKE)
    private String vxeComponent;
    /**
     * 字典类型
     */
    @TableField(value = "dict_type", condition = LIKE)
    private String dictType;
    /**
     * Echo
     */
    @TableField(value = "echo_str", condition = LIKE)
    private String echoStr;
    /**
     * 枚举
     */
    @TableField(value = "enum_str", condition = LIKE)
    private String enumStr;
    /**
     * 排序
     */
    @TableField(value = "sort_value", condition = EQUAL)
    private Integer sortValue;
    /**
     * 默认值
     */
    @TableField(value = "edit_def_value", condition = LIKE)
    private String editDefValue;
    /**
     * 提示信息
     */
    @TableField(value = "edit_help_message", condition = LIKE)
    private String editHelpMessage;
    /**
     * 列表提示信息
     */
    @TableField(value = "index_help_message", condition = LIKE)
    private String indexHelpMessage;


}
