package cn.lmx.kpu.msg.event.model;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.model.vo.BaseEventVO;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/10  22:30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
public class MsgEventVO extends BaseEventVO {
    Long msgId;
}

