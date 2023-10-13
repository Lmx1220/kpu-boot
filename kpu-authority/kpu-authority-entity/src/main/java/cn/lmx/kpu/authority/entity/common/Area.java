package cn.lmx.kpu.authority.entity.common;

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
 * 地区表
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
@TableName("c_area")
@ApiModel(value = "Area", description = "地区表")
@AllArgsConstructor
public class Area extends TreeEntity<Area, Long> implements EchoVO {

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
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编码")
    private String code;

    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    @Size(max = 255, message = "全名长度不能超过255")
    @TableField(value = "full_name", condition = LIKE)
    @Excel(name = "全名")
    private String fullName;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Size(max = 255, message = "经度长度不能超过255")
    @TableField(value = "longitude", condition = LIKE)
    @Excel(name = "经度")
    private String longitude;

    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    @Size(max = 255, message = "维度长度不能超过255")
    @TableField(value = "latitude", condition = LIKE)
    @Excel(name = "维度")
    private String latitude;

    /**
     * 行政区级
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.AREA_LEVEL)
     */
    @ApiModelProperty(value = "行政区级")
    @Size(max = 10, message = "行政区级长度不能超过10")
    @TableField(value = "level_", condition = LIKE)
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.AREA_LEVEL)
    @Excel(name = "行政区级")
    private String level;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    @Size(max = 255, message = "数据来源长度不能超过255")
    @TableField(value = "source_", condition = LIKE)
    @Excel(name = "数据来源")
    private String source;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;


    @Builder
    public Area(Long id, String label, Integer sortValue, Long parentId, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                String code, String fullName, String longitude, String latitude, String level,
                String source, Boolean state) {
        this.id = id;
        this.label = label;
        this.sortValue = sortValue;
        this.parentId = parentId;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.code = code;
        this.fullName = fullName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.level = level;
        this.source = source;
        this.state = state;
    }

}
