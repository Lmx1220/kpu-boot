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
 * 参数配置
 * </p>
 *
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "ParameterUpdateVo", description = "参数配置")
public class ParameterUpdateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 参数键
     */
    @ApiModelProperty(value = "参数键")
    @NotEmpty(message = "参数键不能为空")
    @Size(max = 255, message = "参数键长度不能超过255")
    private String key;
    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    @NotEmpty(message = "参数值不能为空")
    @Size(max = 255, message = "参数值长度不能超过255")
    private String value;
    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    @NotEmpty(message = "参数名称不能为空")
    @Size(max = 255, message = "参数名称长度不能超过255")
    private String name;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    private String remarks;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    private Boolean readonly;
}
