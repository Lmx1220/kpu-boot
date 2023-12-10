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
 * 消息模板
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
@TableName("c_msg_template")
public class MsgTemplate extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 接口ID
     */
    @TableField(value = "interface_id", condition = EQUAL)
    private Long interfaceId;
    /**
     * 消息类型;[01-短信 02-邮件 03-站内信];@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @TableField(value = "type", condition = LIKE)
    private String type;
    /**
     * 模板标识
     */
    @TableField(value = "code", condition = LIKE)
    private String code;
    /**
     * 模板名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 状态
     */
    @TableField(value = "state", condition = EQUAL)
    private Boolean state;
    /**
     * 模板编码
     */
    @TableField(value = "template_code", condition = LIKE)
    private String templateCode;
    /**
     * 签名
     */
    @TableField(value = "sign", condition = LIKE)
    private String sign;
    /**
     * 标题
     */
    @TableField(value = "title", condition = LIKE)
    private String title;
    /**
     * 模板内容
     */
    @TableField(value = "content", condition = LIKE)
    private String content;
    /**
     * 脚本
     */
    @TableField(value = "script", condition = LIKE)
    private String script;
    /**
     * 模板参数
     */
    @TableField(value = "param", condition = LIKE)
    private String param;
    /**
     * 备注
     */
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;
    /**
     * 打开方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)[01-页面 02-弹窗 03-新开窗口]
     */
    @TableField(value = "target_", condition = LIKE)
    private String target;
    /**
     * 自动已读
     */
    @TableField(value = "auto_read", condition = EQUAL)
    private Boolean autoRead;
    /**
     * 提醒方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)[01-待办 02-预警 03-提醒]
     */
    @TableField(value = "remind_mode", condition = LIKE)
    private String remindMode;
    /**
     * 跳转地址
     */
    @TableField(value = "url", condition = LIKE)
    private String url;



}
