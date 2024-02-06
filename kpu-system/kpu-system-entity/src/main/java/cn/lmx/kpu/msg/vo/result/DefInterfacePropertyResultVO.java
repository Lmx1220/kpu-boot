package cn.lmx.kpu.msg.vo.result;

import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
 * 接口属性
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
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(description = "接口属性")
public class DefInterfacePropertyResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "ID")
    private Long id;

    /**
     * 接口ID
     */
    @Schema(description = "接口ID")
    private Long interfaceId;
    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    private String name;
    /**
     * 参数键
     */
    @Schema(description = "参数键")
    private String key;
    /**
     * 参数值
     */
    @Schema(description = "参数值")
    private String value;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer sortValue;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;


}
