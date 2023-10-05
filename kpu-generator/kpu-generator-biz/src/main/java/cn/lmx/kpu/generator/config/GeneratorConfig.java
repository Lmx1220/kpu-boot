package cn.lmx.kpu.generator.config;

import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.constant.Constants;
import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;
import cn.lmx.kpu.generator.type.SuperClass;
import cn.lmx.kpu.generator.type.VueVersion;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/19  11:36
 */
@Getter
@Setter
//@RefreshScope
@Configuration
@ConfigurationProperties(prefix = GeneratorConfig.PREFIX)
public class GeneratorConfig {
    public static final String PREFIX = Constants.PROJECT_PREFIX + ".generator";
    public SuperClass superClass = SuperClass.SUPER_CLASS;
    /**
     * 项目跟路径
     */
    String projectRootPath = System.getProperty("user.dir");
    /**
     * 服务名
     * 如消息服务 (用于创建cloud-%s-api、cloud-%s-entity等项目)
     */
    String serviceName = "";
    /**
     * 子模块名称
     * 如消息服务(cloud-msgs-new)包含消息、短信、邮件3个模块
     * 则分别填入 msgs、sms、email
     * (用于创建cloud-%s-rest、cloud-%s-repository等项目)
     */
    String childModuleName = "";
    /**
     * 子模块是否单独生成 lamp-{childModuleName}-entity模块
     */
    Boolean isGenEntity = false;
    /**
     * 是否lamp-boot
     */
    Boolean isBoot = false;
    /**
     * 基础包   所有的代码都放置在这个包之下
     */
    String packageBase = "cn.lmx.kpu.authority";
    /**
     * 子包名称
     * 会在api、controller、service、serviceImpl、dao、entity等包下面创建子包
     */
    String childPackageName = "";
    /**
     * 作者
     */
    String author = "lmx";
    /**
     * 版本
     */
    String version = "1.0-SNAPSHOT";
    /**
     * 端口号
     */
    String serverPort = "8080";
    /**
     * lamp-cloud项目的包路径和pom中的groupId
     */
    String groupId = "cn.lmx.kpu";
    /**
     * lamp-util项目的包路径
     */
    String utilPackage = "cn.lmx.basic";
    String description = "服务";
    /**
     * 前端字段类型映射
     *
     * @author lmx
     * @date 2021/5/9 10:33 下午
     */
    Map<String, String> fieldTypeMapping = new HashMap<>();
    /**
     * 是否生成导入导出 API
     *
     * @author lmx
     */
    Boolean isGenerateExportApi = false;
    /**
     * 项目统一前缀  比如：  cloud-
     */
    private String projectPrefix = "kpu";
    private String apiSuffix = "-api";
    //    private String serverSuffix = "-server";
    private String entitySuffix = "-entity";
    private String serviceSuffix = "-biz";
    private String controllerSuffix = "-controller";
    /**
     * 表前缀
     */
    private String tablePrefix = "";
    /**
     * 字段前缀
     */
    private String fieldPrefix = "";
    /**
     * 需要包含的表名，允许正则表达式；用来自动生成代码
     */
    private String[] tableInclude = {"c_user"};
    /**
     * 排除那些表
     */
    private String[] tableExclude = {};
    /**
     * 包含表名
     *
     * @since 3.3.0
     */
    private LikeTable likeTable;
    /**
     * 不包含表名
     *
     * @since 3.3.0
     */
    private LikeTable notLikeTable;
    /**
     * 驱动连接的URL
     */
    private String url = "jdbc:mysql://127.0.0.1:3306/kpu_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull";
    /**
     * 驱动名称
     */
    private String driverName = "com.mysql.cj.jdbc.Driver";
    /**
     * 数据库连接用户名
     */
    private String username = "root";
    /**
     * 数据库连接密码
     */
    private String password = "root";
    /**
     * 仅仅在微服务架构下面才进行分包
     */
    private boolean enableMicroService = true;
    private Map<String, FileOverrideStrategyEnum> fileOverrideStrategy = new HashMap<>();
    /**
     * 需要制定生成路径的枚举类列表
     */
    private Set<CustomFile> filedTypes = new HashSet<>();
    private Vue vue = new Vue();
    private PackageInfoConfig packageInfo = new PackageInfoConfig();
    private ControllerConfig controllerConfig = new ControllerConfig();
    private EntityConfig entityConfig = new EntityConfig();
    private MapperConfig mapperConfig = new MapperConfig();
    private ServiceConfig serviceConfig = new ServiceConfig();
    private ManagerConfig managerConfig = new ManagerConfig();

//    /**
//     * 必填项 构造器
//     *
//     * @param serviceName     服务名
//     *                        eg： msgs
//     * @param childModuleName 子模块名
//     *                        eg: sms、emial
//     * @param author          作者
//     * @param tablePrefix     表前缀
//     * @param tableInclude    生成的表 支持通配符
//     *                        eg： msgs_.* 会生成msgs_开头的所有表
//     * @return
//     */
//    public static GeneratorConfig build(String serviceName, String childModuleName, String author, String tablePrefix, List<String> tableInclude) {
//        GeneratorConfig config = new GeneratorConfig();
//        config.setServiceName(serviceName).setAuthor(author).setTablePrefix(tablePrefix)
//                .setTableInclude(tableInclude.stream().toArray(String[]::new))
//                .setChildModuleName(childModuleName == null ? "" : childModuleName);
//        config.setPackageBase(config.getGroupId() + "." + config.getChildModuleName());
//        return config;
//    }
//
//    public static GeneratorConfig buildVue(String serviceName, String tablePrefix, List<String> tableInclude) {
//        GeneratorConfig config = new GeneratorConfig();
//        config.setServiceName(serviceName).setTablePrefix(tablePrefix)
//                .setTableInclude(tableInclude.stream().toArray(String[]::new))
//                .setChildModuleName("");
//        config.setPackageBase(config.getGroupId() + "." + config.getChildModuleName());
//        return config;
//    }

    public String getPackageBaseParent() {
        return StrUtil.subPre(this.packageBase, this.packageBase.lastIndexOf("."));
    }

    public String getChildModuleName() {
        if (StringUtils.isBlank(this.childModuleName)) {
            this.childModuleName = this.serviceName;
        }
        return this.childModuleName;
    }

    @Data
    public static class Vue {
        private String viewsPath = "views" + File.separator + "lamp";
        private VueVersion version = VueVersion.vue;
        /**
         * 表名 - <字段名 - 字段信息>
         */
//        private Map<String, Map<String, GenTableColumn>> tableFieldMap = new HashMap<>();
    }
}
