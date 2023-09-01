package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  09:41
 */
public enum GenTypeEnum implements BaseEnum {
    /**
     * 代码生成方式
     */
    GEN("GEN", "直接生成"),
    ZIP("ZIP", "zip压缩包 下载");

    /**
     * 编码
     */
    private String value;

    /**
     * 描述
     */
    private String desc;

    GenTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getCode() {
        return value;
    }
}
