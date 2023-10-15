package cn.lmx.kpu.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.lmx.basic.base.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName("c_role_org")
@ApiModel(value = "RoleOrg", description = "角色组织关系")
@AllArgsConstructor
public class RoleOrg extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     * #c_role
     */
    @ApiModelProperty(value = "角色ID")
    @NotNull(message = "角色ID不能为空")
    @TableField("role_id")
    @Excel(name = "角色ID")
    private Long roleId;

    /**
     * 部门ID
     * #c_org
     */
    @ApiModelProperty(value = "部门ID")
    @NotNull(message = "部门ID不能为空")
    @TableField("org_id")
    @Excel(name = "部门ID")
    private Long orgId;


    @Builder
    public RoleOrg(Long id, LocalDateTime createdTime, Long createdBy,
                   Long roleId, Long orgId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.roleId = roleId;
        this.orgId = orgId;
    }

}
