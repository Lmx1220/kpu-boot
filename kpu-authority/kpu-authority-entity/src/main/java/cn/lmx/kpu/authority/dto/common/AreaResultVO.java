package cn.lmx.kpu.authority.dto.common;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.TreeEntity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoApi;
import cn.lmx.kpu.model.constant.EchoDictType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.Map;
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
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "AreaResultVO", description = "地区表")
public class AreaResultVO extends TreeEntity<AreaResultVO, Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 编码;统计用区划代码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 城乡划分代码
     */
    @ApiModelProperty(value = "城乡划分代码")
    private String divisionCode;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    private String fullName;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String longitude;
    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    private String latitude;
    /**
     * 行政级别;[10-国家 20-省份/直辖市 30-地市 40-区县 50-乡镇]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.AREA_LEVEL)
     */
    @ApiModelProperty(value = "行政级别")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.AREA_LEVEL)
    private String level;
    /**
     * 数据来源;[10-爬取 20-新增]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Tenant.AREA_SOURCE)
     */
    @ApiModelProperty(value = "数据来源")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Tenant.AREA_SOURCE)
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
    private String treePath;

    @ApiModelProperty(value = "父节点")
    protected Long parentId;

    @ApiModelProperty(value = "排序号")
    protected Integer sortValue;


}
