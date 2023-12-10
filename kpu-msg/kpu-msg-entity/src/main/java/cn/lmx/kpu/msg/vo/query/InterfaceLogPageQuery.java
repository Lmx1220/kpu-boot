package cn.lmx.kpu.msg.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 表单查询条件VO
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
@EqualsAndHashCode
@Builder
@ApiModel(value = "InterfaceLogPageQuery", description = "接口执行日志")
public class InterfaceLogPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

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
