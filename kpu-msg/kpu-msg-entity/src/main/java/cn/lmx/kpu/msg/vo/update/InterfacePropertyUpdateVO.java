package cn.lmx.kpu.msg.vo.update;

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
 * 表单修改方法VO
 * 接口属性
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "InterfacePropertyUpdateVO", description = "接口属性")
public class InterfacePropertyUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "请填写ID", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 接口ID
     */
    @ApiModelProperty(value = "接口ID")
    @NotNull(message = "请填写接口ID")
    private Long interfaceId;
    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    @NotEmpty(message = "请填写参数名称")
    @Size(max = 255, message = "参数名称长度不能超过{max}")
    private String name;
    /**
     * 参数键
     */
    @ApiModelProperty(value = "参数键")
    @NotEmpty(message = "请填写参数键")
    @Size(max = 255, message = "参数键长度不能超过{max}")
    private String key;
    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    @NotEmpty(message = "请填写参数值")
    @Size(max = 255, message = "参数值长度不能超过{max}")
    private String value;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer sortValue;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注长度不能超过{max}")
    private String remarks;


}
