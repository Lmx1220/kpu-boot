package cn.lmx.kpu.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.TreeEntity;
import cn.lmx.kpu.model.constant.EchoDictItem;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static cn.lmx.kpu.model.constant.Condition.LIKE;
import static cn.lmx.kpu.model.constant.EchoApi.DICTIONARY_ITEM_FEIGN_CLASS;
import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_resource")
@ApiModel(value = "Resource", description = "菜单")
@AllArgsConstructor
public class Resource extends TreeEntity<Resource, Long> {

    private static final long serialVersionUID = 1L;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @NotEmpty(message = "标题不能为空")
    @Size(max = 255, message = "标题长度不能超过255")
    @TableField(value = "title")
    protected String title;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @Size(max = 500, message = "编码长度不能超过500")
    @TableField(value = "code", condition = LIKE)
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    @TableField(value = "name")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 200, message = "描述长度不能超过200")
    @TableField(value = "remarks", condition = LIKE)
    @Excel(name = "描述")
    private String remarks;
    /**
     * 类型;[10-菜单 20-视图 30-按钮 40-字段 50-数据]
     */
    @ApiModelProperty(value = "类型")
    @Size(max = 2, message = "类型长度不能超过{max}")
    @TableField(value = "resource_type", condition = LIKE)
    @Excel(name = "类型")
    @Echo(api = DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictItem.RESOURCE_TYPE)
    private String resourceType;

    /**
     * 通用菜单
     * True表示无需分配所有人就可以访问的
     */
    @ApiModelProperty(value = "通用菜单")
    @TableField("is_general")
    @Excel(name = "通用菜单", replace = {"是_true", "否_false", "_null"})
    private Boolean isGeneral;

    /**
     * 重定向
     */
    @ApiModelProperty(value = "重定向")
    @Size(max = 255, message = "重定向长度不能超过255")
    @TableField(value = "redirect", condition = LIKE)
    @Excel(name = "重定向")
    private String redirect;
    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    @Size(max = 255, message = "路径长度不能超过255")
    @TableField(value = "path", condition = LIKE)
    @Excel(name = "路径")
    private String path;

    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    @Size(max = 255, message = "组件长度不能超过255")
    @TableField(value = "component", condition = LIKE)
    @Excel(name = "组件")
    private String component;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @Size(max = 255, message = "菜单图标长度不能超过255")
    @TableField(value = "icon", condition = LIKE)
    @Excel(name = "菜单图标")
    private String icon;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "激活菜单图标")
    @Size(max = 255, message = "激活菜单图标长度不能超过255")
    @TableField(value = "active_icon", condition = LIKE)
    @Excel(name = "菜单图标")
    private String activeIcon;

    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    @Size(max = 20, message = "分组长度不能超过20")
    @TableField(value = "group_", condition = LIKE)
    @Excel(name = "分组")
    private String group;


    /**
     * 数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]
     */
    @ApiModelProperty(value = "数据范围")
    @TableField(value = "data_scope", condition = LIKE)
    @Size(max = 2, message = "数据范围长度不能超过{max}")
    private String dataScope;
    /**
     * 实现类;自定义实现类全类名
     */
    @ApiModelProperty(value = "实现类")
    @TableField(value = "custom_class", condition = LIKE)
    @Size(max = 255, message = "实现类长度不能超过{max}")
    private String customClass;

    /**
     * 是否默认
     */
    @ApiModelProperty(value = "是否默认")
    @TableField(value = "is_def")
    private Boolean isDef;

    /**
     * 树层级
     */
    @ApiModelProperty(value = "树层级")
    @TableField(value = "tree_grade", condition = EQUAL)
    private Integer treeGrade;
    /**
     * 树路径;用id拼接树结构
     */
    @ApiModelProperty(value = "树路径")
    @TableField(value = "tree_path", condition = LIKE)
    private String treePath;
    /**
     * 路由元数据
     */
    @ApiModelProperty(value = "路由元数据")
    @TableField(value = "meta_json", condition = LIKE)
    private String metaJson;

    @Builder
    public Resource(Long id, String code, String title, String name, Integer sortValue, Long parentId, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                String remarks, Boolean isGeneral, String redirect, String path, String component, Boolean state, String resourceType,
                String icon, String activeIcon, String group, Boolean readonly, String dataScope, String customClass, Boolean isDef, Integer treeGrade, String treePath, String metaJson) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.name = name;
        this.sortValue = sortValue;
        this.parentId = parentId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.remarks = remarks;
        this.isGeneral = isGeneral;
        this.redirect = redirect;
        this.path = path;
        this.component = component;
        this.state = state;
        this.icon = icon;
        this.activeIcon = activeIcon;
        this.resourceType = resourceType;
        this.group = group;
        this.dataScope = dataScope;
        this.customClass = customClass;
        this.isDef = isDef;
        this.treeGrade = treeGrade;
        this.treePath = treePath;
        this.metaJson = metaJson;
    }

}
