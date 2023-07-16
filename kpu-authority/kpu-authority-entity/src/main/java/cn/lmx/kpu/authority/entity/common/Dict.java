package cn.lmx.kpu.authority.entity.common;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.lmx.basic.base.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static cn.lmx.kpu.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 字典项
 * </p>
 *
 * @author lmx
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(of = {"type", "code"})
@Accessors(chain = true)
@Builder
@TableName("c_dict")
@ApiModel(value = "Dict", description = "字典项")
@AllArgsConstructor
public class Dict extends Entity<Long> {

    private static final long serialVersionUID = 1L;
    /**
     * 字典Id
     */
    @ApiModelProperty(value = "字典Id")
    @Size(max = 255, message = "字典Id长度不能超过255")
    @TableField(value = "parent_id")
    @Excel(name = "字典Id")
    private Long parentId;

    /**
     * 字典Key
     */
    @ApiModelProperty(value = "字典Key")
    @Size(max = 255, message = "字典Key长度不能超过255")
    @TableField(value = "parent_key")
    @Excel(name = "字典Key")
    private String parentKey;
    /**
     * 分类
     */
    @ApiModelProperty(value = "分类")
    @NotEmpty(message = "分类不能为空")
    @Size(max = 255, message = "分类长度不能超过255")
    @TableField(value = "classify")
    @Excel(name = "分类")
    private String classify;

    /**
     * 标识
     */
    @ApiModelProperty(value = "标识")
    @NotEmpty(message = "标识不能为空")
    @Size(max = 255, message = "类型长度不能超过255")
    @TableField(value = "key_")
    @Excel(name = "标识")
    private String key;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 64, message = "名称长度不能超过64")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称")
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    @TableField(value = "remark", condition = LIKE)
    @Excel(name = "描述")
    private String remark;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    @Excel(name = "排序")
    private Integer sortValue;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Size(max = 255, message = "图标长度不能超过255")
    @TableField(value = "icon", condition = LIKE)
    @Excel(name = "图标")
    private String icon;

    /**
     * css样式
     */
    @ApiModelProperty(value = "css样式")
    @Size(max = 255, message = "css样式长度不能超过255")
    @TableField(value = "css_style", condition = LIKE)
    @Excel(name = "css样式")
    private String cssStyle;

    /**
     * 类选择器
     */
    @ApiModelProperty(value = "类选择器")
    @Size(max = 255, message = "类选择器长度不能超过255")
    @TableField(value = "css_class", condition = LIKE)
    @Excel(name = "类选择器")
    private String cssClass;




}
