package cn.lmx.kpu.msg.entity;

import cn.lmx.basic.base.entity.Entity;
import cn.lmx.kpu.msg.enumeration.*;
import cn.lmx.kpu.msg.enumeration.MsgTemplateTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
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
@TableName("c_msg")
public class Msg extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     */
    @TableField(value = "biz_id", condition = LIKE)
    private String bizId;
    /**
     * 业务类型 
     * #MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}
     */
    @TableField(value = "biz_type", condition = EQUAL)
    private MsgBizType bizType;
    /**
     * 消息类型 
     * #MsgTemplateTypeEnum{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}
     */
    @TableField(value = "type", condition = EQUAL)
    private String type;
    /**
     * 消息模板
     */
    @TableField(value = "template_code", condition = LIKE)
    private String templateCode;
    /**
     * 标题
     */
    @TableField(value = "title", condition = LIKE)
    private String title;
    /**
     * 内容
     */
    @TableField(value = "content", condition = LIKE)
    private String content;
    /**
     * 发布人
     */
    @TableField(value = "author", condition = LIKE)
    private String author;
    /**
     * 发送渠道,可用值:APP,SERVICE
     */
    @TableField(value = "channel", condition = LIKE)
    private SourceType channel;
    /**
     * 参数
     */
    @TableField(value = "params", condition = LIKE)
    private String params;
    /**
     * 执行状态
     */
    @TableField(value = "status", condition = LIKE)
    private TaskStatus status;
    /**
     * 提醒方式
     */
    @TableField(value = "remind_model", condition = LIKE)
    private String remindModel;
    /**
     * 发送时间
     */
    @TableField(value = "send_time", condition = EQUAL)
    private LocalDateTime sendTime;

    private Long createdOrgId;

}
