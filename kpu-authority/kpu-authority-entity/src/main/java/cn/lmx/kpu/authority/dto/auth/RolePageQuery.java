package cn.lmx.kpu.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色
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
@ApiModel(value = "RolePageQuery", description = "角色")
public class RolePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;
    /**
     * 是否绑定范围
     */
    @ApiModelProperty(value = "范围")
    private String scope;
    /**
     * 范围类型
     */
    @ApiModelProperty(value = "范围类型")
    private String scopeType;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
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
     * 内置角色
     */
    @ApiModelProperty(value = "内置角色")
    private List<Boolean> readonly;
    /**
     * 角色类别;[10-功能角色 20-桌面角色 30-数据角色]
     */
    @ApiModelProperty(value = "角色类别")
    private String category;

}
