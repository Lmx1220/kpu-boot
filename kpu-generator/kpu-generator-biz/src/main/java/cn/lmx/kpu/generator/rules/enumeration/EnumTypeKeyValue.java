package cn.lmx.kpu.generator.rules.enumeration;

import lombok.Data;

import java.util.List;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Data
public class EnumTypeKeyValue {
    private String key;
    private List<String> values;
}
