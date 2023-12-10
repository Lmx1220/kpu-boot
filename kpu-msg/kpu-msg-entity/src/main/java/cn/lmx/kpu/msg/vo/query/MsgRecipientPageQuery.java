package cn.lmx.kpu.msg.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
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
 * 表单查询条件VO
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
@ApiModel(value = "MsgRecipientPageQuery", description = "消息接收人")
public class MsgRecipientPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 任务ID
    */
    @ApiModelProperty(value = "任务ID")
    private Long msgId;
    /**
    * 扩展信息
    */
    @ApiModelProperty(value = "扩展信息")
    private String ext;
    /**
    * 接收人
    */
    @ApiModelProperty(value = "接收人")
    private String recipient;



}
