package cn.lmx.kpu.authority.entity.core;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.TreeEntity;
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
 * 组织
 * </p>
 *
 * @author lmx
 * @since 2021-04-01
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_org")
@ApiModel(value = "Org", description = "组织")
@AllArgsConstructor
public class Org extends TreeEntity<Org, Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    @TableField(value = "label")
    protected String label;


    /**
     * 类型
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.ORG_TYPE)
     */
    @ApiModelProperty(value = "类型")
    @Size(max = 2, message = "类型长度不能超过2")
    @TableField(value = "type_", condition = LIKE)
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.ORG_TYPE)
    @Excel(name = "类型")
    private String type;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    @Size(max = 255, message = "简称长度不能超过255")
    @TableField(value = "abbreviation", condition = LIKE)
    @Excel(name = "简称")
    private String abbreviation;

    /**
     * 树结构
     */
    @ApiModelProperty(value = "树结构")
    @Size(max = 255, message = "树结构长度不能超过255")
    @TableField(value = "tree_path", condition = LIKE)
    @Excel(name = "树结构")
    private String treePath;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    @TableField(value = "remarks", condition = LIKE)
    @Excel(name = "描述")
    private String remarks;


    @Builder
    public Org(Long id, String label, Long parentId, Integer sortValue, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
               String type, String abbreviation, String treePath, Boolean state, String remarks) {
        this.id = id;
        this.label = label;
        this.parentId = parentId;
        this.sortValue = sortValue;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.type = type;
        this.abbreviation = abbreviation;
        this.treePath = treePath;
        this.state = state;
        this.remarks = remarks;
    }

}
