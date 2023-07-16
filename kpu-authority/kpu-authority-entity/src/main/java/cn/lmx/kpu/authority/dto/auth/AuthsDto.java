package cn.lmx.kpu.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/07  16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "AuthsDto", description = "权限")
public class AuthsDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 权限Sort
     */
    @ApiModelProperty(value = "权限Sort")
    private Integer sortValue;
    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String code;
}
