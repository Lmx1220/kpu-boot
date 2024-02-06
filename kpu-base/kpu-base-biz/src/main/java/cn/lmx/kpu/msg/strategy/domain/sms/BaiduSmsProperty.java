package cn.lmx.kpu.msg.strategy.domain.sms;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.msg.strategy.domain.BaseProperty;

/**
 * @author lmx
 * @date 2024/02/07 0010 18:56
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaiduSmsProperty extends BaseProperty {
    private final static String DEF_END_POINT = "http://smsv3.bj.baidubce.com";
    /**
     * accessKeyId
     */
    private String accessKeyId;
    /**
     * secretKey
     */
    private String secretKey;
    /**
     * 域名
     */
    private String endPoint;

    @Override
    public boolean initAndValid() {
        super.initAndValid();
        if (StrUtil.isEmpty(endPoint)) {
            endPoint = DEF_END_POINT;
        }
        ArgumentAssert.notEmpty(accessKeyId, "accessKeyId 不能为空");
        ArgumentAssert.notEmpty(secretKey, "secretKey 不能为空");
        return true;
    }
}
