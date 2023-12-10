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
 * 接口
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
@TableName("c_interface")
public class Interface extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 接口编码
     */
    @TableField(value = "code", condition = LIKE)
    private String code;
    /**
     * 接口名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 执行方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.INTERFACE_EXEC_MODE)[01-实现类 02-脚本]
     */
    @TableField(value = "exec_mode", condition = LIKE)
    private String execMode;
    /**
     * 实现脚本
     */
    @TableField(value = "script", condition = LIKE)
    private String script;
    /**
     * 实现类
     */
    @TableField(value = "impl_class", condition = LIKE)
    private String implClass;
    /**
     * 状态
     */
    @TableField(value = "state", condition = EQUAL)
    private Boolean state;



}
