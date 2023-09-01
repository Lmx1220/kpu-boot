package cn.lmx.kpu.generator.entity;

import lombok.Data;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  12:29
 */
@Data
public class Column {
    private String name;
    private String comment;
    private String columnDef;
    private String typeName;
    private Long size;
    private Integer digits;
    /**
     * 是否主键
     */
    private boolean pk;
    private boolean nullable;
    private boolean autoIncrement;

    public boolean isPk() {
        return pk;
    }

    public boolean isNullable() {
        return nullable;
    }

    public boolean isAutoIncrement() {
        return nullable;
    }
}
