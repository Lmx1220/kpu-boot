package cn.lmx.kpu.sms.entity;

import cn.lmx.basic.base.entity.Entity;
import cn.lmx.kpu.sms.enumeration.SendStatus;
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
 * 短信发送状态
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
@TableName("e_sms_send_status")
public class ESmsSendStatus extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID 
     * #e_sms_task
     */
    @TableField(value = "task_id", condition = EQUAL)
    private Long taskId;
    /**
     * 发送状态 
     * #SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}
     */
    @TableField(value = "send_status", condition = EQUAL)
    private SendStatus sendStatus;
    /**
     * 接收者手机
     * 单个手机号 
     * 阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID
     */
    @TableField(value = "tel_num", condition = LIKE)
    private String telNum;
    /**
     * 发送回执ID
     */
    @TableField(value = "biz_id", condition = LIKE)
    private String bizId;
    /**
     * 发送返回 
     * 阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无
     */
    @TableField(value = "ext", condition = LIKE)
    private String ext;
    /**
     * 状态码 
     * 阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功
     */
    @TableField(value = "code", condition = LIKE)
    private String code;
    /**
     * 状态码的描述
     */
    @TableField(value = "message", condition = LIKE)
    private String message;
    /**
     * 短信计费的条数
     * 腾讯专用
     */
    @TableField(value = "fee", condition = EQUAL)
    private Integer fee;



}
