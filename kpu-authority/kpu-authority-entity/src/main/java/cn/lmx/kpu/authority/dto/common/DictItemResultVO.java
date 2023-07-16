package cn.lmx.kpu.authority.dto.common;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.base.entity.TreeEntity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/08  15:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "DictItemResultVO", description = "DictItemResultVO")
public class DictItemResultVO extends TreeEntity<DictItemResultVO, Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 编码
     */
    @ApiModelProperty(value = "标识")
    private String key;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remark;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * css样式
     */
    @ApiModelProperty(value = "css样式")
    private String cssStyle;
    /**
     * 类选择器
     */
    @ApiModelProperty(value = "类选择器")
    private String cssClass;
}
