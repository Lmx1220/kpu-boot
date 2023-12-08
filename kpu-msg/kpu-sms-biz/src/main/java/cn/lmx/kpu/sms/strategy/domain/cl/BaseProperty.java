package cn.lmx.kpu.sms.strategy.domain.cl;

import lombok.Data;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/11/19  18:40
 */
@Data
public class BaseProperty {
    /* 是否debug模式 */
    private Boolean debug;
    // 校验参数是否合法
    public boolean initAndValid() {
        if (this.debug == null) {
            this.debug = false;
        }
        return true;
    }
}

