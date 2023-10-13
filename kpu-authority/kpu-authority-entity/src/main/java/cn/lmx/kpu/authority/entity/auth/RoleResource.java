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
 * 角色的资源
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
@TableName("c_role_resource")
@ApiModel(value = "RoleResource", description = "角色的资源")
@AllArgsConstructor
public class RoleResource extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     * #c_resource #c_resource
     */
    @ApiModelProperty(value = "资源id")
    @NotNull(message = "资源id不能为空")
    @TableField("resource_id")
    @Excel(name = "资源id")
    private Long resourceId;

    /**
     * 角色id
     * #c_role
     */
    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空")
    @TableField("role_id")
    @Excel(name = "角色id")
    private Long roleId;


    @Builder
    public RoleResource(Long id, LocalDateTime createdTime, Long createdBy,
                        Long resourceId, Long roleId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.resourceId = resourceId;
        this.roleId = roleId;
    }

}
