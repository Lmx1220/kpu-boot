package cn.lmx.kpu.generator.vo.result;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.generator.enumeration.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
 * 代码生成
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "GenTableResultVO", description = "代码生成")
public class GenTableResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 表名称
     */
    @ApiModelProperty(value = "表名称")
    private String name;
    /**
     * 表描述
     */
    @ApiModelProperty(value = "表描述")
    private String comment;
    /**
     * swagger 描述
     */
    @ApiModelProperty(value = "swagger 描述")
    private String swaggerComment;
    /**
     * 数据源
     */
    @ApiModelProperty(value = "数据源")
    private Long dsId;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String author;
    /**
     * 关联子表ID
     */
    @ApiModelProperty(value = "关联子表ID")
    private Long subId;
    /**
     * 子表关联的外键名
     */
    @ApiModelProperty(value = "子表关联的外键字段名")
    private String subJavaFieldName;
    /**
     * 实体类名称
     */
    @ApiModelProperty(value = "实体类名称")
    private String entityName;
    /**
     * 实体父类;
     * #EntitySuperClassEnum{SUPER_ENTITY:01}
     */
    @ApiModelProperty(value = "实体父类")
    @Echo(api = Echo.ENUM_API)
    private EntitySuperClassEnum entitySuperClass;
    /**
     * 父类;
     * <p>
     * #SuperClassEnum{SUPER_CLASS:01}
     */
    @ApiModelProperty(value = "父类")
    @Echo(api = Echo.ENUM_API)
    private SuperClassEnum superClass;
    /**
     * 基础包路径
     */
    @ApiModelProperty(value = "基础包路径")
    private String parent;
    /**
     * 前端应用名;
     * 如：src/views目录下的 basic 和 devOperation
     * <p>
     * basic 表示基础平台
     * <p>
     * devOperation 表示开发运营系统
     * <p>
     * xxx 表示你们自己新建的xxx系统
     */
    @ApiModelProperty(value = "前端应用名")
    private String plusApplicationName;
    /**
     * 前端模块名;
     * 如：src/views/devOperation 目录下的文件夹名
     * 如：src/views/basic 目录下的文件夹名
     */
    @ApiModelProperty(value = "前端模块名")
    private String plusModuleName;
    /**
     * 服务名
     */
    @ApiModelProperty(value = "服务名")
    private String serviceName;
    /**
     * 模块名
     */
    @ApiModelProperty(value = "模块名")
    private String moduleName;
    /**
     * 子包名
     */
    @ApiModelProperty(value = "子包名")
    private String childPackageName;
    /**
     * 行级租户注解
     */
    @ApiModelProperty(value = "行级租户注解")
    private Boolean isTenantLine;
    /**
     * 数据源
     */
    @ApiModelProperty(value = "数据源")
    private String dsValue;
    /**
     * 数据源级租户注解
     */
    @ApiModelProperty(value = "数据源级租户注解")
    private Boolean isDs;
    /**
     * 是否为lombok模型
     */
    @ApiModelProperty(value = "是否为lombok模型")
    private Boolean isLombok;
    /**
     * 是否为链式模型
     */
    @ApiModelProperty(value = "是否为链式模型")
    private Boolean isChain;
    /**
     * 是否生成字段常量
     */
    @ApiModelProperty(value = "是否生成字段常量")
    private Boolean isColumnConstant;
    /**
     * 生成代码方式;
     * #GenTypeEnum{GEN:01,直接生成;ZIP:02,打包下载;}
     * （01zip压缩包 02自定义路径）
     */
    @ApiModelProperty(value = "生成代码方式")
    @Echo(api = Echo.ENUM_API)
    private GenTypeEnum genType;
    /**
     * 生成路径;
     * （不填默认项目路径）
     */
    @ApiModelProperty(value = "生成路径")
    private String outputDir;
    /**
     * 前端生成路径;
     * （不填默认项目路径）
     */
    @ApiModelProperty(value = "前端生成路径")
    private String frontOutputDir;
    /**
     * 使用的模板;
     * #TplEnum{SIMPLE:01,单表;TREE:02,树结构;MAIN_SUB:03,主从结构}
     */
    @ApiModelProperty(value = "使用的模板")
    @Echo(api = Echo.ENUM_API)
    private TplEnum tplType;
    /**
     * 弹窗方式;
     * #PopupTypeEnum{MODAL:01,对话框;DRAWER:02,抽屉;}
     */
    @ApiModelProperty(value = "弹窗方式")
    @Echo(api = Echo.ENUM_API)
    private PopupTypeEnum popupType;
    /**
     * 新增按钮权限编码
     */
    @ApiModelProperty(value = "新增按钮权限编码")
    private String addAuth;
    /**
     * 编辑按钮权限编码
     */
    @ApiModelProperty(value = "编辑按钮权限编码")
    private String editAuth;
    /**
     * 删除按钮权限编码
     */
    @ApiModelProperty(value = "删除按钮权限编码")
    private String deleteAuth;
    /**
     * 复制按钮权限编码
     */
    @ApiModelProperty(value = "复制按钮权限编码")
    private String copyAuth;
    @ApiModelProperty(value = "查看按钮权限编码")
    private String viewAuth;
    /**
     * 新增按钮是否显示
     */
    @ApiModelProperty(value = "新增按钮是否显示")
    private Boolean addShow;
    /**
     * 编辑按钮是否显示
     */
    @ApiModelProperty(value = "编辑按钮是否显示")
    private Boolean editShow;
    /**
     * 删除按钮是否显示
     */
    @ApiModelProperty(value = "删除按钮是否显示")
    private Boolean deleteShow;
    /**
     * 复制按钮是否显示
     */
    @ApiModelProperty(value = "复制按钮是否显示")
    private Boolean copyShow;
    /**
     * 详情按钮是否显示
     */
    @ApiModelProperty(value = "详情按钮是否显示")
    private Boolean viewShow;
    /**
     * 其它生成选项
     */
    @ApiModelProperty(value = "其它生成选项")
    private String options;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 上级菜单ID
     */
    @ApiModelProperty(value = "上级菜单ID")
    private Long menuParentId;
    /**
     * 所属应用ID
     */
    @ApiModelProperty(value = "所属应用ID")
    private Long menuApplicationId;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;
    /**
     * 父ID字段名
     */
    @ApiModelProperty(value = "父ID字段名")
    private Long treeParentId;
    /**
     * 名称字段名
     */
    @ApiModelProperty(value = "名称字段名")
    private String treeName;


}
