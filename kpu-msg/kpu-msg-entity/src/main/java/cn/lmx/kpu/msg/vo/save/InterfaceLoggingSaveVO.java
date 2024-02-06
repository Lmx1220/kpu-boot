package cn.lmx.kpu.msg.vo.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 表单保存方法VO
 * 接口执行日志记录
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
@ApiModel(value = "InterfaceLoggingSaveVO", description = "接口执行日志记录")
public class InterfaceLoggingSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口日志ID
     */
    @ApiModelProperty(value = "接口日志ID")
    @NotNull(message = "请填写接口日志ID")
    private Long logId;
    /**
     * 业务ID
     */
    @ApiModelProperty(value = "业务ID")
    private Long bizId;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    @Size(max = 255, message = "请求参数长度不能超过{max}")
    private String params;
    /**
     * 接口返回
     */
    @ApiModelProperty(value = "接口返回")
    @Size(max = 65535, message = "接口返回长度不能超过{max}")
    private String result;
    /**
     * 执行状态
     */
    @ApiModelProperty(value = "执行状态")
    private String status;
    /**
     * 异常信息
     */
    @ApiModelProperty(value = "异常信息")
    @Size(max = 255, message = "异常信息长度不能超过{max}")
    private String errorMsg;
    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间")
    private LocalDateTime execTime;



}
