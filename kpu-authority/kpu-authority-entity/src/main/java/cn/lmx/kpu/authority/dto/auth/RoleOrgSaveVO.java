package cn.lmx.kpu.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色组织关系
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
@ApiModel(value = "RoleOrgSaveVO", description = "角色组织关系")
public class RoleOrgSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "绑定或取消")
    @NotNull(message = "请填写绑定或取消参数")
    private Boolean flag;
    /**
     * 角色ID
     * #c_role
     */
    @ApiModelProperty(value = "角色ID")
    @NotNull(message = "角色ID不能为空")
    private List<Long> roleIdList;
    /**
     * 部门ID
     * #c_org
     */
    @ApiModelProperty(value = "部门ID")
    @NotNull(message = "部门ID不能为空")
    private Long orgId;

}
