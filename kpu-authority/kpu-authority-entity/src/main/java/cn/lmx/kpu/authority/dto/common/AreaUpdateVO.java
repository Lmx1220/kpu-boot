package cn.lmx.kpu.authority.dto.common;

import cn.lmx.basic.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 地区表
 * </p>
 *
 * @author lmx
 * @date 2023-10-25 19:28:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "AreaUpdateVO", description = "地区表")
public class AreaUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "请填写id", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 编码;统计用区划代码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "请填写编码")
    @Size(max = 64, message = "编码长度不能超过{max}")
    private String code;
    /**
     * 城乡划分代码
     */
    @ApiModelProperty(value = "城乡划分代码")
    @Size(max = 255, message = "城乡划分代码长度不能超过{max}")
    private String divisionCode;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "请填写名称")
    @Size(max = 255, message = "名称长度不能超过{max}")
    private String name;
    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    @Size(max = 255, message = "全名长度不能超过{max}")
    private String fullName;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Size(max = 255, message = "经度长度不能超过{max}")
    private String longitude;
    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    @Size(max = 255, message = "维度长度不能超过{max}")
    private String latitude;
    /**
     * 行政级别;[10-国家 20-省份/直辖市 30-地市 40-区县 50-乡镇]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.AREA_LEVEL)
     */
    @ApiModelProperty(value = "行政级别")
    @Size(max = 2, message = "行政级别长度不能超过{max}")
    private String level;
    /**
     * 数据来源;[10-爬取 20-新增]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Tenant.AREA_SOURCE)
     */
    @ApiModelProperty(value = "数据来源")
    @Size(max = 2, message = "数据来源长度不能超过{max}")
    private String source;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer treeGrade;
    /**
     *
     */
    @ApiModelProperty(value = "")
    @Size(max = 512, message = "长度不能超过{max}")
    private String treePath;

    @ApiModelProperty(value = "父节点")
    protected Long parentId;

    @ApiModelProperty(value = "排序号")
    protected Integer sortValue;

}
