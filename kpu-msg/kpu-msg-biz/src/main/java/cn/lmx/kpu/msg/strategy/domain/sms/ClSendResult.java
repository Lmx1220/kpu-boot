package cn.lmx.kpu.msg.strategy.domain.sms;

import lombok.Data;

/**
 * @author lmx
 * @date 2023/11/19  18:40
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
