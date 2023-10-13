package cn.lmx.kpu.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.lmx.basic.base.entity.SuperEntity;
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
 * 角色分配
 * 账号角色绑定
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
@TableName("c_user_role")
@ApiModel(value = "UserRole", description = "角色分配")
@AllArgsConstructor
public class UserRole extends SuperEntity<Long> {

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
     * 用户ID
     * #c_user
     */
    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "用户ID不能为空")
    @TableField("user_id")
    @Excel(name = "用户ID")
    private Long userId;


    @Builder
    public UserRole(Long id, Long createdBy, LocalDateTime createdTime,
                    Long roleId, Long userId) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.roleId = roleId;
        this.userId = userId;
    }

}
