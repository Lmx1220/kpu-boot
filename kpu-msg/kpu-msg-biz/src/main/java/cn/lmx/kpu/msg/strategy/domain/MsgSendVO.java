package cn.lmx.kpu.msg.strategy.domain;

import cn.lmx.basic.model.Kv;
import cn.lmx.kpu.msg.vo.save.MsgRecipientSaveVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/12/10  19:24
 */
@Data
@AllArgsConstructor
@Builder
public class MsgSendVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模板标识
     */
    @ApiModelProperty(value = "模板标识")
    @Size(max = 255, message = "模板标识长度不能超过{max}")
    @NotEmpty(message = "模板标识")
    private String templateCode;
    /**
     * 参数;
     * <p>
     * 需要封装为[{{‘key’:'', ’value’:''}, {'key2':'', 'value2':''}]格式
     */
    @ApiModelProperty(value = "参数")
    private List<Kv> paramList;

    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;
    /**
     * 业务ID
     */
    @ApiModelProperty(value = "业务ID")
    private Long bizId;
    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    @Size(max = 255, message = "业务类型长度不能超过{max}")
    private String bizType;
    /**
     * 发布人姓名
     */
    @ApiModelProperty(value = "发布人姓名")
    @Size(max = 255, message = "发布人姓名长度不能超过{max}")
    private String author;

    @ApiModelProperty(value = "接收人")
    @NotEmpty(message = "请选择接收人")
    private List<MsgRecipientSaveVO> recipientList;


    public MsgSendVO addParam(String key, String value) {
        if (paramList == null) {
            paramList = new ArrayList<>();
        }
        paramList.add(Kv.builder().key(key).value(value).build());
        return this;
    }

    public MsgSendVO addRecipient(String recipient, String ext) {
        if (recipientList == null) {
            recipientList = new ArrayList<>();
        }
        recipientList.add(MsgRecipientSaveVO.builder().recipient(recipient).ext(ext).build());
        return this;
    }

    public MsgSendVO addRecipient(String recipient) {
        if (recipientList == null) {
            recipientList = new ArrayList<>();
        }
        recipientList.add(MsgRecipientSaveVO.builder().recipient(recipient).build());
        return this;
    }

    public MsgSendVO addParam(Kv kv) {
        if (paramList == null) {
            paramList = new ArrayList<>();
        }
        paramList.add(kv);
        return this;
    }

    public MsgSendVO addRecipient(MsgRecipientSaveVO recipient) {
        if (recipientList == null) {
            recipientList = new ArrayList<>();
        }
        recipientList.add(recipient);
        return this;
    }

    public MsgSendVO clearParam() {
        if (paramList == null) {
            paramList = new ArrayList<>();
        }
        paramList.clear();
        return this;
    }

    public MsgSendVO clearRecipient() {
        if (recipientList == null) {
            recipientList = new ArrayList<>();
        }
        recipientList.clear();
        return this;
    }
}