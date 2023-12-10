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
 * 接口执行日志记录
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
@TableName("c_interface_logging")
public class InterfaceLogging extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 接口日志ID
     */
    @TableField(value = "log_id", condition = EQUAL)
    private Long logId;
    /**
     * 业务ID
     */
    @TableField(value = "biz_id", condition = EQUAL)
    private Long bizId;
    /**
     * 请求参数
     */
    @TableField(value = "params", condition = LIKE)
    private String params;
    /**
     * 接口返回
     */
    @TableField(value = "result", condition = LIKE)
    private String result;
    /**
     * 执行状态
     */
    @TableField(value = "status", condition = EQUAL)
    private Boolean status;
    /**
     * 异常信息
     */
    @TableField(value = "error_msg", condition = LIKE)
    private String errorMsg;
    /**
     * 执行时间
     */
    @TableField(value = "exec_time", condition = EQUAL)
    private LocalDateTime execTime;



}
