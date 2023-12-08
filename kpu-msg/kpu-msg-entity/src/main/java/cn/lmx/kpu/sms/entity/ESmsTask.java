package cn.lmx.kpu.sms.entity;

import cn.lmx.basic.base.entity.Entity;
import cn.lmx.kpu.sms.enumeration.SourceType;
import cn.lmx.kpu.sms.enumeration.TaskStatus;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 发送任务
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("e_sms_task")
public class ESmsTask extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 短信模板 @Echo(api = EchoApi.SMS_TEMPLATE_ID_CLASS) #e_sms_template
     */
    @TableField(value = "template_id", condition = EQUAL)
    private Long templateId;
    /**
     * 执行状态 
     * (手机号具体发送状态看sms_send_status表) 
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @TableField(value = "status", condition = EQUAL)
    private TaskStatus status;
    /**
     * 发送渠道
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @TableField(value = "source_type", condition = EQUAL)
    private SourceType sourceType;
    /**
     * 主题
     */
    @TableField(value = "topic", condition = LIKE)
    private String topic;
    /**
     * 参数 
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
     */
    @TableField(value = "template_params", condition = LIKE)
    private String templateParams;
    /**
     * 发送时间
     */
    @TableField(value = "send_time", condition = EQUAL)
    private LocalDateTime sendTime;
    /**
     * 发送内容 
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    @TableField(value = "content", condition = LIKE)
    private String content;
    /**
     * 是否草稿
     */
    @TableField(value = "draft", condition = EQUAL)
    private Boolean draft;



}
