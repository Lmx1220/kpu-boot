package cn.lmx.kpu.sms.vo.save;

import cn.lmx.kpu.sms.enumeration.SendStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
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
 * 表单保存方法VO
 * 短信发送状态
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "ESmsSendStatusSaveVO", description = "短信发送状态")
public class ESmsSendStatusSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID 
     * #e_sms_task
     */
    @ApiModelProperty(value = "任务ID  #e_sms_task")
    @NotNull(message = "请填写任务ID  #e_sms_task")
    private Long taskId;
    /**
     * 发送状态 
     * #SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}
     */
    @ApiModelProperty(value = "发送状态  #SendStatus{WAITING:等待发送")
    @NotNull(message = "请填写发送状态  #SendStatus{WAITING:等待发送")
    private SendStatus sendStatus;
    /**
     * 接收者手机
     * 单个手机号 
     * 阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID
     */
    @ApiModelProperty(value = "接收者手机 单个手机号  阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID")
    @NotEmpty(message = "请填写接收者手机 单个手机号  阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID")
    @Size(max = 20, message = "接收者手机 单个手机号  阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID长度不能超过{max}")
    private String telNum;
    /**
     * 发送回执ID
     */
    @ApiModelProperty(value = "发送回执ID")
    @Size(max = 255, message = "发送回执ID长度不能超过{max}")
    private String bizId;
    /**
     * 发送返回 
     * 阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无
     */
    @ApiModelProperty(value = "发送返回  阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无")
    @Size(max = 255, message = "发送返回  阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无长度不能超过{max}")
    private String ext;
    /**
     * 状态码 
     * 阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功
     */
    @ApiModelProperty(value = "状态码  阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功")
    @Size(max = 255, message = "状态码  阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功长度不能超过{max}")
    private String code;
    /**
     * 状态码的描述
     */
    @ApiModelProperty(value = "状态码的描述")
    @Size(max = 500, message = "状态码的描述长度不能超过{max}")
    private String message;
    /**
     * 短信计费的条数
     * 腾讯专用
     */
    @ApiModelProperty(value = "短信计费的条数 腾讯专用")
    private Integer fee;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;



}
