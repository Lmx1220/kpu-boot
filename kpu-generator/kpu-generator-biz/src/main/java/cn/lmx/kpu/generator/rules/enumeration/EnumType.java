package cn.lmx.kpu.generator.rules.enumeration;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Data
@EqualsAndHashCode(of = "enumName")
public class EnumType {
    private String enumStr;
    private String enumName;
    private String swaggerComment;
    private String enumPackage;
    private String keyValue;
    private List<EnumTypeKeyValue> kvList;


}
