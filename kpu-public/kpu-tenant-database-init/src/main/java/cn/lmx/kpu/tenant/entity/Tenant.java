package cn.lmx.kpu.tenant.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.kpu.model.enumeration.system.TenantConnectTypeEnum;
import cn.lmx.kpu.model.enumeration.system.TenantStatusEnum;
import cn.lmx.kpu.model.enumeration.system.TenantTypeEnum;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static cn.lmx.basic.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;
import static cn.lmx.kpu.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 企业
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
@TableName("c_tenant")
@ApiModel(value = "Tenant", description = "企业")
@AllArgsConstructor
public class Tenant extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    @NotEmpty(message = "企业编码不能为空")
    @Size(max = 20, message = "企业编码长度不能超过20")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "企业编码", width = 20)
    private String code;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Size(max = 255, message = "企业名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "企业名称", width = 20)
    private String name;

    /**
     * 类型
     * #{CREATE:创建;REGISTER:注册}
     */
    @ApiModelProperty(value = "类型")
    @TableField("type")
    @Excel(name = "类型", width = 20, replace = {"创建_CREATE", "注册_REGISTER", "_null"})
    @Echo(api = Echo.ENUM_API)
    private TenantTypeEnum type;

    /**
     * 连接类型
     * #TenantConnectTypeEnum{LOCAL:本地;REMOTE:远程}
     */
    @ApiModelProperty(value = "连接类型")
    @TableField("connect_type")
    @Excel(name = "连接类型", width = 20, replace = {"本地_LOCAL", "远程_REMOTE", "_null"})
    @Echo(api = Echo.ENUM_API)
    private TenantConnectTypeEnum connectType;

    /**
     * 状态
     * #{NORMAL:正常;WAIT_INIT:待初始化;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝;DELETE:已删除}
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    @Excel(name = "状态", width = 20, replace = {"正常_NORMAL", "待初始化_WAIT_INIT", "禁用_FORBIDDEN", "待审核_WAITING", "拒绝_REFUSE", "已删除_DELETE", "_null"})
    @Echo(api = Echo.ENUM_API)
    private TenantStatusEnum status;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    @TableField("readonly_")
    @Excel(name = "内置", replace = {"是_true", "否_false", "_null"})
    private Boolean readonly;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人")
    @Size(max = 50, message = "责任人长度不能超过50")
    @TableField(value = "duty", condition = LIKE)
    @Excel(name = "责任人")
    private String duty;

    /**
     * 有效期
     * 为空表示永久
     */
    @ApiModelProperty(value = "有效期")
    @TableField(value = "expiration_time", updateStrategy = FieldStrategy.IGNORED)
    @Excel(name = "有效期", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime expirationTime;

    /**
     * logo地址
     */
    @ApiModelProperty(value = "logo地址")
    @Size(max = 255, message = "logo地址长度不能超过255")
    @TableField(value = "logo", condition = LIKE)
    private String logo;

    /**
     * 企业简介
     */
    @ApiModelProperty(value = "企业简介")
    @Size(max = 255, message = "企业简介长度不能超过255")
    @TableField(value = "remarks", condition = LIKE)
    @Excel(name = "企业简介", width = 20)
    private String remarks;


    @Builder
    public Tenant(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                  String code, String name, TenantTypeEnum type, TenantConnectTypeEnum connectType, TenantStatusEnum status,
                  Boolean readonly, String duty, LocalDateTime expirationTime, String logo, String remarks) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.code = code;
        this.name = name;
        this.type = type;
        this.connectType = connectType;
        this.status = status;
        this.readonly = readonly;
        this.duty = duty;
        this.expirationTime = expirationTime;
        this.logo = logo;
        this.remarks = remarks;
    }

}
