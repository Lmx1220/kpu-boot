package cn.lmx.kpu.msg.vo.result;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.kpu.msg.enumeration.MsgBizType;
import cn.lmx.kpu.msg.enumeration.MsgTemplateTypeEnum;
import cn.lmx.kpu.msg.enumeration.SourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
 * 消息表
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
@ApiModel(value = "MsgResultVO", description = "消息表")
public class MsgResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 业务ID
    */
    @ApiModelProperty(value = "业务ID")
    private Long bizId;
    /**
    * 业务类型 
     * #MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}
    */
    @ApiModelProperty(value = "业务类型  #MsgBizType{USER_LOCK:账号锁定")
    @Echo(api = Echo.ENUM_API)
    private MsgBizType bizType;
    /**
    * 消息类型 
     * #MsgTemplateTypeEnum{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}
    */
    @ApiModelProperty(value = "消息类型  #MsgTemplateTypeEnum{WAIT:待办")
    @Echo(api = Echo.ENUM_API)
    private String type;
    /**
    * 消息模板
    */
    @ApiModelProperty(value = "消息模板")
    private String templateCode;
    /**
    * 标题
    */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
    * 内容
    */
    @ApiModelProperty(value = "内容")
    private String content;
    /**
    * 发布人
    */
    @ApiModelProperty(value = "发布人")
    private String author;
    /**
    * 发送渠道,可用值:APP,SERVICE
    */
    @ApiModelProperty(value = "发送渠道,可用值:APP,SERVICE")
    private SourceType channel;
    /**
    * 参数
    */
    @ApiModelProperty(value = "参数")
    private String param;
    /**
    * 执行状态
    */
    @ApiModelProperty(value = "执行状态")
    private String status;
    /**
    * 提醒方式
    */
    @ApiModelProperty(value = "提醒方式")
    private String remindMode;
    /**
    * 发送时间
    */
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;

    private List<String> recipientList;

}
