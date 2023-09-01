package cn.lmx.kpu.generator.rules;

public interface ColumnType {
    /**
     * 获取字段类型
     *
     * @return 字段类型
     */
    String getType();

    /**
     * 获取字段类型完整名
     *
     * @return 字段类型完整名
     */
    String getPkg();

    /**
     * 获取TS字段类型
     *
     * @return TS字段类型
     */

    String getTsType();
}
