package cn.lmx.kpu.msg.vo.result;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
 * 接口执行日志
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
@ApiModel(value = "InterfaceLogResultVO", description = "接口执行日志")
public class InterfaceLogResultVO extends Entity<Long> implements Serializable, EchoVO {

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
    * 接口名称
    */
    @ApiModelProperty(value = "接口名称")
    private String name;
    /**
    * 成功次数
    */
    @ApiModelProperty(value = "成功次数")
    private Integer successCount;
    /**
    * 失败次数
    */
    @ApiModelProperty(value = "失败次数")
    private Integer failCount;
    /**
    * 最后执行时间
    */
    @ApiModelProperty(value = "最后执行时间")
    private LocalDateTime lastExecTime;



}
