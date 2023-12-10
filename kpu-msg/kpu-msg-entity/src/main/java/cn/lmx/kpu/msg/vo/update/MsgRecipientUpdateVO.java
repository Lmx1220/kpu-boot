package cn.lmx.kpu.msg.vo.update;

import cn.lmx.basic.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 消息接收人
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 21:43:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "MsgRecipientUpdateVO", description = "消息接收人")
public class MsgRecipientUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "请填写ID", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    @NotNull(message = "请填写任务ID")
    private Long msgId;
    /**
     * 扩展信息
     */
    @ApiModelProperty(value = "扩展信息")
    @Size(max = 255, message = "扩展信息长度不能超过{max}")
    private String ext;
    /**
     * 接收人
     */
    @ApiModelProperty(value = "接收人")
    @Size(max = 255, message = "接收人长度不能超过{max}")
    private String recipient;


}
