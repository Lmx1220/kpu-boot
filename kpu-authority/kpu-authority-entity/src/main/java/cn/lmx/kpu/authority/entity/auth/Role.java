package cn.lmx.kpu.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoDictItem;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

import static cn.lmx.kpu.model.constant.Condition.LIKE;
import static cn.lmx.kpu.model.constant.EchoApi.DICT_ITEM_CLASS;

/**
 * <p>
 * 实体类
 * 角色
 * </p>
 *
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_role")
@ApiModel(value = "Role", description = "角色")
@AllArgsConstructor
public class Role extends Entity<Long> implements EchoVO {

    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();
    private static final long serialVersionUID = 1L;
    /**
     * 角色类别;[10-功能角色 20-桌面角色 30-数据角色]
     */
    @ApiModelProperty(value = "角色类别")
    @TableField(value = "category", condition = LIKE)
    @Size(max = 2, message = "角色类别长度不能超过{max}")
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.ROLE_CATEGORY)
    private String category;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 30, message = "名称长度不能超过30")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称")
    private String name;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @Size(max = 20, message = "编码长度不能超过20")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编码")
    private String code;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 100, message = "描述长度不能超过100")
    @TableField(value = "remarks", condition = LIKE)
    @Excel(name = "描述")
    private String remarks;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;

    /**
     * 内置角色
     */
    @ApiModelProperty(value = "内置角色")
    @TableField("readonly_")
    @Excel(name = "内置角色", replace = {"是_true", "否_false", "_null"})
    private Boolean readonly;

    @Builder
    public Role(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                String name, String code, String remarks, Boolean state, Boolean readonly, String category) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.name = name;
        this.code = code;
        this.remarks = remarks;
        this.state = state;
        this.readonly = readonly;
        this.category = category;
    }

}
