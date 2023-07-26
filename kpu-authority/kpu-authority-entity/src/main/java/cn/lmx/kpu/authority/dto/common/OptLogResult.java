package cn.lmx.kpu.authority.dto.common;

import cn.lmx.kpu.authority.entity.common.OptLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 系统日志扩展
 * </p>
 *
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OptLogResult", description = "系统日志扩展")
public class OptLogResult extends OptLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String params;
    /**
     * 返回值
     */
    @ApiModelProperty(value = "返回值")
    private String result;
    /**
     * 异常描述
     */
    @ApiModelProperty(value = "异常描述")
    private String exDetail;

}
