package cn.lmx.kpu.authority.dto.core;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.TreeEntity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoDictItem;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

import static cn.lmx.kpu.model.constant.EchoApi.DICT_ITEM_CLASS;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/08  14:59
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
public class OrgPageResultVO extends TreeEntity<OrgPageResultVO, Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    protected String name;
    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();
    /**
     * 类型
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.ORG_TYPE)
     */
    @ApiModelProperty(value = "类型")
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.ORG_TYPE)
    private String type;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String abbreviation;

    /**
     * 树结构
     */
    @ApiModelProperty(value = "树结构")
    private String treePath;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remarks;


    @Builder
    public OrgPageResultVO(Long id, String name, Long parentId, Integer sortValue, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
                           String type, String abbreviation, String treePath, Boolean state, String remarks) {
        this.id = id;
        this.name = name;
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
