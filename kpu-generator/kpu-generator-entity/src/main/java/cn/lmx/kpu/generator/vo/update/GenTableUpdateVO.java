package cn.lmx.kpu.generator.vo.update;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.kpu.generator.enumeration.EntitySuperClassEnum;
import cn.lmx.kpu.generator.enumeration.GenTypeEnum;
import cn.lmx.kpu.generator.enumeration.SuperClassEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  15:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@ApiModel(value = "GenTableUpdateVO", description = "代码生成")
public class GenTableUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @NotNull(message = "请填写编号", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 表名称
     */
    @ApiModelProperty(value = "表名称")
    @NotEmpty(message = "请填写表名称")
    @Size(max = 200, message = "表名称长度不能超过{max}")
    private String name;
    /**
     * 表描述
     */
    @ApiModelProperty(value = "表描述")
    @Size(max = 500, message = "表描述长度不能超过{max}")
    private String comment;
    /**
     * swagger描述
     */
    @ApiModelProperty(value = "swagger描述")
    @Size(max = 255, message = "swagger描述长度不能超过{max}")
    private String swaggerComment;
    /**
     * 数据源
     */
    @ApiModelProperty(value = "数据源")
    @NotNull(message = "请填写数据源")
    private Long dsId;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    @NotEmpty(message = "请填写作者")
    @Size(max = 255, message = "作者长度不能超过{max}")
    private String author;
    /**
     * 关联子表的ID
     */
    @ApiModelProperty(value = "关联子表的ID")
    private Long subId;
    /**
     * 子表关联的外键Java字段名
     */
    @ApiModelProperty(value = "子表关联的外键Java字段名")
    @Size(max = 255, message = "子表关联的外键Java字段名长度不能超过{max}")
    private String subJavaFieldName;
    /**
     * 实体类名称
     */
    @ApiModelProperty(value = "实体类名称")
    @NotEmpty(message = "请填写实体类名称")
    @Size(max = 255, message = "实体类名称长度不能超过{max}")
    private String entityName;
    /**
     * 实体父类;
     * #EntitySuperClassEnum{SUPER_ENTITY:01}
     */
    @ApiModelProperty(value = "实体父类")
    @NotNull(message = "请填写实体父类")
    private EntitySuperClassEnum entitySuperClass;
    /**
     * 父类;
     * <p>
     * #SuperClassEnum{SUPER_CLASS:01}
     */
    @ApiModelProperty(value = "父类")
    @NotNull(message = "请填写父类")
    private SuperClassEnum superClass;
    /**
     * 基础包路径
     */
    @ApiModelProperty(value = "基础包路径")
    @NotEmpty(message = "请填写基础包路径")
    @Size(max = 255, message = "基础包路径长度不能超过{max}")
    private String parent;
    /**
     * 前端应用名;如：src/views目录下的basic和devOperation,basic表示基础平台。devOperation表示开发运营系统。xxx 表示你们自己新建的xxx系统。
     */
    @ApiModelProperty(value = "前端应用名")
    @Size(max = 255, message = "前端应用名长度不能超过{max}")
    private String plusApplicationName;
    /**
     * 前端模块名;如：src/views/devOperation目录下的文件夹名
     * 如：src/views/basic 目录下的文件夹名
     */
    @ApiModelProperty(value = "前端模块名")
    @Size(max = 255, message = "前端模块名长度不能超过{max}")
    private String plusModuleName;
    /**
     * 服务名
     */
    @ApiModelProperty(value = "服务名")
    @Size(max = 255, message = "服务名长度不能超过{max}")
    private String serviceName;
    /**
     * 模块名
     */
    @ApiModelProperty(value = "模块名")
    @Size(max = 255, message = "模块名长度不能超过{max}")
    private String moduleName;
    /**
     * 子包名
     */
    @ApiModelProperty(value = "子包名")
    @Size(max = 255, message = "子包名长度不能超过{max}")
    private String childPackageName;
    /**
     * 行级租户注解
     */
    @ApiModelProperty(value = "行级租户注解")
    @NotNull(message = "请填写行级租户注解")
    private Boolean isTenantLine;
    /**
     * 数据源
     */
    @ApiModelProperty(value = "数据源")
    @Size(max = 255, message = "数据源长度不能超过{max}")
    private String dsValue;
    /**
     * 数据源级租户注解
     */
    @ApiModelProperty(value = "数据源级租户注解")
    @NotNull(message = "请填写数据源级租户注解")
    private Boolean isDs;
    /**
     * 是否为lombok模型
     */
    @ApiModelProperty(value = "是否为lombok模型")
    @NotNull(message = "请填写是否为lombok模型")
    private Boolean isLombok;
    /**
     * 是否为链式模型
     */
    @ApiModelProperty(value = "是否为链式模型")
    @NotNull(message = "请填写是否为链式模型")
    private Boolean isChain;
    /**
     * 是否生成字段常量
     */
    @ApiModelProperty(value = "是否生成字段常量")
    @NotNull(message = "请填写是否生成字段常量")
    private Boolean isColumnConstant;
    /**
     * 生成代码方式;、; [01-zip压缩包 02-自定义路径]
     * #GenTypeEnum{GEN:01,直接生成;ZIP:02,打包下载;}
     */
    @ApiModelProperty(value = "生成代码方式")
    @NotNull(message = "请填写生成代码方式")
    private GenTypeEnum genType;
    /**
     * 生成路径;（不填默认项目路径）
     */
    @ApiModelProperty(value = "生成路径")
    @Size(max = 255, message = "生成路径长度不能超过{max}")
    private String outputDir;
    /**
     * 前端生成路径;（不填默认项目路径）
     */
    @ApiModelProperty(value = "前端生成路径")
    @Size(max = 255, message = "前端生成路径长度不能超过{max}")
    private String frontOutputDir;
    /**
     * 使用的模板; #TplEnum{SIMPLE:01,单表;TREE:02,树结构;MAIN_SUB:03,主从结构}
     */
//    @ApiModelProperty(value = "使用的模板")
//    @NotNull(message = "请填写使用的模板")
//    private TplEnum tplType;
//    /**
//     * 弹窗方式; #PopupTypeEnum{MODAL:01,对话框;DRAWER:02,抽屉;}
//     */
//    @ApiModelProperty(value = "弹窗方式")
//    @NotNull(message = "请填写弹窗方式")
//    private PopupTypeEnum popupType;
    /**
     * 新增按钮权限编码
     */
    @ApiModelProperty(value = "新增按钮权限编码")
    @Size(max = 255, message = "新增按钮权限编码长度不能超过{max}")
    private String addAuth;
    /**
     * 编辑按钮权限编码
     */
    @ApiModelProperty(value = "编辑按钮权限编码")
    @Size(max = 255, message = "编辑按钮权限编码长度不能超过{max}")
    private String editAuth;
    /**
     * 删除按钮权限编码
     */
    @ApiModelProperty(value = "删除按钮权限编码")
    @Size(max = 255, message = "删除按钮权限编码长度不能超过{max}")
    private String deleteAuth;
    /**
     * 查看按钮权限编码
     */
    @ApiModelProperty(value = "查看按钮权限编码")
    @Size(max = 255, message = "查看按钮权限编码长度不能超过{max}")
    private String viewAuth;
    /**
     * 复制按钮权限编码
     */
    @ApiModelProperty(value = "复制按钮权限编码")
    @Size(max = 255, message = "复制按钮权限编码长度不能超过{max}")
    private String copyAuth;
    /**
     * 新增按钮是否显示
     */
    @ApiModelProperty(value = "新增按钮是否显示")
    @NotNull(message = "请填写新增按钮是否显示")
    private Boolean addShow;
    /**
     * 编辑按钮是否显示
     */
    @ApiModelProperty(value = "编辑按钮是否显示")
    @NotNull(message = "请填写编辑按钮是否显示")
    private Boolean editShow;
    /**
     * 删除按钮是否显示
     */
    @ApiModelProperty(value = "删除按钮是否显示")
    @NotNull(message = "请填写删除按钮是否显示")
    private Boolean deleteShow;
    /**
     * 复制按钮是否显示
     */
    @ApiModelProperty(value = "复制按钮是否显示")
    @NotNull(message = "请填写复制按钮是否显示")
    private Boolean copyShow;
    /**
     * 详情按钮是否显示
     */
    @ApiModelProperty(value = "详情按钮是否显示")
    @NotNull(message = "请填写详情按钮是否显示")
    private Boolean viewShow;
    /**
     * 其它生成选项
     */
    @ApiModelProperty(value = "其它生成选项")
    @Size(max = 1000, message = "其它生成选项长度不能超过{max}")
    private String options;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
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
    @Size(max = 255, message = "菜单名称长度不能超过{max}")
    private String menuName;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @Size(max = 255, message = "菜单图标长度不能超过{max}")
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
    @Size(max = 255, message = "名称字段名长度不能超过{max}")
    private String treeName;


}