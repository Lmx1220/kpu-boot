package cn.lmx.kpu.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import cn.lmx.basic.constant.Constants;
import cn.lmx.kpu.generator.enumeration.GenTypeEnum;
import cn.lmx.kpu.generator.enumeration.ProjectTypeEnum;
import cn.lmx.kpu.generator.enumeration.SuperClassEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取代码生成相关配置
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = GeneratorConfig.PREFIX)
public class GeneratorConfig {
    public static final String PREFIX = Constants.PROJECT_PREFIX + ".generator";
    /**
     * 后端工程 kpu-cloud-pro-datasource-column 代码生成跟路径
     */
    public String outputDir;
    /**
     * 前端工程kpu-web-plus 跟路径
     */
    public String frontOutputDir;
    /** 默认项目 */
    public ProjectTypeEnum projectType = ProjectTypeEnum.CLOUD;

    /**
     * 作者
     */
    public String author = "lmx";
    /**
     * 去除表前缀(类名不会包含表前缀)
     */
    public List<String> tablePrefix = new ArrayList<>();
    /**
     * 去除字段前缀
     */
    public List<String> fieldPrefix = new ArrayList<>();
    /**
     * 去除字段后缀
     */
    public List<String> fieldSuffix = new ArrayList<>();
    /**
     * 后端项目前缀
     * 项目部分公共文件夹的名称
     * <p>
     * 如：后端 kpu-base、kpu-base-api、kpu-base-entity、kpu-base-biz、kpu-base-controller、kpu-base-server 等模块的前缀：kpu
     * 如：前端 src/api/kpu/xxx
     * 如：前端 src/utils/kpu/xxx
     * 如：前端 src/utils/kpu/xxx
     */
    private String projectPrefix = Constants.PROJECT_PREFIX;

    /**
     * 其他类的父类
     */
    private SuperClassEnum superClass = SuperClassEnum.SUPER_CLASS;

    /**
     * 生成方式
     */
    private GenTypeEnum genType = GenTypeEnum.GEN;

    /**
     * 包配置
     */
    @NestedConfigurationProperty
    private PackageInfoConfig packageInfoConfig = new PackageInfoConfig();

    /**
     * 实体 VO 配置
     */
    @NestedConfigurationProperty
    private EntityConfig entityConfig = new EntityConfig();
    /**
     * Mapper 配置
     */
    @NestedConfigurationProperty
    private MapperConfig mapperConfig = new MapperConfig();
    /**
     * Service 配置
     */
    @NestedConfigurationProperty
    private ServiceConfig serviceConfig = new ServiceConfig();
    /**
     * Manager 配置
     */
    @NestedConfigurationProperty
    private ManagerConfig managerConfig = new ManagerConfig();
    /**
     * Controller 配置
     */
    @NestedConfigurationProperty
    private ControllerConfig controllerConfig = new ControllerConfig();
    /**
     * Web 端配置
     */
    @NestedConfigurationProperty
    private WebProConfig webProConfig = new WebProConfig();
    /** 文件覆盖策略 */
    @NestedConfigurationProperty
    private FileOverrideStrategy fileOverrideStrategy = new FileOverrideStrategy();

    /**
     * 常用的常量、枚举包
     * 配置后，若生成代码时，枚举和常量名称与{constantsPackage.key}一致，就不会重复生成，而是采用{constantsPackage.value}的类
     */
    private Map<String, Class<?>> constantsPackage = new HashMap<>();

}
