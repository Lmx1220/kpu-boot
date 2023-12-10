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
 * 通知表
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
@TableName("c_notice")
public class Notice extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 发布人
     */
    @TableField(value = "author", condition = EQUAL)
    private Long author;
    /**
     * 自动已读
     */
    @TableField(value = "auto_read", condition = EQUAL)
    private Boolean autoRead;
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
     * 内容
     */
    @TableField(value = "content", condition = LIKE)
    private String content;
    /**
     * 处理地址
     */
    @TableField(value = "url", condition = LIKE)
    private String url;
    /**
     * 标题
     */
    @TableField(value = "title", condition = LIKE)
    private String title;
    /**
     * 打开方式
     */
    @TableField(value = "target", condition = LIKE)
    private String target;
    /**
     * 提醒方式
     */
    @TableField(value = "remind_mode", condition = LIKE)
    private String remindMode;
    /**
     * 接收人
     */
    @TableField(value = "recipient_id", condition = EQUAL)
    private Long recipientId;
    /**
     * 消息ID
     */
    @TableField(value = "msg_id", condition = EQUAL)
    private Long msgId;
    /**
     * 读取时间
     */
    @TableField(value = "read_time", condition = EQUAL)
    private LocalDateTime readTime;
    /**
     * 是否已读
     */
    @TableField(value = "is_read", condition = EQUAL)
    private Boolean isRead;
    /**
     * 是否处理
     */
    @TableField(value = "is_handle", condition = EQUAL)
    private Boolean isHandle;
    /**
     * 处理时间
     */
    @TableField(value = "handle_time", condition = EQUAL)
    private LocalDateTime handleTime;
    /**
     * 所属组织
     */
    @TableField(value = "created_org_id", condition = EQUAL)
    private Long createdOrgId;



}
