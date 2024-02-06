package cn.lmx.kpu.msg.strategy.domain.sms;

import lombok.Data;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Data
public class ClSendResult {
    private String code;
    private String failNum;
    private String successNum;
    private String msgId;
    private String time;
    private String errorMsg;

}
