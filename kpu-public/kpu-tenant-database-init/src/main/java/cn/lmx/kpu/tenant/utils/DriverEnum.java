package cn.lmx.kpu.tenant.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lmx
 */
@Slf4j
public enum DriverEnum {

    /**
     * mysql
     */
    MYSQL5("com.mysql.jdbc.Driver"),
    MYSQL6("com.mysql.cj.jdbc.Driver"),

    /**
     * oracle
     */
    ORACLE("oracle.jdbc.driver.OracleDriver"),

    /**
     * sql server
     */
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    ;

    /**
     * 驱动
     */
    private final String driver;

    DriverEnum(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }

    public static DriverEnum findEnumByType(String type) {
        for (DriverEnum value : DriverEnum.values()) {
            if (value.name().equalsIgnoreCase(type)) {
                return value;
            }
        }
        return null;
    }
}
