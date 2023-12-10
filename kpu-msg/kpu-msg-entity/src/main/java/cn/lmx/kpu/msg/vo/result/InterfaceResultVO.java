package cn.lmx.kpu.msg.vo.result;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoApi;
import cn.lmx.kpu.model.constant.EchoDictType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "InterfaceResultVO", description = "接口")
public class InterfaceResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "")
    private Long id;

    /**
    * 接口编码
    */
    @ApiModelProperty(value = "接口编码")
    private String code;
    /**
    * 接口名称
    */
    @ApiModelProperty(value = "接口名称")
    private String name;
    /**
    * 执行方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.INTERFACE_EXEC_MODE)[01-实现类 02-脚本]
    */
    @ApiModelProperty(value = "执行方式")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.INTERFACE_EXEC_MODE)
    private String execMode;
    /**
    * 实现脚本
    */
    @ApiModelProperty(value = "实现脚本")
    private String script;
    /**
    * 实现类
    */
    @ApiModelProperty(value = "实现类")
    private String implClass;
    /**
    * 状态
    */
    @ApiModelProperty(value = "状态")
    private Boolean state;



}
