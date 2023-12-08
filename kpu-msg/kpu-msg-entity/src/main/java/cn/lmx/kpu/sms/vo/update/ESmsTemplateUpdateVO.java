package cn.lmx.kpu.sms.vo.update;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.kpu.sms.enumeration.ProviderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 短信模板
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "ESmsTemplateUpdateVO", description = "短信模板")
public class ESmsTemplateUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板ID")
    @NotNull(message = "请填写模板ID", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 供应商类型 
     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
     */
    @ApiModelProperty(value = "供应商类型  #ProviderType{ALI:OK,阿里云短信")
    @NotNull(message = "请填写供应商类型  #ProviderType{ALI:OK,阿里云短信")
    private ProviderType providerType;
    /**
     * 应用ID
     */
    @ApiModelProperty(value = "应用ID")
    @NotEmpty(message = "请填写应用ID")
    @Size(max = 255, message = "应用ID长度不能超过{max}")
    private String appId;
    /**
     * 应用密码
     */
    @ApiModelProperty(value = "应用密码")
    @NotEmpty(message = "请填写应用密码")
    @Size(max = 255, message = "应用密码长度不能超过{max}")
    private String appSecret;
    /**
     * SMS服务域名 
     * 百度、其他厂商会用
     */
    @ApiModelProperty(value = "SMS服务域名  百度、其他厂商会用")
    @Size(max = 255, message = "SMS服务域名  百度、其他厂商会用长度不能超过{max}")
    private String url;
    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    @Size(max = 255, message = "模板名称长度不能超过{max}")
    private String name;
    /**
     * 模板内容
     */
    @ApiModelProperty(value = "模板内容")
    @NotEmpty(message = "请填写模板内容")
    @Size(max = 255, message = "模板内容长度不能超过{max}")
    private String content;
    /**
     * 模板参数
     */
    @ApiModelProperty(value = "模板参数")
    @NotEmpty(message = "请填写模板参数")
    @Size(max = 255, message = "模板参数长度不能超过{max}")
    private String templateParams;
    /**
     * 模板编码
     */
    @ApiModelProperty(value = "模板编码")
    @NotEmpty(message = "请填写模板编码")
    @Size(max = 50, message = "模板编码长度不能超过{max}")
    private String templateCode;
    /**
     * 签名
     */
    @ApiModelProperty(value = "签名")
    @Size(max = 100, message = "签名长度不能超过{max}")
    private String signName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注长度不能超过{max}")
    private String templateDescribe;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;


}
