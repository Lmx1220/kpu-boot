package cn.lmx.kpu.msg.strategy.domain;

import lombok.Data;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/11/19  18:36
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
