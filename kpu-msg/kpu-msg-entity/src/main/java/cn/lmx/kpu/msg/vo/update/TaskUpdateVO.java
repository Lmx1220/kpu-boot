package cn.lmx.kpu.msg.vo.update;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.kpu.msg.enumeration.SourceType;
import cn.lmx.kpu.msg.enumeration.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 表单修改方法VO
 * 发送任务
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "TaskUpdateVO", description = "发送任务")
public class TaskUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信记录ID")
    @NotNull(message = "请填写短信记录ID", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 短信模板 @Echo(api = EchoApi.SMS_TEMPLATE_ID_CLASS) #e_sms_template
     */
    @ApiModelProperty(value = "短信模板 @Echo(api = EchoApi.SMS_TEMPLATE_ID_CLASS) #e_sms_template")
    @NotNull(message = "请填写短信模板 @Echo(api = EchoApi.SMS_TEMPLATE_ID_CLASS) #e_sms_template")
    private Long templateId;
    /**
     * 接收者手机
     * 群发用英文逗号分割.
     * 支持2种 格式:1: 手机号,手机号  格式2: 姓名<手机号>,姓名<手机号>
     */
    @ApiModelProperty(value = "接收者手机")
    @Size(min = 1, message = "请填写接收者手机")
    private List<String> telNum;
    /**
     * 参数
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
     */
    @ApiModelProperty(value = "参数")
    private LinkedHashMap<String, String> templateParam;
    /**
     * 执行状态 
     * (手机号具体发送状态看sms_send_status表) 
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @ApiModelProperty(value = "执行状态  (手机号具体发送状态看sms_send_status表)  #TaskStatus{WAITING:等待执行")
    private TaskStatus status;
    /**
     * 发送渠道
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @ApiModelProperty(value = "发送渠道 #SourceType{APP:应用")
    private SourceType sourceType;
    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    @Size(max = 255, message = "主题长度不能超过{max}")
    private String topic;
    /**
     * 参数 
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
     */
    @ApiModelProperty(value = "参数  需要封装为{‘key’:’value’, ...}格式且key必须有序")
    @Size(max = 500, message = "参数  需要封装为{‘key’:’value’, ...}格式且key必须有序长度不能超过{max}")
    private String templateParams;
    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;
    /**
     * 发送内容 
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    @ApiModelProperty(value = "发送内容  需要封装正确格式化: 您好，张三，您有一个新的快递。")
    @Size(max = 500, message = "发送内容  需要封装正确格式化: 您好，张三，您有一个新的快递。长度不能超过{max}")
    private String content;
    /**
     * 是否草稿
     */
    @ApiModelProperty(value = "是否草稿")
    private Boolean draft;
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
