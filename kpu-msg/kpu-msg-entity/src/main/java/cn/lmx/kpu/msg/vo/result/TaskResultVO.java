package cn.lmx.kpu.msg.vo.result;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoApi;
import cn.lmx.kpu.msg.enumeration.SourceType;
import cn.lmx.kpu.msg.enumeration.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "TaskResultVO", description = "发送任务")
public class TaskResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "短信记录ID")
    private Long id;

    /**
    * 短信模板 @Echo(api = EchoApi.SMS_TEMPLATE_ID_CLASS) #e_sms_template
    */
    @ApiModelProperty(value = "短信模板 @Echo(api = EchoApi.SMS_TEMPLATE_ID_CLASS) #e_sms_template")
    @Echo(api = EchoApi.SMS_TEMPLATE_ID_CLASS)
    private Long templateId;
    /**
    * 执行状态 
     * (手机号具体发送状态看sms_send_status表) 
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
    */
    @ApiModelProperty(value = "执行状态  (手机号具体发送状态看sms_send_status表)  #TaskStatus{WAITING:等待执行")
    @Echo(api = Echo.ENUM_API)
    private TaskStatus status;
    /**
    * 发送渠道
     * #SourceType{APP:应用;SERVICE:服务}
    */
    @ApiModelProperty(value = "发送渠道 #SourceType{APP:应用")
    @Echo(api = Echo.ENUM_API)
    private SourceType sourceType;
    /**
    * 主题
    */
    @ApiModelProperty(value = "主题")
    private String topic;
    /**
    * 参数 
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
    */
    @ApiModelProperty(value = "参数  需要封装为{‘key’:’value’, ...}格式且key必须有序")
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
