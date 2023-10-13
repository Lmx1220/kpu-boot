package cn.lmx.kpu.generator.rules.enumeration;

import lombok.Data;

import java.util.List;

/**
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Data
public class EnumTypeKeyValue {
    private String key;
    private List<String> values;
}
