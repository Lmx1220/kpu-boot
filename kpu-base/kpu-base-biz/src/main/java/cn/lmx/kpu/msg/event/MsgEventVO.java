package cn.lmx.kpu.msg.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import cn.lmx.kpu.model.vo.BaseEventVO;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 PM
 * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
public class MsgEventVO extends BaseEventVO {
    Long msgId;
}
