package cn.lmx.kpu.authority.dto.auth;

import cn.lmx.basic.base.entity.TreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * menuList
 * 菜单资源树
 *
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Data
@ToString(callSuper = true)
public class MenuViewTreeVO extends TreeEntity<MenuViewTreeVO, Long> {
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    protected String title;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remarks;
    /**
     * 类型;[10-菜单 20-视图 30-按钮 40-字段 50-数据]
     */
    @ApiModelProperty(value = "类型")
    private String resourceType;

    /**
     * 通用菜单
     * True表示无需分配所有人就可以访问的
     */
    @ApiModelProperty(value = "通用菜单")
    private Boolean isGeneral;

    /**
     * 重定向
     */
    @ApiModelProperty(value = "重定向")
    private String redirect;
    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String path;

    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    private String component;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "激活菜单图标")
    private String activeIcon;

    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    private String group;


    /**
     * 数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]
     */
    @ApiModelProperty(value = "数据范围")
    private String dataScope;
    /**
     * 实现类;自定义实现类全类名
     */
    @ApiModelProperty(value = "实现类")
    private String customClass;

    /**
     * 是否默认
     */
    @ApiModelProperty(value = "是否默认")
    private Boolean isDef;

    /**
     * 树层级
     */
    @ApiModelProperty(value = "树层级")
    private Integer treeGrade;
    /**
     * 树路径;用id拼接树结构
     */
    @ApiModelProperty(value = "树路径")
    private String treePath;

    @ApiModelProperty(value = "元数据")
    private RouterMeta meta;

}
