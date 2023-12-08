package cn.lmx.kpu.sms.entity;

import cn.lmx.basic.base.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/11/19  18:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("def_msg")
public class ExtendMsg extends Entity<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 发布人姓名
     */
    private String author;
    /**
     * 业务ID
     */
    private Long bizId;
    /**
     * 业务类型
     */
    private String bizType;
    /**
     * 发送渠道,可用值:APP,SERVICE
     */
    private String channel;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 参数
     */
    private String param;
    /**
     * 接收人
     */
    private List<String> recipientList;
    /**
     * 提醒方式
     */
    private String remindMode;
    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 执行状态,可用值:DRAFT,WAITING,SUCCESS,FAIL
     */
    private String status;
    /**
     * 消息模板
     */
    private String templateCode;
    /**
     * 标题
     */
    private String title;
    /**
     * 消息类型
     */
    private String type;

}
