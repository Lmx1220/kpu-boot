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
public class ClSmsProperty extends BaseProperty {
    private final static String DEF_END_POINT = "http://smssh1.253.com/msg/v1/send/json";
    private final static String DEF_VARIABLE_END_POINT = "http://smssh1.253.com/msg/variable/json";

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 普通接口 发送接口
     */
    private String endPoint;
    /**
     * 变量接口 发送接口
     */
    private String variableEndPoint;
    /**
     * 是否变量短信
     */
    private Boolean variable;

    @Override
    public boolean initAndValid() {
        super.initAndValid();
        if (variable == null) {
            variable = true;
        }
        if (StrUtil.isEmpty(endPoint)) {
            endPoint = DEF_END_POINT;
        }
        if (StrUtil.isEmpty(variableEndPoint)) {
            variableEndPoint = DEF_VARIABLE_END_POINT;
        }
        ArgumentAssert.notEmpty(this.account, "account 不能为空");
        ArgumentAssert.notEmpty(this.password, "password 不能为空");
        return true;
    }
}
