package cn.lmx.kpu.authority.dto.auth;

import cn.lmx.kpu.authority.enumeration.auth.ApplicationAppTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 表单保存方法VO
 * 应用
 * </p>
 *
 * @author lmx
 * @date 2023-10-24 11:41:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "ApplicationSaveVO", description = "应用")
public class ApplicationSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    @Size(max = 24, message = "客户端ID长度不能超过{max}")
    private String clientId;
    /**
     * 客户端密码
     */
    @ApiModelProperty(value = "客户端密码")
    @Size(max = 32, message = "客户端密码长度不能超过{max}")
    private String clientSecret;
    /**
     * 官网
     */
    @ApiModelProperty(value = "官网")
    @Size(max = 100, message = "官网长度不能超过{max}")
    private String website;
    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    @NotEmpty(message = "请填写应用名称")
    @Size(max = 255, message = "应用名称长度不能超过{max}")
    private String name;
    /**
     * 应用图标
     */
    @ApiModelProperty(value = "应用图标")
    @Size(max = 255, message = "应用图标长度不能超过{max}")
    private String icon;
    /**
     * 类型; #{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}
     */
    @ApiModelProperty(value = "类型")
    private ApplicationAppTypeEnum appType;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 200, message = "备注长度不能超过{max}")
    private String remarks;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;



}
