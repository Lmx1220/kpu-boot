package cn.lmx.kpu.model.entity.base;

import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.TreeEntity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoDictType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

import static cn.lmx.kpu.model.constant.Condition.LIKE;
import static cn.lmx.kpu.model.constant.EchoApi.DICTIONARY_ITEM_CLASS;

/**
 * <p>
 * 实体类
 *
 * </p>
 *
 * @author lmx
 * @since 2019-07-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@TableName("c_org")
public class SysOrg extends TreeEntity<SysOrg, Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = new HashMap<>();
    /**
     * 类型
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.ORG_TYPE)
     */
    @ApiModelProperty(value = "类型")
    @Size(max = 2, message = "类型长度不能超过2")
    @TableField(value = "type_", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, dictType = EchoDictType.ORG_TYPE)
    private String type;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    @Size(max = 255, message = "简称长度不能超过255")
    @TableField(value = "abbreviation", condition = LIKE)
    private String abbreviation;

    /**
     * 树结构
     */
    @ApiModelProperty(value = "树结构")
    @Size(max = 255, message = "树结构长度不能超过255")
    @TableField(value = "tree_path", condition = LIKE)
    private String treePath;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    private Boolean state;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    @TableField(value = "describe_", condition = LIKE)
    private String describe;


}
