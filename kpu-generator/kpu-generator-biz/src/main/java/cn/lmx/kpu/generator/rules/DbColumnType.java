package cn.lmx.kpu.generator.rules;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/26  19:12
 */
public enum DbColumnType implements ColumnType {
    // 基本类型
    BASE_BYTE("byte", null, "string"),
    BASE_SHORT("short", null, "number"),
    BASE_CHAR("char", null, "string"),
    BASE_INT("int", null, "number"),
    BASE_LONG("long", null, "string"),
    BASE_FLOAT("float", null, "number"),
    BASE_DOUBLE("double", null, "number"),
    BASE_BOOLEAN("boolean", null, "boolean"),
    // 包装类性
    BYTE("Byte", null, "string"),
    SHORT("Short", null, "number"),
    CHARACTER("Character", null, "string"),
    INTEGER("Integer", null, "number"),
    LONG("Long", null, "string"),
    FLOAT("Float", null, "number"),
    DOUBLE("Double", null, "number"),
    BOOLEAN("Boolean", null, "boolean"),
    STRING("String", null, "string"),

    // sql 包下数据类型

    DATE_SQL("Date", "java.sql.Date", "string"),
    TIME("Time", "java.sql.Time", "string"),
    TIMESTAMP("Timestamp", "java.sql.Timestamp", "string"),
    BLOB("Blob", "java.sql.Blob", "string"),
    CLOB("Clob", "java.sql.Clob", "string"),

    // java8 新时间类型

    LOCAL_DATE("LocalDate", "java.time.LocalDate", "string"),
    LOCAL_TIME("LocalTime", "java.time.LocalTime", "string"),
    YEAR("Year", "java.time.Year", "string"),
    YEAR_MONTH("YearMonth", "java.time.YearMonth", "string"),
    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime", "string"),
    INSTANT("Instant", "java.time.Instant", "string"),

    // 其他杂类
    MAP("Map", "java.util.Map", "Map<string,any>"),
    BYTE_ARRAY("byte[]", null, "string"),
    OBJECT("Object", null, "any"),
    DATE("Date", "java.util.Date", "string"),
    BIG_INTEGER("BigInteger", "java.math.BigInteger", "string"),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal", "string"),


    ;
    /**
     * 类型
     */
    private final String type;

    /**
     * 包路径
     */
    private final String pkg;
    /**
     * TS类型
     */
    private final String tsType;

    DbColumnType(final String type, final String pkg, String tsType) {
        this.type = type;
        this.pkg = pkg;
        this.tsType = tsType;
    }


    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getPkg() {
        return pkg;
    }

    @Override
    public String getTsType() {
        return tsType;
    }
}
