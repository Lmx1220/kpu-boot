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
     * 消息模板;
     * #extend_msg_template
     */
    @TableField(value = "template_code", condition = EQUAL)
    private String templateCode;
    /**
     * 消息类型;
     * [01-短信 02-邮件 03-站内信];
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @TableField(value = "type", condition = LIKE)
    private String type;
    /**
     * 执行状态;
     * #TaskStatus{DRAFT:草稿;WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @TableField(value = "status", condition = EQUAL)
    private TaskStatus status;
    /**
     * 发送渠道;
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @TableField(value = "channel", condition = EQUAL)
    private SourceType channel;
    /**
     * 参数;
     * <p>
     * 需要封装为[{{‘key’:'', ’value’:''}, {'key2':'', 'value2':''}]格式
     */
    @TableField(value = "param", condition = LIKE)
    private String param;
    /**
     * 标题
     */
    @TableField(value = "title", condition = LIKE)
    private String title;
    /**
     * 发送内容;
     * <p>
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    @TableField(value = "content", condition = LIKE)
    private String content;
    /**
     * 发送时间
     */
    @TableField(value = "send_time", condition = EQUAL)
    private LocalDateTime sendTime;
    /**
     * 业务ID
     */
    @TableField(value = "biz_id", condition = EQUAL)
    private Long bizId;
    /**
     * 业务类型
     */
    @TableField(value = "biz_type", condition = LIKE)
    private String bizType;
    /**
     * 发布人姓名
     */
    @TableField(value = "author", condition = LIKE)
    private String author;

    /**
     * 提醒方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
     * [01-待办 02-预警 03-提醒]
     */
    @TableField(value = "remind_mode", condition = LIKE)
    private String remindMode;
    /**
     * 所属组织
     */
    @TableField(value = "created_org_id", condition = EQUAL)
    private Long createdOrgId;
}
