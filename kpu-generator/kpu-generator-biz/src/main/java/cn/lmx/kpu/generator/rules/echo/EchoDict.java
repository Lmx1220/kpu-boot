package cn.lmx.kpu.generator.rules.echo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/13 14:27
 */
@Data
@EqualsAndHashCode(of = "key")
public class EchoDict {
    private String str;
    private String key;
    private String value;
    private List<EchoDict> itemList;

    public static EchoDict of(String key, String value) {
        EchoDict dict = new EchoDict();
        dict.setKey(key);
        dict.setValue(value);
        return dict;
    }

    public static EchoDict of(String str, String key, String value) {
        EchoDict dict = new EchoDict();
        dict.setStr(str);
        dict.setKey(key);
        dict.setValue(value);
        return dict;
    }
}
