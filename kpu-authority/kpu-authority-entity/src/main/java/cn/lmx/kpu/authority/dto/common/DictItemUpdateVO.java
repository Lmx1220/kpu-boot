package cn.lmx.kpu.authority.dto.common;

import cn.lmx.basic.base.entity.SuperEntity;
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
 * 实体类
 * 字典项
 * </p>
 *
 * @author lmx
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "DictUpdateVo", description = "字典项")
public class DictItemUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 类型
     */
    @ApiModelProperty(value = "字典ID")
    @NotEmpty(message = "字典ID不能为空")
    private Long parentId;
    /**
     * 编码
     */
    @ApiModelProperty(value = "标识")
    @NotEmpty(message = "标识不能为空")
    @Size(max = 64, message = "标识长度不能超过64")
    private String key;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 64, message = "名称长度不能超过64")
    private String name;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    private String remark;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Size(max = 255, message = "图标长度不能超过255")
    private String icon;
    /**
     * css样式
     */
    @ApiModelProperty(value = "css样式")
    @Size(max = 255, message = "css样式长度不能超过255")
    private String cssStyle;
    /**
     * 类选择器
     */
    @ApiModelProperty(value = "类选择器")
    @Size(max = 255, message = "类选择器长度不能超过255")
    private String cssClass;


}
