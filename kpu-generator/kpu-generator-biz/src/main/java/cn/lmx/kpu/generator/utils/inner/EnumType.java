package cn.lmx.kpu.generator.utils.inner;

import lombok.Data;

import java.util.List;

@Data
public class EnumType {
    private String swaggerComment;
    private String enumStr;
    private String enumName;
    private String keyValue;
    private List<EnumTypeKeyValue> kvList;
}
