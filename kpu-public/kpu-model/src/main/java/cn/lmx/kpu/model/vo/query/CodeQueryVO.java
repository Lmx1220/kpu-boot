package cn.lmx.kpu.model.vo.query;

import cn.lmx.kpu.model.vo.result.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/13  15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "CodeQueryVO", description = "字典、枚举查询参数")
public class CodeQueryVO {
    @ApiModelProperty(value = "需要排除的字典条目或枚举条目")
    private String[] excludes;
    @ApiModelProperty(value = "扩展条目")
    private Option extend;
    @ApiModelProperty(value = "扩展条目放在第一位还是最后一位")
    private Boolean extendFirst;
    @ApiModelProperty(value = "字典类型或枚举类型")
    private String type;
}
