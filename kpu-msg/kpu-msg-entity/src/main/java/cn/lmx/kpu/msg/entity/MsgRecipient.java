package cn.lmx.kpu.msg.entity;

import cn.lmx.basic.base.entity.Entity;
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
 * 消息接收人
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 21:43:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("c_msg_recipient")
public class MsgRecipient extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableField(value = "msg_id", condition = EQUAL)
    private Long msgId;
    /**
     * 扩展信息
     */
    @TableField(value = "ext", condition = LIKE)
    private String ext;
    /**
     * 接收人
     */
    @TableField(value = "recipient", condition = LIKE)
    private String recipient;



}
