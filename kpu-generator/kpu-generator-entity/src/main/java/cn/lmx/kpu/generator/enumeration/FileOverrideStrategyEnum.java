package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileOverrideStrategyEnum implements BaseEnum {
    OVERRIDE("OVERRIDE", "覆盖"),
    ADD("ADD", "新增"),
    IGNORE("IGNORE", "忽略"),
    EXIST_IGNORE("EXIST_IGNORE", "存在时忽略");

    private String value;
    private String desc;

    @Override
    public String getDesc() {
        return desc;
    }

    public boolean eq(FileOverrideStrategyEnum val) {
        return val != null && eq(val.name());
    }
}
