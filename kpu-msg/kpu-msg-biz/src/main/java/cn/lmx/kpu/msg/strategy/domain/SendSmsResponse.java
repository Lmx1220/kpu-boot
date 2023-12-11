package cn.lmx.kpu.msg.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/12  00:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsResponse implements Serializable {
    private SendStatus[] statusCode;
}