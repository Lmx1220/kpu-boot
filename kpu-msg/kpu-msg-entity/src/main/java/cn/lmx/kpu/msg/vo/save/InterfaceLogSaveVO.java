package cn.lmx.kpu.msg.vo.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 表单保存方法VO
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
@ApiModel(value = "InterfaceLogSaveVO", description = "接口执行日志")
public class InterfaceLogSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口ID
     */
    @ApiModelProperty(value = "接口ID")
    @NotNull(message = "请填写接口ID")
    private Long interfaceId;
    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @NotEmpty(message = "请填写接口名称")
    @Size(max = 255, message = "接口名称长度不能超过{max}")
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
