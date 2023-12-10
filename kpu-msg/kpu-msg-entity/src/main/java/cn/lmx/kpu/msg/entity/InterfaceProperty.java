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
 * 接口属性
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
@TableName("c_interface_property")
public class InterfaceProperty extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 接口ID
     */
    @TableField(value = "interface_id", condition = EQUAL)
    private Long interfaceId;
    /**
     * 参数名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 参数键
     */
    @TableField(value = "key_", condition = LIKE)
    private String key;
    /**
     * 参数值
     */
    @TableField(value = "value", condition = LIKE)
    private String value;
    /**
     * 排序号
     */
    @TableField(value = "sort_value", condition = EQUAL)
    private Integer sortValue;
    /**
     * 备注
     */
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;



}
