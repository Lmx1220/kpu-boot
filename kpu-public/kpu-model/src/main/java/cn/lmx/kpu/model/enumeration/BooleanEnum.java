package cn.lmx.kpu.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否
 *
 * @author lmx
 * @date 2024/02/07  02:04 上午
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum {
    /**
     * true
     */
    TRUE(true, 1, "1", "是"),
    /**
     * false
     */
    FALSE(false, 0, "0", "否"),
    ;
    private final Boolean bool;
    private final int integer;
    private final String str;
    private final String describe;

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
