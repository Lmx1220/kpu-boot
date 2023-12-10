package cn.lmx.kpu.msg.strategy.domain;


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
public class MsgPublishVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 模板标识
     */
    @ApiModelProperty(value = "标题")
    @Size(max = 255, message = "标题长度不能超过{max}")
    @NotEmpty(message = "标题")
    private String title;
    /**
     * 模板标识
     */
    @ApiModelProperty(value = "内容")
    @Size(max = 255, message = "内容长度不能超过{max}")
    @NotEmpty(message = "内容")
    private String content;
    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;
    /**
     * 草稿
     */
    @ApiModelProperty(value = "草稿")
    private Boolean draft;
    /**
     * 发布人姓名
     */
    @ApiModelProperty(value = "发布人姓名")
    @Size(max = 255, message = "发布人姓名长度不能超过{max}")
    private String author;

    @ApiModelProperty(value = "接收人")
    @NotEmpty(message = "请选择接收人")
    private List<String> recipientList;




//
//    public MsgPublishVO addRecipient(String recipient) {
//        if (recipientList == null) {
//            recipientList = new ArrayList<>();
//        }
//        recipientList.add(recipient);
//        return this;
//    }
//
//
//
//    public MsgPublishVO clearRecipient() {
//        if (recipientList == null) {
//            recipientList = new ArrayList<>();
//        }
//        recipientList.clear();
//        return this;
//    }
}