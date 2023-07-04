package cn.lmx.kpu.model.vo.result;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@Builder
@ApiModel(value = "Option", description = "下拉、多选组件选项")
public class Option {
    private String label;
    private String text;
    private String value;


    public static List<Option> mapOptions(BaseEnum[] values) {
        return Arrays.stream(values).map(item -> Option.builder().label(item.getDesc())
                .text(item.getDesc()).value(item.getCode()).build()).collect(Collectors.toList());
    }
}
