package cn.lmx.kpu.generator.vo.save;

import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  17:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "GenVO", description = "生成代码")
public class GenVO {
    private List<Long> ids;
    private TemplateEnum template;
    private Map<String, FileOverrideStrategyEnum> fileOverrideConfig;
}
