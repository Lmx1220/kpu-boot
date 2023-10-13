package cn.lmx.kpu.generator.vo.update;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.kpu.generator.enumeration.SqlConditionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 代码生成字段
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "GenTableColumnUpdateVO", description = "代码生成字段")
public class GenTableColumnUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "请填写ID", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 所属表ID
     */
    @ApiModelProperty(value = "所属表ID")
    @NotNull(message = "请填写所属表ID")
    private Long tableId;
    /**
     * 列名称
     */
    @ApiModelProperty(value = "列名称")
    @NotEmpty(message = "请填写列名称")
    @Size(max = 255, message = "列名称长度不能超过{max}")
    private String name;
    /**
     * 列描述
     */
    @ApiModelProperty(value = "列描述")
    @NotEmpty(message = "请填写列描述")
    @Size(max = 500, message = "列描述长度不能超过{max}")
    private String comment;
    /**
     * swagger 描述
     */
    @ApiModelProperty(value = "swagger 描述")
    @NotEmpty(message = "请填写swagger 描述")
    @Size(max = 255, message = "swagger 描述长度不能超过{max}")
    private String swaggerComment;
    /**
     * 列类型
     */
    @ApiModelProperty(value = "列类型")
    @NotEmpty(message = "请填写列类型")
    @Size(max = 255, message = "列类型长度不能超过{max}")
    private String type;
    /**
     * JAVA类型
     */
    @ApiModelProperty(value = "JAVA类型")
    @NotEmpty(message = "请填写JAVA类型")
    @Size(max = 500, message = "JAVA类型长度不能超过{max}")
    private String javaType;
    /**
     * JAVA字段名
     */
    @ApiModelProperty(value = "JAVA字段名")
    @NotEmpty(message = "请填写JAVA字段名")
    @Size(max = 255, message = "JAVA字段名长度不能超过{max}")
    private String javaField;
    /**
     * TS类型
     */
    @ApiModelProperty(value = "TS类型")
    @NotEmpty(message = "请填写TS类型")
    @Size(max = 255, message = "TS类型长度不能超过{max}")
    private String tsType;
    /**
     * 长度
     */
    @ApiModelProperty(value = "长度")
    @NotNull(message = "请填写长度")
    private Integer size;
    /**
     * 小数位数
     */
    @ApiModelProperty(value = "小数位数")
    private Integer digit;
    /**
     * 是否主键
     */
    @ApiModelProperty(value = "是否主键")
    @NotNull(message = "请填写是否主键")
    private Boolean isPk;
    /**
     * 是否自增
     */
    @ApiModelProperty(value = "是否自增")
    @NotNull(message = "请填写是否自增")
    private Boolean isIncrement;
    /**
     * 是否必填
     */
    @ApiModelProperty(value = "是否必填")
    @NotNull(message = "请填写是否必填")
    private Boolean isRequired;
    /**
     * 是否为逻辑删除字段
     */
    @ApiModelProperty(value = "是否为逻辑删除字段")
    @NotNull(message = "请填写是否为逻辑删除字段")
    private Boolean isLogicDeleteField;
    /**
     * 是否为乐观锁字段
     */
    @ApiModelProperty(value = "是否为乐观锁字段")
    @NotNull(message = "请填写是否为乐观锁字段")
    private Boolean isVersionField;
    /**
     * 填充类型
     */
    @ApiModelProperty(value = "填充类型")
    @Size(max = 255, message = "填充类型长度不能超过{max}")
    private String fill;
    /**
     * 是否编辑字段
     */
    @ApiModelProperty(value = "是否编辑字段")
    @NotNull(message = "请填写是否编辑字段")
    private Boolean isEdit;
    /**
     * 是否列表字段
     */
    @ApiModelProperty(value = "是否列表字段")
    @NotNull(message = "请填写是否列表字段")
    private Boolean isList;
    /**
     * 是否查询字段
     */
    @ApiModelProperty(value = "是否查询字段")
    @NotNull(message = "请填写是否查询字段")
    private Boolean isQuery;
    /**
     * 宽度
     */
    @ApiModelProperty(value = "宽度")
    @Size(max = 255, message = "宽度长度不能超过{max}")
    private String width;
    /**
     * 查询方式;
     * #SqlConditionEnum{EQUAL:01}
     * （等于、不等于、大于、小于、范围）
     */
    @ApiModelProperty(value = "查询方式")
    private SqlConditionEnum queryType;
    /**
     * 显示组件;
     * （文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @ApiModelProperty(value = "显示组件")
    @Size(max = 200, message = "显示组件长度不能超过{max}")
    @NotEmpty(message = "请填写显示组件")
    private String component;

    @ApiModelProperty(value = "VXE显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    @Size(max = 255, message = "VXE显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）长度不能超过{max}")
    @NotEmpty(message = "请填写VXE显示组件")
    private String vxeComponent;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    @Size(max = 255, message = "字典类型长度不能超过{max}")
    private String dictType;
    /**
     * @Echo
     */
    @ApiModelProperty(value = "@Echo")
    @Size(max = 255, message = "@Echo长度不能超过{max}")
    private String echoStr;
    /**
     * 枚举
     */
    @ApiModelProperty(value = "枚举")
    @Size(max = 255, message = "枚举长度不能超过{max}")
    private String enumStr;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    /**
     * 编辑页面默认值
     */
    @ApiModelProperty(value = "编辑页面默认值")
    @Size(max = 255, message = "编辑页面默认值长度不能超过{max}")
    private String editDefValue;
    /**
     * 编辑页面提示信息
     */
    @ApiModelProperty(value = "编辑页面提示信息")
    @Size(max = 255, message = "编辑页面提示信息长度不能超过{max}")
    private String editHelpMessage;
    /**
     * 主页提示信息
     */
    @ApiModelProperty(value = "主页提示信息")
    @Size(max = 255, message = "主页提示信息长度不能超过{max}")
    private String indexHelpMessage;


}
