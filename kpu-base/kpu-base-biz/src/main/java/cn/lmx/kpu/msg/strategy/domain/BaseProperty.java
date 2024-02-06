package cn.lmx.kpu.msg.strategy.domain;

import lombok.Data;

/**
 * @author lmx
 * @date 2024/02/07 0011 22:35
 */
@Data
public class BaseProperty {
    private Boolean debug;

    public boolean initAndValid() {
        if (this.debug == null) {
            this.debug = false;
        }
        return true;
    }
}
