package cn.lmx.kpu.generator.utils.inner;

import lombok.Data;

import java.util.List;

@Data
public class EnumTypeKeyValue {
    private String key;
    private List<String> values;
}
