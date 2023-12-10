package cn.lmx.kpu.msg.vo.result;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
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
@ApiModel(value = "InterfacePropertyResultVO", description = "接口属性")
public class InterfacePropertyResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 接口ID
    */
    @ApiModelProperty(value = "接口ID")
    private Long interfaceId;
    /**
    * 参数名称
    */
    @ApiModelProperty(value = "参数名称")
    private String name;
    /**
    * 参数键
    */
    @ApiModelProperty(value = "参数键")
    private String key;
    /**
    * 参数值
    */
    @ApiModelProperty(value = "参数值")
    private String value;
    /**
    * 排序号
    */
    @ApiModelProperty(value = "排序号")
    private Integer sortValue;
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remarks;



}
