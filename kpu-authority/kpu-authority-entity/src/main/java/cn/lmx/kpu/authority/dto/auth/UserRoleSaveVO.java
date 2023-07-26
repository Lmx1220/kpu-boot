package cn.lmx.kpu.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色分配
 * 账号角色绑定
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
@ApiModel(value = "UserRoleSaveVO", description = "角色分配 账号角色绑定")
public class UserRoleSaveVO implements Serializable {


    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "绑定或取消")
    @NotNull(message = "请填写绑定或取消参数")
    private Boolean flag;
    /**
     * 角色;#c_role
     */
    @ApiModelProperty(value = "用户")
    @NotNull(message = "请选择用户")
    private Long userId;
    /**
     * 用户;#c_user
     */
    @ApiModelProperty(value = "角色")
    @Size(min = 1, message = "请选择角色")
    private List<Long> roleIdList;

}
