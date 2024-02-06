package cn.lmx.kpu.generator.converts;

import cn.lmx.kpu.generator.config.DateType;
import cn.lmx.kpu.generator.rules.ColumnType;
import cn.lmx.kpu.generator.rules.DbColumnType;

import static cn.lmx.kpu.generator.converts.TypeConverts.contains;
import static cn.lmx.kpu.generator.converts.TypeConverts.containsAny;
import static cn.lmx.kpu.generator.rules.DbColumnType.BIG_DECIMAL;
import static cn.lmx.kpu.generator.rules.DbColumnType.BOOLEAN;
import static cn.lmx.kpu.generator.rules.DbColumnType.BYTE_ARRAY;
import static cn.lmx.kpu.generator.rules.DbColumnType.DOUBLE;
import static cn.lmx.kpu.generator.rules.DbColumnType.FLOAT;
import static cn.lmx.kpu.generator.rules.DbColumnType.INTEGER;
import static cn.lmx.kpu.generator.rules.DbColumnType.LONG;
import static cn.lmx.kpu.generator.rules.DbColumnType.STRING;


/**
 * SQLServer 字段类型转换
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 AM
 * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
 */
public class SqlServerTypeConvert implements ITypeConvert {

    public static final SqlServerTypeConvert INSTANCE = new SqlServerTypeConvert();

    /**
     * 转换为日期类型
     *
     * @param dt   日期类型
     * @param type 类型
     * @return 返回对应的列类型
     */
    public static ColumnType toDateType(DateType dt, String type) {
        return switch (dt) {
            case SQL_PACK -> switch (type) {
                case "date" -> DbColumnType.DATE_SQL;
                case "time" -> DbColumnType.TIME;
                default -> DbColumnType.TIMESTAMP;
            };
            case TIME_PACK -> switch (type) {
                case "date" -> DbColumnType.LOCAL_DATE;
                case "time" -> DbColumnType.LOCAL_TIME;
                default -> DbColumnType.LOCAL_DATE_TIME;
            };
            default -> DbColumnType.DATE;
        };
    }

    @Override
    public ColumnType processTypeConvert(DateType datetype, String fieldType, Long size, Integer digit) {
        return TypeConverts.use(fieldType)
                .test(containsAny("char", "xml", "text").then(STRING))
                .test(contains("bigint").then(LONG))
                .test(contains("int").then(INTEGER))
                .test(containsAny("date", "time").then(t -> toDateType(datetype, t)))
                .test(contains("bit").then(BOOLEAN))
                .test(containsAny("decimal", "numeric").then(DOUBLE))
                .test(contains("money").then(BIG_DECIMAL))
                .test(containsAny("binary", "image").then(BYTE_ARRAY))
                .test(containsAny("float", "real").then(FLOAT))
                .or(STRING);
    }
}
