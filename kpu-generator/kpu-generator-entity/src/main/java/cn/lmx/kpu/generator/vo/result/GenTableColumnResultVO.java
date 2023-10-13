package cn.lmx.kpu.generator.vo.result;


import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.generator.enumeration.SqlConditionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "GenTableColumnResultVO", description = "代码生成字段")
public class GenTableColumnResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 所属表ID
     */
    @ApiModelProperty(value = "所属表ID")
    private Long tableId;
    /**
     * 列名称
     */
    @ApiModelProperty(value = "列名称")
    private String name;
    /**
     * 列描述
     */
    @ApiModelProperty(value = "列描述")
    private String comment;
    /**
     * swagger 描述
     */
    @ApiModelProperty(value = "swagger 描述")
    private String swaggerComment;
    /**
     * 列类型
     */
    @ApiModelProperty(value = "列类型")
    private String type;
    /**
     * JAVA类型
     */
    @ApiModelProperty(value = "JAVA类型")
    private String javaType;
    /**
     * JAVA字段名
     */
    @ApiModelProperty(value = "JAVA字段名")
    private String javaField;
    /**
     * TS类型
     */
    @ApiModelProperty(value = "TS类型")
    private String tsType;
    /**
     * 长度
     */
    @ApiModelProperty(value = "长度")
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
    private Boolean isPk;
    /**
     * 是否自增
     */
    @ApiModelProperty(value = "是否自增")
    private Boolean isIncrement;
    /**
     * 是否必填
     */
    @ApiModelProperty(value = "是否必填")
    private Boolean isRequired;
    /**
     * 是否为逻辑删除字段
     */
    @ApiModelProperty(value = "是否为逻辑删除字段")
    private Boolean isLogicDeleteField;
    /**
     * 是否为乐观锁字段
     */
    @ApiModelProperty(value = "是否为乐观锁字段")
    private Boolean isVersionField;
    /**
     * 填充类型
     */
    @ApiModelProperty(value = "填充类型")
    private String fill;
    /**
     * 是否编辑字段
     */
    @ApiModelProperty(value = "是否编辑字段")
    private Boolean isEdit;
    /**
     * 是否列表字段
     */
    @ApiModelProperty(value = "是否列表字段")
    private Boolean isList;
    /**
     * 是否查询字段
     */
    @ApiModelProperty(value = "是否查询字段")
    private Boolean isQuery;
    /**
     * 宽度
     */
    @ApiModelProperty(value = "宽度")
    private String width;
    /**
     * 查询方式;
     * #SqlConditionEnum{EQUAL:01}
     * （等于、不等于、大于、小于、范围）
     */
    @ApiModelProperty(value = "查询方式")
    @Echo(api = Echo.ENUM_API)
    private SqlConditionEnum queryType;
    /**
     * 显示组件;
     * （文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @ApiModelProperty(value = "显示组件")
    private String component;

    @ApiModelProperty(value = "Vxe显示组件")
    private String vxeComponent;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;
    /**
     * @Echo
     */
    @ApiModelProperty(value = "@Echo")
    private String echoStr;
    /**
     * 枚举
     */
    @ApiModelProperty(value = "枚举")
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
    private String editDefValue;
    /**
     * 编辑页面提示信息
     */
    @ApiModelProperty(value = "编辑页面提示信息")
    private String editHelpMessage;
    /**
     * 主页提示信息
     */
    @ApiModelProperty(value = "主页提示信息")
    private String indexHelpMessage;


}
