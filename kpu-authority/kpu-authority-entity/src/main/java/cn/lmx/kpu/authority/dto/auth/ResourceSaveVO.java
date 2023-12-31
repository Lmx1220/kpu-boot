package cn.lmx.kpu.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 菜单
 * </p>
 *
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "ResourceSaveVO", description = "菜单")
public class ResourceSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "请填写名称")
    @Size(max = 255, message = "名称长度不能超过255")
    protected String title;
    @ApiModelProperty(value = "组件名称")
    protected String name;
    @ApiModelProperty(value = "上级资源")
    protected Long parentId;
    @ApiModelProperty(value = "排序号")
    protected Integer sortValue;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 200, message = "描述长度不能超过200")
    private String remarks;
    /**
     * 路径
     */
    @ApiModelProperty(value = "地址栏地址")
//    @NotEmpty(message = "请填写地址栏地址")
    @Size(max = 255, message = "地址栏地址长度不能超过255")
    private String path;
    /**
     * 组件
     */
    @ApiModelProperty(value = "页面代码地址")
//    @NotEmpty(message = "请填写页面代码地址")
    @Size(max = 255, message = "页面代码地址长度不能超过255")
    private String component;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "图标")
    @Size(max = 255, message = "图标长度不能超过255")
    private String icon;
    @ApiModelProperty(value = "激活菜单图标")
    @Size(max = 255, message = "激活菜单图标长度不能超过255")
    private String activeIcon;
    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    @Size(max = 20, message = "分组长度不能超过20")
    private String group;
    /**
     * 通用菜单
     * True表示无需分配所有人就可以访问的
     */
    @ApiModelProperty(value = "通用菜单")
    private Boolean isGeneral;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    private Boolean readonly;
    /**
     * 类型;[10-菜单 20-视图 30-按钮 40-字段 50-数据]
     */
    @ApiModelProperty(value = "类型")
    @Size(max = 2, message = "类型长度不能超过{max}")
    private String resourceType;
    /**
     * 数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]
     */
    @ApiModelProperty(value = "数据范围")
    @Size(max = 2, message = "数据范围长度不能超过{max}")
    private String dataScope;
    /**
     * 实现类;自定义实现类全类名
     */
    @ApiModelProperty(value = "实现类")
    @Size(max = 255, message = "实现类长度不能超过{max}")
    private String customClass;

    /**
     * 是否默认
     */
    @ApiModelProperty(value = "是否默认")
    private Boolean isDef;
    @ApiModelProperty(value = "路由元数据")
    private String metaJson;
    /**
     * 权限集合
     */
    private List<AuthDto> auths;

}
