package cn.lmx.kpu.model.entity.base;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.model.constant.EchoDictItem;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.lmx.kpu.model.constant.Condition.LIKE;
import static cn.lmx.kpu.model.constant.EchoApi.DICT_ITEM_CLASS;

/**
 * 角色
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@TableName("c_role")
public class SysRole extends Entity<Long> implements EchoVO {

    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();
    private static final long serialVersionUID = 1L;
    /**
     * 角色类别;[10-功能角色 20-桌面角色 30-数据角色]
     */
    @ApiModelProperty(value = "角色类别")
    @TableField(value = "category", condition = LIKE)
    @Size(max = 2, message = "角色类别长度不能超过{max}")
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.ROLE_CATEGORY)
    private String category;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 30, message = "名称长度不能超过30")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @Size(max = 20, message = "编码长度不能超过20")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 100, message = "描述长度不能超过100")
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    private Boolean state;

    /**
     * 内置角色
     */
    @ApiModelProperty(value = "内置角色")
    @TableField("readonly_")
    private Boolean readonly;


    /**
     * 角色列表转换成角色编码列表
     *
     */
    public static List<String> getRoleCode(List<SysRole> list) {
        if (ArrayUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(SysRole::getCode).collect(Collectors.toList());
    }

    /**
     * 指定角色编码是否在角色列表中
     *
     */
    public static boolean contains(List<SysRole> list, String code) {
        if (ArrayUtil.isEmpty(list) || StrUtil.isEmpty(code)) {
            return false;
        }
        return list.stream().anyMatch((item) -> code.equals(item.getCode()));
    }
}
