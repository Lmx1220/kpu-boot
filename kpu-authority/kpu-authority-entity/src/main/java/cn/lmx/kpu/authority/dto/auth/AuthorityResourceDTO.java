package cn.lmx.kpu.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 拥有拥有的权限资源
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "AuthorityResourceDTO", description = "权限资源")
public class AuthorityResourceDTO implements Serializable {
    @ApiModelProperty(value = "是否启用URI/按钮权限")
    private Boolean enabled;
    @ApiModelProperty(value = "是否区分大小写")
    private Boolean caseSensitive;
    @ApiModelProperty(value = "拥有的资源编码")
    private List<String> resourceList;
    @ApiModelProperty(value = "拥有的角色编码")
    private List<String> roleList;
}
