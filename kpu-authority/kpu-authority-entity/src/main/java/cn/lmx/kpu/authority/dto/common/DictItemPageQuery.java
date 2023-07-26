package cn.lmx.kpu.authority.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 字典项
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
@ApiModel(value = "DictItemPageQuery", description = "查询参数")
public class DictItemPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 标识
     */
    @ApiModelProperty(value = "字典ID")
    @NotNull(message = "请选择字典")
    private Long parentId;

    /**
     * 类型
     */
    @ApiModelProperty(value = "分类")
    private String[] classify;
    /**
     * 标识
     */
    @ApiModelProperty(value = "标识")
    private String key;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean[] state;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remark;
    /**
     * 描述
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;

}
