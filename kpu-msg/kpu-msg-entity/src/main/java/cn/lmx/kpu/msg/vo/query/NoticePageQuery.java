package cn.lmx.kpu.msg.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 表单查询条件VO
 * 通知表
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "NoticePageQuery", description = "通知表")
public class NoticePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
    * 发布人
    */
    @ApiModelProperty(value = "发布人")
    private String author;
    /**
    * 自动已读
    */
    @ApiModelProperty(value = "自动已读")
    private Boolean autoRead;
    /**
    * 业务ID
    */
    @ApiModelProperty(value = "业务ID")
    private Long bizId;
    /**
    * 业务类型
    */
    @ApiModelProperty(value = "业务类型")
    private String bizType;
    /**
    * 内容
    */
    @ApiModelProperty(value = "内容")
    private String content;
    /**
    * 处理地址
    */
    @ApiModelProperty(value = "处理地址")
    private String url;
    /**
    * 标题
    */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
    * 打开方式
    */
    @ApiModelProperty(value = "打开方式")
    private String target;
    /**
    * 提醒方式
    */
    @ApiModelProperty(value = "提醒方式")
    private String remindMode;
    /**
    * 接收人
    */
    @ApiModelProperty(value = "接收人")
    private Long recipientId;
    /**
    * 消息ID
    */
    @ApiModelProperty(value = "消息ID")
    private Long msgId;
    /**
    * 读取时间
    */
    @ApiModelProperty(value = "读取时间")
    private LocalDateTime readTime;
    /**
    * 是否已读
    */
    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;
    /**
    * 是否处理
    */
    @ApiModelProperty(value = "是否处理")
    private Boolean isHandle;
    /**
    * 处理时间
    */
    @ApiModelProperty(value = "处理时间")
    private LocalDateTime handleTime;
    /**
    * 所属组织
    */
    @ApiModelProperty(value = "所属组织")
    private Long createdOrgId;



}
