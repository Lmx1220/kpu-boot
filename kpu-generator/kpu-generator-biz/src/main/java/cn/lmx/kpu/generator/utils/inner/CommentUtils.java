package cn.lmx.kpu.generator.utils.inner;

import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.generator.utils.GenCodeConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  11:00
 */
public class CommentUtils {

    private static final Pattern ECHO_FIELD_PATTERN = Pattern.compile("(\\s*echo\\s*=\\s*\\{)(\\s*api\\s*=\\s*\"(.*?)\")?(\\s*,\\s*ref\\s*=\\s*\"(.*?)\")?(\\s*,\\s*beanClass\\s*=\\s*\"(.*?)\")?(\\s*,\\s*dictType\\s*=\\s*\"(.*?)\")?(\\s*})?");
    private static final Pattern ENUM_FIELD_PATTERN = Pattern.compile("(\\s*enum\\s*=\\s*\\{)(\\s*name\\s*=\\s*\"(.*?)\")?(\\s*,\\s*keyValue\\s*=\\s*\"(.*?)\")?(\\s*})?");
    private static final Pattern ENUM_KEY_VALUE_PATTERN = Pattern.compile("(\\s*\"(.*?)\"\\s*:\\s*\"(.*?)\")");
    ;

    public static EchoType getEchoType(String comment) {
        if (StrUtil.isEmpty(comment)) {
            return null;
        }
        Matcher matcher = ECHO_FIELD_PATTERN.matcher(comment);
        if (matcher.find()) {
            String echoStr = trim(matcher.group(1));
            String apiValue = trim(matcher.group(3));
            String refValue = trim(matcher.group(5));
            String beanClassValue = trim(matcher.group(7));
            String dictType = trim(matcher.group(9));

            EchoType et = new EchoType();
            et.setEchoStr(echoStr);
            et.setApi(apiValue);
            et.setRef(refValue);
            et.setDictType(dictType);
            et.setBeanClass(beanClassValue);
            et.valid();
            return et;
        }
        return null;
    }

    private static String trim(String group) {
        return StrUtil.isEmpty(group) ? null : group.trim();
    }

    public static EnumType getEnumStr(String entityName, String javaField, String formatEnumFileName, String swaggerComment, String comment) {
        if (StrUtil.isEmpty(comment)) {
            return null;
        }
        EnumType et = new EnumType();
        et.setSwaggerComment(swaggerComment);
        Matcher matcher = ENUM_FIELD_PATTERN.matcher(comment);

        if (matcher.find()) {
            // 枚举字符串
            String enumStr = trim(matcher.group(1));
            // 枚举名称
            String enumName = trim(matcher.group(2));
            // kv
            String keyValue = trim(matcher.group(3));
            et.setEnumStr(enumStr);
            enumName = StrUtil.isEmpty(enumName) ? getName(entityName + StrUtil.upperFirst(javaField), formatEnumFileName, GenCodeConstant.ENUM) : enumName;
            et.setEnumName(enumName);
            et.setKeyValue(keyValue);
            List<EnumTypeKeyValue> kvList = new ArrayList<>();
            if (StrUtil.isNotEmpty(keyValue)) {
                keyValue = keyValue.endsWith(StrPool.SEMICOLON) ? keyValue : keyValue + StrPool.SEMICOLON;
                Matcher kvMatcher = ENUM_KEY_VALUE_PATTERN.matcher(keyValue);
                while (kvMatcher.find()) {
                    String key = trim(kvMatcher.group(1));
                    String valueStr = trim(kvMatcher.group(2));
                    List<String> valueList = StrUtil.split(valueStr, StrPool.COMMA);
                    List<String> values = valueList.stream().filter(StrUtil::isNotEmpty).collect(Collectors.toList());
                    EnumTypeKeyValue etKv = new EnumTypeKeyValue();
                    etKv.setKey(key);
                    etKv.setValues(values);
                    kvList.add(etKv);
                }
            }

            et.setKvList(kvList);
            return et;
        }
        return null;
    }

    private static String getName(String entityName, String formatEnumFileName, String suffix) {
        String name = StrUtil.format(formatEnumFileName, entityName);
        return StrUtil.upperFirst(name) + suffix;
    }

}

