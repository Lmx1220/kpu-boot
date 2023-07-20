package cn.lmx.kpu.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum {
    TRUE(true, 1, "1", "是"),
    FALSE(false, 0, "0", "否"),
    ;
    private Boolean bool;
    private int integer;
    private String str;
    private String remarks;

    public boolean eq(Integer val) {
        if (val == null) {
            return FALSE.getBool();
        }
        return val.equals(this.getInteger());
    }

    public boolean eq(String val) {
        if (val == null) {
            return FALSE.getBool();
        }
        return val.equals(this.getStr());
    }

    public boolean eq(Boolean val) {
        if (val == null) {
            return FALSE.getBool();
        }
        return val.equals(this.getBool());
    }
}
