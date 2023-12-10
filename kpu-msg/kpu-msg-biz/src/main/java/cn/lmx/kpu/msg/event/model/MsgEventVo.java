package cn.lmx.kpu.msg.event.model;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.base.service.SuperService;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/10  22:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class MsgEventVo implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;

    private Long msgId;
    public void copy(){

    }
}
