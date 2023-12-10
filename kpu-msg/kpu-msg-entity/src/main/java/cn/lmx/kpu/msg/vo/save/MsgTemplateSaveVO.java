package cn.lmx.kpu.msg.vo.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 表单保存方法VO
 * 消息模板
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "MsgTemplateSaveVO", description = "消息模板")
public class MsgTemplateSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口ID
     */
    @ApiModelProperty(value = "接口ID")
    @NotNull(message = "请填写接口ID")
    private Long interfaceId;
    /**
     * 消息类型;[01-短信 02-邮件 03-站内信];@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @ApiModelProperty(value = "消息类型")
    @NotEmpty(message = "请填写消息类型")
    @Size(max = 2, message = "消息类型长度不能超过{max}")
    private String type;
    /**
     * 模板标识
     */
    @ApiModelProperty(value = "模板标识")
    @NotEmpty(message = "请填写模板标识")
    @Size(max = 255, message = "模板标识长度不能超过{max}")
    private String code;
    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    @Size(max = 255, message = "模板名称长度不能超过{max}")
    private String name;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 模板编码
     */
    @ApiModelProperty(value = "模板编码")
    @Size(max = 255, message = "模板编码长度不能超过{max}")
    private String templateCode;
    /**
     * 签名
     */
    @ApiModelProperty(value = "签名")
    @Size(max = 255, message = "签名长度不能超过{max}")
    private String sign;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @Size(max = 255, message = "标题长度不能超过{max}")
    private String title;
    /**
     * 模板内容
     */
    @ApiModelProperty(value = "模板内容")
    @NotEmpty(message = "请填写模板内容")
    @Size(max = 2147483647, message = "模板内容长度不能超过{max}")
    private String content;
    /**
     * 脚本
     */
    @ApiModelProperty(value = "脚本")
    @Size(max = 255, message = "脚本长度不能超过{max}")
    private String script;
    /**
     * 模板参数
     */
    @ApiModelProperty(value = "模板参数")
    @Size(max = 255, message = "模板参数长度不能超过{max}")
    private String param;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注长度不能超过{max}")
    private String remarks;
    /**
     * 打开方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)[01-页面 02-弹窗 03-新开窗口]
     */
    @ApiModelProperty(value = "打开方式")
    @Size(max = 2, message = "打开方式长度不能超过{max}")
    private String target;
    /**
     * 自动已读
     */
    @ApiModelProperty(value = "自动已读")
    private Boolean autoRead;
    /**
     * 提醒方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)[01-待办 02-预警 03-提醒]
     */
    @ApiModelProperty(value = "提醒方式")
    @Size(max = 2, message = "提醒方式长度不能超过{max}")
    private String remindMode;
    /**
     * 跳转地址
     */
    @ApiModelProperty(value = "跳转地址")
    @Size(max = 255, message = "跳转地址长度不能超过{max}")
    private String url;



}
