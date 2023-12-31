package cn.lmx.kpu.authority.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ParameterPageQuery", description = "参数配置")
public class ParameterPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参数键
     */
    @ApiModelProperty(value = "参数键")
    private String key;
    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    private String value;
    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    private String name;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
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
