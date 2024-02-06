package cn.lmx.kpu.msg.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 表单查询条件VO
 * 接口执行日志记录
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "接口执行日志记录")
public class ExtendInterfaceLoggingPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    /**
     * 接口日志ID;
     * #extend_interface_log
     */
    @Schema(description = "接口日志ID")
    private Long logId;
    /**
     * 执行时间
     */
    @Schema(description = "执行时间")
    private LocalDateTime execTime;
    /**
     * 执行状态;
     * [01-初始化 02-成功 03-失败]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_INTERFACE_LOGGING_STATUS)
     */
    @Schema(description = "执行状态")
    private String status;
    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String params;
    /**
     * 接口返回
     */
    @Schema(description = "接口返回")
    private String result;
    /**
     * 业务ID
     */
    @Schema(description = "业务ID")
    private Long bizId;

    @Schema(description = "异常信息")
    private String errorMsg;
}
