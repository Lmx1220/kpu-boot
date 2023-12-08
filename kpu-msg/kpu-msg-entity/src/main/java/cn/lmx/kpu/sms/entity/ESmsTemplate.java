package cn.lmx.kpu.sms.entity;

import cn.lmx.basic.base.entity.Entity;
import cn.lmx.kpu.sms.enumeration.ProviderType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import static cn.lmx.kpu.model.constant.Condition.LIKE;
import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;


/**
 * <p>
 * 实体类
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
@TableName("e_sms_template")
public class ESmsTemplate extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 供应商类型 
     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
     */
    @TableField(value = "provider_type", condition = EQUAL)
    private ProviderType providerType;
    /**
     * 应用ID
     */
    @TableField(value = "app_id", condition = LIKE)
    private String appId;
    /**
     * 应用密码
     */
    @TableField(value = "app_secret", condition = LIKE)
    private String appSecret;
    /**
     * SMS服务域名 
     * 百度、其他厂商会用
     */
    @TableField(value = "url", condition = LIKE)
    private String url;
    /**
     * 模板名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 模板内容
     */
    @TableField(value = "content", condition = LIKE)
    private String content;
    /**
     * 模板参数
     */
    @TableField(value = "template_params", condition = LIKE)
    private String templateParams;
    /**
     * 模板编码
     */
    @TableField(value = "template_code", condition = LIKE)
    private String templateCode;
    /**
     * 签名
     */
    @TableField(value = "sign_name", condition = LIKE)
    private String signName;
    /**
     * 备注
     */
    @TableField(value = "template_describe", condition = LIKE)
    private String templateDescribe;




}
