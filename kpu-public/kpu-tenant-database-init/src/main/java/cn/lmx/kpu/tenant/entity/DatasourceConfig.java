package cn.lmx.kpu.tenant.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.kpu.model.enumeration.system.TenantConnectTypeEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static cn.lmx.kpu.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 数据源
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
@TableName("c_datasource_config")
@ApiModel(value = "DatasourceConfig", description = "数据源")
@AllArgsConstructor
public class DatasourceConfig extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称")
    private String name;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 255, message = "账号长度不能超过255")
    @TableField(value = "username", condition = LIKE)
    @Excel(name = "账号")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Size(max = 255, message = "密码长度不能超过255")
    @TableField(value = "password", condition = LIKE)
    @Excel(name = "密码")
    private String password;

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    @NotEmpty(message = "链接不能为空")
    @Size(max = 255, message = "链接长度不能超过255")
    @TableField(value = "url", condition = LIKE)
    @Excel(name = "链接")
    private String url;

    /**
     * 驱动
     */
    @ApiModelProperty(value = "驱动")
    @NotEmpty(message = "驱动不能为空")
    @Size(max = 255, message = "驱动长度不能超过255")
    @TableField(value = "driver_class_name", condition = LIKE)
    @Excel(name = "驱动")
    private String driverClassName;

    @ApiModelProperty(value = "数据源名")
    @TableField(exist = false)
    private String poolName;

    @TableField(exist = false)
    private TenantConnectTypeEnum type;

    @Builder
    public DatasourceConfig(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                            String name, String username, String password, String url, String driverClassName) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.name = name;
        this.username = username;
        this.password = password;
        this.url = url;
        this.driverClassName = driverClassName;
    }

}
