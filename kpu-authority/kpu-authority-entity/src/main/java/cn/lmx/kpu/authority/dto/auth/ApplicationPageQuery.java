package cn.lmx.kpu.authority.dto.auth;

import cn.lmx.kpu.authority.enumeration.auth.ApplicationAppTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * 表单查询条件VO
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
@ApiModel(value = "ApplicationPageQuery", description = "应用")
public class ApplicationPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 客户端ID
    */
    @ApiModelProperty(value = "客户端ID")
    private String clientId;
    /**
    * 客户端密码
    */
    @ApiModelProperty(value = "客户端密码")
    private String clientSecret;
    /**
    * 官网
    */
    @ApiModelProperty(value = "官网")
    private String website;
    /**
    * 应用名称
    */
    @ApiModelProperty(value = "应用名称")
    private String name;
    /**
    * 应用图标
    */
    @ApiModelProperty(value = "应用图标")
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
    private String remarks;
    /**
    * 状态
    */
    @ApiModelProperty(value = "状态")
    private Boolean state;



}
