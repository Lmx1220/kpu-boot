package cn.lmx.kpu.generator.converts;

import cn.lmx.kpu.generator.rules.ColumnType;
import cn.lmx.kpu.generator.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import static cn.lmx.kpu.generator.converts.TypeConverts.containsAny;
import static cn.lmx.kpu.generator.rules.DbColumnType.*;


/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/26  18:42
 */
public class MySqlTypeConvert implements ITypeConvert {
    public static final MySqlTypeConvert INSTANCE = new MySqlTypeConvert();

    /**
     * 转换为日期类型
     *
     * @param dt   日期类型
     * @param type 类型
     * @return 返回对应的列类型
     * @author lmx
     * @date 2023/8/26 18:52
     */
    public static ColumnType toDateType(DateType dt, String type) {
        String dateType = type.replaceAll("\\(\\d+\\)", "");
        switch (dt) {
            case ONLY_DATE:
                return DbColumnType.DATE;
            case SQL_PACK:
                switch (dateType) {
                    case "date":
                    case "year":
                        return DbColumnType.DATE;
                    case "time":
                        return DbColumnType.TIME;
                    default:
                        return DbColumnType.TIMESTAMP;
                }
            case TIME_PACK:
                switch (dateType) {
                    case "date":
                        return DbColumnType.LOCAL_DATE;
                    case "time":
                        return DbColumnType.LOCAL_TIME;
                    case "year":
                        return DbColumnType.YEAR;
                    default:
                        return DbColumnType.LOCAL_DATE_TIME;
                }
            default:
                return STRING;
        }
    }

    @Override
    public ColumnType processTypeConvert(DateType dateType, String fieldType, Long size, Integer digit) {
        return TypeConverts.use(fieldType)
                .test(containsAny("longtext", "char", "text", "json", "enum").then(STRING))
                .test(containsAny("bigint").then(LONG))
                .test(containsAny("tinyint(1)", "bit(1)").then(BOOLEAN))
                .test(containsAny("bit").then(BOOLEAN))
                .test(containsAny("int").then(INTEGER))
                .test(containsAny("decimal").then(BIG_DECIMAL))
                .test(containsAny("clob").then(CLOB))
                .test(containsAny("blob").then(BLOB))
                .test(containsAny("binary").then(BYTE_ARRAY))
                .test(containsAny("float").then(FLOAT))
                .test(containsAny("double").then(DOUBLE))
                .test(containsAny("date", "time", "year").then(t -> toDateType(dateType, t)))
                .or(STRING);

    }
}
