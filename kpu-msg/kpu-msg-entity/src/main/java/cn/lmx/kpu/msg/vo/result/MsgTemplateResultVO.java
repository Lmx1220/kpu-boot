package cn.lmx.kpu.msg.vo.result;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoApi;
import cn.lmx.kpu.model.constant.EchoDictType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "MsgTemplateResultVO", description = "消息模板")
public class MsgTemplateResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 接口ID
    */
    @ApiModelProperty(value = "接口ID")
    private Long interfaceId;
    /**
    * 消息类型;[01-短信 02-邮件 03-站内信];@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
    */
    @ApiModelProperty(value = "消息类型")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
    private String type;
    /**
    * 模板标识
    */
    @ApiModelProperty(value = "模板标识")
    private String code;
    /**
    * 模板名称
    */
    @ApiModelProperty(value = "模板名称")
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
    private String templateCode;
    /**
    * 签名
    */
    @ApiModelProperty(value = "签名")
    private String sign;
    /**
    * 标题
    */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
    * 模板内容
    */
    @ApiModelProperty(value = "模板内容")
    private String content;
    /**
    * 脚本
    */
    @ApiModelProperty(value = "脚本")
    private String script;
    /**
    * 模板参数
    */
    @ApiModelProperty(value = "模板参数")
    private String param;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
    * 打开方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)[01-页面 02-弹窗 03-新开窗口]
    */
    @ApiModelProperty(value = "打开方式")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)
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
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
    private String remindMode;
    /**
    * 跳转地址
    */
    @ApiModelProperty(value = "跳转地址")
    private String url;



}
