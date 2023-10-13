package cn.lmx.kpu.generator.converts;


import cn.lmx.kpu.generator.config.DateType;
import cn.lmx.kpu.generator.rules.ColumnType;
import cn.lmx.kpu.generator.rules.DbColumnType;

import static cn.lmx.kpu.generator.converts.TypeConverts.contains;
import static cn.lmx.kpu.generator.converts.TypeConverts.containsAny;
import static cn.lmx.kpu.generator.rules.DbColumnType.*;

/**
 * Oracle 数据库生成对应实体类时字段类型转换，跟据 Oracle 中的数据类型，返回对应的 Java 类型
 *
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/13 14:27
 */
public class OracleTypeConvert implements ITypeConvert {
    public static final OracleTypeConvert INSTANCE = new OracleTypeConvert();

    /**
     * 将对应的类型名称转换为对应的 java 类类型
     * <p>
     * String.valueOf(Integer.MAX_VALUE).length() == 10
     * Integer 不一定能装下 10 位的数字
     * <p>
     * String.valueOf(Long.MAX_VALUE).length() == 19
     * Long 不一定能装下 19 位的数字
     *
     * @param typeName 类型名称
     * @return 返回列类型
     */
    private static ColumnType toNumberType(String typeName, Long size, Integer digit) {
        if (size == null) {
            return DbColumnType.LONG;
        }
        if (digit != null && digit > 0) {
            return DbColumnType.BIG_DECIMAL;
        }
        if (Long.valueOf(1).equals(size)) {
            return DbColumnType.BOOLEAN;
        } else if (Long.valueOf(0).equals(size) || (size >= 2 && size <= 10)) {
            return DbColumnType.INTEGER;
        } else if (size > 10 && size <= 19) {
            return DbColumnType.LONG;
        }
        return DbColumnType.BIG_DECIMAL;
    }

    /**
     * 当前时间为字段类型，根据全局配置返回对应的时间类型
     *
     * @param dt   日期类型
     * @param type 类型
     * @return 返回对应的列类型
     */
    protected static ColumnType toDateType(DateType dt, String type) {
        switch (dt) {
            case ONLY_DATE:
                return DbColumnType.DATE;
            case SQL_PACK:
                return DbColumnType.TIMESTAMP;
            case TIME_PACK:
                return DbColumnType.LOCAL_DATE_TIME;
            default:
                return STRING;
        }
    }

    @Override
    public ColumnType processTypeConvert(DateType datetype, String fieldType, Long size, Integer digit) {
        return TypeConverts.use(fieldType)
                .test(containsAny("char", "clob").then(STRING))
                .test(containsAny("date", "timestamp").then(p -> toDateType(datetype, p)))
                .test(contains("number").then(p -> toNumberType(p, size, digit)))
                .test(contains("float").then(FLOAT))
                .test(contains("blob").then(BLOB))
                .test(containsAny("binary", "raw").then(BYTE_ARRAY))
                .or(STRING);
    }

}
