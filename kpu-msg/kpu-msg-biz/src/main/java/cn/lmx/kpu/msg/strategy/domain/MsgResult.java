package cn.lmx.kpu.msg.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/11/19  18:36
 */
// 出参是脚本需要返回的，并且需要将3个参数都返回
@Data
@AllArgsConstructor
@Builder
public class MsgResult {
    /** 消息标题 */
    private String title;
    /** 消息内容 */
    private String content;
    /** 返回结果 */
    private Object result;
}
