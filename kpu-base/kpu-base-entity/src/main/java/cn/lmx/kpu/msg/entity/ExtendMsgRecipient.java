package cn.lmx.kpu.msg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import cn.lmx.basic.base.entity.Entity;

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static cn.lmx.kpu.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 消息接收人
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("extend_msg_recipient")
public class ExtendMsgRecipient extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID;
     * <p>
     * #extend_msg
     */
    @TableField(value = "msg_id", condition = EQUAL)
    private Long msgId;
    /**
     * 接收人;
     * 可能是手机号、邮箱、用户ID等
     */
    @TableField(value = "recipient", condition = LIKE)
    private String recipient;

    /**
     * 扩展信息
     */
    @TableField(value = "ext", condition = LIKE)
    private String ext;


}
