package cn.lmx.kpu.msg.vo.save;

import cn.lmx.kpu.msg.enumeration.MsgBizType;
import cn.lmx.kpu.msg.enumeration.MsgTemplateTypeEnum;
import cn.lmx.kpu.msg.enumeration.SourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 表单保存方法VO
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
@ApiModel(value = "MsgSaveVO", description = "消息表")
public class MsgSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     */
    @ApiModelProperty(value = "业务ID")
    @Size(max = 64, message = "业务ID长度不能超过{max}")
    private Long bizId;
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
    @NotNull(message = "请填写消息类型  #MsgTemplateTypeEnum{WAIT:待办")
    private String type;
    /**
     * 消息模板
     */
    @ApiModelProperty(value = "消息模板")
    @Size(max = 255, message = "消息模板长度不能超过{max}")
    private String templateCode;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @Size(max = 255, message = "标题长度不能超过{max}")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @Size(max = 65535, message = "内容长度不能超过{max}")
    private String content;
    /**
     * 发布人
     */
    @ApiModelProperty(value = "发布人")
    @Size(max = 50, message = "发布人长度不能超过{max}")
    private String author;
    /**
     * 发送渠道,可用值:APP,SERVICE
     */
    @ApiModelProperty(value = "发送渠道,可用值:APP,SERVICE")
    @Size(max = 255, message = "发送渠道,可用值:APP,SERVICE长度不能超过{max}")
    private SourceType channel;
    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    @Size(max = 500, message = "参数长度不能超过{max}")
    private String param;
    /**
     * 执行状态
     */
    @ApiModelProperty(value = "执行状态")
    @Size(max = 2, message = "执行状态长度不能超过{max}")
    private String status;
    /**
     * 提醒方式
     */
    @ApiModelProperty(value = "提醒方式")
    @Size(max = 2, message = "提醒方式长度不能超过{max}")
    private String remindMode;
    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;



}
