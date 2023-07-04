package cn.lmx.kpu.authority.event.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 登录状态DTO
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class ParameterUpdate implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;

    private String key;
    private String value;
    private String oldValue;

    private String tenant;
}
