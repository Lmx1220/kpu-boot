package cn.lmx.kpu.msg.vo.query;

import cn.lmx.kpu.msg.enumeration.MsgBizType;
import cn.lmx.kpu.msg.enumeration.MsgTemplateTypeEnum;
import cn.lmx.kpu.msg.enumeration.MsgTemplateTypeEnum;
import cn.lmx.kpu.msg.enumeration.SourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 表单查询条件VO
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
@EqualsAndHashCode
@Builder
@ApiModel(value = "MsgPageQuery", description = "消息表")
public class MsgPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 业务ID
    */
    @ApiModelProperty(value = "业务ID")
    private String bizId;
    /**
    * 业务类型 
     * #MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}
    */
    @ApiModelProperty(value = "业务类型  #MsgBizType{USER_LOCK:账号锁定")
    private MsgBizType bizType;
    /**
    * 消息类型 
     * #MsgTemplateTypeEnum{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}
    */
    @ApiModelProperty(value = "消息类型  #MsgTemplateTypeEnum{WAIT:待办")
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
    private String params;
    /**
    * 执行状态
    */
    @ApiModelProperty(value = "执行状态")
    private String status;
    /**
    * 提醒方式
    */
    @ApiModelProperty(value = "提醒方式")
    private String remindModel;
    /**
    * 发送时间
    */
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;



}
