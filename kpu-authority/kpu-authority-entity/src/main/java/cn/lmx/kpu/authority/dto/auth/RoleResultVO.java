package cn.lmx.kpu.authority.dto.auth;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoDictItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

import static cn.lmx.kpu.model.constant.EchoApi.DICT_ITEM_CLASS;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/08  14:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "TestUserResultVO", description = "用户")
public class RoleResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 角色类别;[10-功能角色 20-桌面角色 30-数据角色]
     */
    @ApiModelProperty(value = "角色类别")
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.ROLE_CATEGORY)
    private String category;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remarks;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;

    /**
     * 内置角色
     */
    @ApiModelProperty(value = "内置角色")
    private Boolean readonly;

}
