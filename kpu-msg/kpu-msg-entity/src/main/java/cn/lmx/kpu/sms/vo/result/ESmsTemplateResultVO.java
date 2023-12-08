package cn.lmx.kpu.sms.vo.result;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.sms.enumeration.ProviderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.Map;
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
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "ESmsTemplateResultVO", description = "短信模板")
public class ESmsTemplateResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "模板ID")
    private Long id;

    /**
    * 供应商类型 
     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
    */
    @ApiModelProperty(value = "供应商类型  #ProviderType{ALI:OK,阿里云短信")
    @Echo(api = Echo.ENUM_API)
    private ProviderType providerType;
    /**
    * 应用ID
    */
    @ApiModelProperty(value = "应用ID")
    private String appId;
    /**
    * 应用密码
    */
    @ApiModelProperty(value = "应用密码")
    private String appSecret;
    /**
    * SMS服务域名 
     * 百度、其他厂商会用
    */
    @ApiModelProperty(value = "SMS服务域名  百度、其他厂商会用")
    private String url;
    /**
    * 模板名称
    */
    @ApiModelProperty(value = "模板名称")
    private String name;
    /**
    * 模板内容
    */
    @ApiModelProperty(value = "模板内容")
    private String content;
    /**
    * 模板参数
    */
    @ApiModelProperty(value = "模板参数")
    private String templateParams;
    /**
    * 模板编码
    */
    @ApiModelProperty(value = "模板编码")
    private String templateCode;
    /**
    * 签名
    */
    @ApiModelProperty(value = "签名")
    private String signName;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
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
