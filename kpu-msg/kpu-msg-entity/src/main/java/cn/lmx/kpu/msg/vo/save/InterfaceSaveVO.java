package cn.lmx.kpu.msg.vo.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 表单保存方法VO
 * 接口
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
@ApiModel(value = "InterfaceSaveVO", description = "接口")
public class InterfaceSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口编码
     */
    @ApiModelProperty(value = "接口编码")
    @NotEmpty(message = "请填写接口编码")
    @Size(max = 255, message = "接口编码长度不能超过{max}")
    private String code;
    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @NotEmpty(message = "请填写接口名称")
    @Size(max = 255, message = "接口名称长度不能超过{max}")
    private String name;
    /**
     * 执行方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.INTERFACE_EXEC_MODE)[01-实现类 02-脚本]
     */
    @ApiModelProperty(value = "执行方式")
    @Size(max = 2, message = "执行方式长度不能超过{max}")
    private String execMode;
    /**
     * 实现脚本
     */
    @ApiModelProperty(value = "实现脚本")
    @Size(max = 65535, message = "实现脚本长度不能超过{max}")
    private String script;
    /**
     * 实现类
     */
    @ApiModelProperty(value = "实现类")
    @Size(max = 255, message = "实现类长度不能超过{max}")
    private String implClass;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;



}
