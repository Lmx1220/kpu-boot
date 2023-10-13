package cn.lmx.kpu.generator.vo.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 代码生成字段
 * </p>
 *
 * @author lmx
 * @since 2023/10/13 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "GenTableColumnSaveVO", description = "代码生成字段")
public class GenTableColumnSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 归属表编号
     */
    @ApiModelProperty(value = "归属表编号")
    @Size(max = 64, message = "归属表编号长度不能超过{max}")
    private String tableId;
    /**
     * 列名称
     */
    @ApiModelProperty(value = "列名称")
    @Size(max = 200, message = "列名称长度不能超过{max}")
    private String columnName;
    /**
     * 列描述
     */
    @ApiModelProperty(value = "列描述")
    @Size(max = 500, message = "列描述长度不能超过{max}")
    private String columnComment;
    /**
     * 列类型
     */
    @ApiModelProperty(value = "列类型")
    @Size(max = 100, message = "列类型长度不能超过{max}")
    private String columnType;
    /**
     * JAVA类型
     */
    @ApiModelProperty(value = "JAVA类型")
    @Size(max = 500, message = "JAVA类型长度不能超过{max}")
    private String javaType;
    /**
     * JAVA字段名
     */
    @ApiModelProperty(value = "JAVA字段名")
    @Size(max = 200, message = "JAVA字段名长度不能超过{max}")
    private String javaField;
    /**
     * 是否主键（1是）
     */
    @ApiModelProperty(value = "是否主键（1是）")
    @Size(max = 1, message = "是否主键（1是）长度不能超过{max}")
    private String isPk;
    /**
     * 是否自增（1是）
     */
    @ApiModelProperty(value = "是否自增（1是）")
    @Size(max = 1, message = "是否自增（1是）长度不能超过{max}")
    private String isIncrement;
    /**
     * 是否必填（1是）
     */
    @ApiModelProperty(value = "是否必填（1是）")
    @Size(max = 1, message = "是否必填（1是）长度不能超过{max}")
    private String isRequired;
    /**
     * 是否为插入字段（1是）
     */
    @ApiModelProperty(value = "是否为插入字段（1是）")
    @Size(max = 1, message = "是否为插入字段（1是）长度不能超过{max}")
    private String isInsert;
    /**
     * 是否编辑字段（1是）
     */
    @ApiModelProperty(value = "是否编辑字段（1是）")
    @Size(max = 1, message = "是否编辑字段（1是）长度不能超过{max}")
    private String isEdit;
    /**
     * 是否列表字段（1是）
     */
    @ApiModelProperty(value = "是否列表字段（1是）")
    @Size(max = 1, message = "是否列表字段（1是）长度不能超过{max}")
    private String isList;
    /**
     * 是否查询字段（1是）
     */
    @ApiModelProperty(value = "是否查询字段（1是）")
    @Size(max = 1, message = "是否查询字段（1是）长度不能超过{max}")
    private String isQuery;
    /**
     * 宽度
     */
    @ApiModelProperty(value = "宽度")
    @Size(max = 255, message = "宽度长度不能超过{max}")
    private String width;
    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    @ApiModelProperty(value = "查询方式（等于、不等于、大于、小于、范围）")
    @Size(max = 200, message = "查询方式（等于、不等于、大于、小于、范围）长度不能超过{max}")
    private String queryType;
    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @ApiModelProperty(value = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    @Size(max = 200, message = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）长度不能超过{max}")
    private String component;

    @ApiModelProperty(value = "VXE显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    @Size(max = 255, message = "VXE显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）长度不能超过{max}")
    private String vxeComponent;

    /**
     * 枚举类型
     */
    @ApiModelProperty(value = "枚举类型")
    @Size(max = 255, message = "枚举类型长度不能超过{max}")
    private String enumType;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    @Size(max = 200, message = "字典类型长度不能超过{max}")
    private String dictType;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    @ApiModelProperty(value = "")
    @Size(max = 255, message = "长度不能超过{max}")
    private String editDefValue;
    @ApiModelProperty(value = "")
    @Size(max = 255, message = "长度不能超过{max}")
    private String editHelpMessage;

}
