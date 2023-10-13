package cn.lmx.kpu.authority.dto.common;

import cn.lmx.basic.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/08  15:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "ParameterResultVO", description = "参数配置")
public class ParameterResultVO extends Entity<Long> {

    private static final long serialVersionUID = 1L;

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
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remarks;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    private Boolean readonly;


    @Builder
    public ParameterResultVO(Long id, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                             String key, String value, String name, String remarks, Boolean state, Boolean readonly) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.key = key;
        this.value = value;
        this.name = name;
        this.remarks = remarks;
        this.state = state;
        this.readonly = readonly;
    }
}
