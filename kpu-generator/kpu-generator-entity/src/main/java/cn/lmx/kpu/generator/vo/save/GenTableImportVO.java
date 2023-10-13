package cn.lmx.kpu.generator.vo.save;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "GenTableImportVO", description = "表导入")
public class GenTableImportVO {

    private Long dsId;
    private List<String> tableNames;
}
