package cn.lmx.kpu.generator.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.DateUtils;
import cn.lmx.basic.utils.StrHelper;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.common.constant.DefValConstants;
import cn.lmx.kpu.common.constant.DsConstant;
import cn.lmx.kpu.generator.config.ControllerConfig;
import cn.lmx.kpu.generator.config.GeneratorConfig;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.enumeration.*;
import cn.lmx.kpu.generator.rules.echo.EchoDict;
import cn.lmx.kpu.generator.rules.echo.EchoType;
import cn.lmx.kpu.generator.utils.inner.CommentUtils;
import cn.lmx.kpu.generator.utils.inner.PackageUtils;
import com.baidu.fsg.uid.UidGenerator;
import com.baomidou.mybatisplus.annotation.DbType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 源码生成 工具类
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
public class SourceCodeUtils {

    /**
     * 组装模板参数
     *
     * @param generatorConfig    默认配置
     * @param databaseProperties 数据元配置
     * @param genTable           表信息
     * @param allFieldList       所有字段
     * @param uidGenerator       uidGenerator
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27] [lmx] [初始创建]
     */
    public static Map<String, Object> getObjectMap(GeneratorConfig generatorConfig, DatabaseProperties databaseProperties, UidGenerator uidGenerator,
                                                   Map<String, Object> subObjectMap, GenTable genTable, List<GenTableColumn> allFieldList,
                                                   DbType dbType) {
        Map<String, Object> map = new HashMap<>();
        if (CollUtil.isNotEmpty(subObjectMap)) {
            map.put("sub", subObjectMap);
        } else {
            map.put("sub", Collections.emptyMap());
        }
        map.put("config", generatorConfig);
        map.put("controllerConfig", generatorConfig.getControllerConfig());
        map.put("entityConfig", generatorConfig.getEntityConfig());
        map.put("serviceConfig", generatorConfig.getServiceConfig());
        map.put("managerConfig", generatorConfig.getManagerConfig());
        map.put("mapperConfig", generatorConfig.getMapperConfig());
        map.put("table", genTable);
        map.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)));
        map.put("dbType", dbType.getDb());
        map.put("oracle", DbType.ORACLE.getDb());
        // 是否树型结构
        map.put("isTreeEntity", EntitySuperClassEnum.TREE_ENTITY.eq(genTable.getEntitySuperClass()));

        // 设置不同类型的字段，并返回普通字段
        List<GenTableColumn> allFields = getGenTableColumns(databaseProperties, genTable, allFieldList, map);

        map.put("projectPrefix", generatorConfig.getProjectPrefix());
        map.put("author", genTable.getAuthor());
        map.put("parent", genTable.getParent());
        map.put("utilParent", genTable.getParent());
        map.put("moduleName", genTable.getModuleName());
        map.put("plusName",StrHelper.convertUriToCamelCase(genTable.getPlusModuleName()));
        // 导包信息
        Map<String, Object> packageMap = PackageUtils.getPackage(genTable, generatorConfig);
        map.put("package", packageMap);

        // controller 常用参数
        map.putAll(controllerMap(genTable, generatorConfig));
        // service 常用参数
        map.putAll(serviceMap(genTable, generatorConfig));
        // 父类
        map.putAll(PackageUtils.getSuperClassPackage(genTable));
        // 业务类 名称和完整包路径
        map.putAll(PackageUtils.getMvcPackage(genTable, generatorConfig, packageMap));
        // 业务类 需要导入的包
        map.putAll(PackageUtils.getImportPackages(dbType, genTable, generatorConfig, allFields, map));
        // 常量
        map.putAll(getConstant());
        map.putAll(getSqlParams(genTable, uidGenerator));

        Set<EchoDict> dictList = new HashSet<>();
        allFields.forEach(field -> {
            EchoDict echoDict = initEchoDictSql(field);
            if (echoDict != null) {
                dictList.add(echoDict);
            }
        });
        map.put("dictList", dictList);

        return map;
    }

    /**
     * 解析注释中的字典列表
     * [x-y a-b]
     *
     * @param field field
     * @return cn.lmx.kpu.generator.rules.echo.EchoDict
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27] [lmx] [初始创建]
     */
    private static EchoDict initEchoDictSql(GenTableColumn field) {
        String echoStr = StrUtil.isNotEmpty(field.getEchoStr()) ? field.getEchoStr() : field.getComment();
        if (StrUtil.isNotEmpty(echoStr)) {
            EchoType echoType = CommentUtils.getEchoType(echoStr);
            if (echoType != null) {

                String fieldComment = StrUtil.isNotEmpty(field.getSwaggerComment()) ? field.getSwaggerComment() : field.getName();
                return CommentUtils.getEchoDict(echoType.getDictTypeField(), fieldComment, field.getComment());
            }
        }
        return null;
    }

    private static List<GenTableColumn> getGenTableColumns(DatabaseProperties databaseProperties, GenTable genTable,
                                                           List<GenTableColumn> allFieldList, Map<String, Object> map) {
        List<GenTableColumn> pkFieldList = allFieldList.stream().filter(GenTableColumn::getIsPk).collect(Collectors.toList());
        ArgumentAssert.notEmpty(pkFieldList, "请设置主键id");
        ArgumentAssert.isFalse(pkFieldList.size() > 1, "目前只支持1个主键id, 不支持符合组件");
        map.put("pkField", pkFieldList.get(0));
        List<GenTableColumn> commonFields = new ArrayList<>();
        List<GenTableColumn> fields = new ArrayList<>();
        allFieldList.forEach(field -> {
            if (genTable.getEntitySuperClass() != null && genTable.getEntitySuperClass().matchSuperEntityColumns(field.getName())) {
                commonFields.add(field);
            } else {
                fields.add(field);
            }
        });

        // 所有字段
        map.put("allFields", allFieldList);
        // 父类含有的字段
        map.put("commonFields", commonFields);
        // 普通字段
        map.put("fields", fields);
        // 租户列
        map.put("tenantIdColumn", databaseProperties.getTenantIdColumn());
        return allFieldList;
    }

    private static Map<String, Object> controllerMap(GenTable genTable, GeneratorConfig generatorConfig) {
        ControllerConfig controllerConfig = generatorConfig.getControllerConfig();
        Map<String, Object> map = new HashMap<>();
        if (controllerConfig.getHyphenStyle()) {
            map.put("mappingHyphen", StrHelper.convertToCamelCase(StrUtil.lowerFirst(genTable.getEntityName())));
        }
        return map;
    }

    private static Map<String, Object> serviceMap(GenTable genTable, GeneratorConfig generatorConfig) {
        Map<String, Object> map = new HashMap<>();
        // serviceImpl
        if (genTable.getIsDs()) {
            String dsVal = genTable.getDsValue();
            if (StrUtil.isEmpty(dsVal)) {
                dsVal = DsConstant.class.getSimpleName() + ".BASE_TENANT";
            }
            if (!dsVal.contains(DsConstant.class.getSimpleName())) {
                if (!dsVal.startsWith(StrPool.QUOTE)) {
                    dsVal = StrPool.QUOTE + dsVal;
                }
                if (!dsVal.endsWith(StrPool.QUOTE)) {
                    dsVal = dsVal + StrPool.QUOTE;
                }
            }
            map.put("dsVal", dsVal);
        }
        return map;
    }

    private static Map<String, Object> getConstant() {
        Map<String, Object> map = new HashMap<>();
        map.put("TPL_TREE", TplEnum.TREE);
        map.put("TPL_MAIN_SUB", TplEnum.MAIN_SUB);
        map.put("POPUP_TYPE_MODAL", PopupTypeEnum.MODAL);
        map.put("POPUP_TYPE_DRAWER", PopupTypeEnum.DRAWER);
        map.put("POPUP_TYPE_JUMP", PopupTypeEnum.JUMP);
        map.put("SUPER_CLASS_SUPER_POI_CLASS", SuperClassEnum.SUPER_POI_CLASS);
        map.put("COMPONENT_ENUM", ComponentEnum.values());
        return map;
    }

    public static Map<String, Object> getSqlParams(GenTable genTable, UidGenerator uidGenerator) {
        Map<String, Object> map = new HashMap<>();
        map.put("applicationId", genTable.getMenuApplicationId());
        map.put("parentMenuId", genTable.getMenuParentId());
        map.put("createdBy", ContextUtil.getUserId());
        map.put("menuId", uidGenerator.getUid());
        if (genTable.getPopupType().eq(PopupTypeEnum.JUMP)) {
            map.put("menuListId", uidGenerator.getUid());
            map.put("menuEditId", uidGenerator.getUid());
        }
        map.put("menuId", uidGenerator.getUid());
        map.put("buttonAddId", uidGenerator.getUid());
        map.put("buttonEditId", uidGenerator.getUid());
        map.put("buttonCopyId", uidGenerator.getUid());
        map.put("buttonDeleteId", uidGenerator.getUid());
        map.put("buttonViewId", uidGenerator.getUid());

        map.put("roleId", DefValConstants.DEF_ROLE_ID);
        map.put("rrMenuId", uidGenerator.getUid());
        map.put("rrMenuListId", uidGenerator.getUid());
        map.put("rrMenuEditId", uidGenerator.getUid());
        map.put("rrButtonAddId", uidGenerator.getUid());
        map.put("rrButtonEditId", uidGenerator.getUid());
        map.put("rrButtonCopyId", uidGenerator.getUid());
        map.put("rrButtonDeleteId", uidGenerator.getUid());
        map.put("rrButtonViewId", uidGenerator.getUid());
        map.put("uidGenerator", uidGenerator);
        return map;
    }

}
