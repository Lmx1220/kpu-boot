package cn.lmx.kpu.generator.utils;

import cn.hutool.core.collection.CollUtil;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.basic.base.service.SuperService;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.utils.DateUtils;
import cn.lmx.kpu.generator.config.GeneratorConfig;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.utils.inner.PackageUtils;
import com.baidu.fsg.uid.UidGenerator;
import com.baomidou.mybatisplus.annotation.DbType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  15:53
 */
public class SourceCodeUtil {
    public static Map<String, Object> getObjMap(GeneratorConfig generatorConfig, DatabaseProperties databaseProperties
            , UidGenerator uidGenerator, Map<String, Object> subObjectMap, GenTable genTable
            , List<GenTableColumn> allFieldList, DbType dbType) {
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
        // 是否树形结构
//        map.put("isTreeEntity", EntitySuperClassEnum.TREE_ENTITY.eq(genTable.getEntitySuperClass()));

        // 设计不同类型的字段,并返回普通字段
        List<GenTableColumn> allFields = getDefGenTableColumn(databaseProperties, genTable, allFieldList, map);
        map.put("projectPrefix", generatorConfig.getProjectPrefix());
        map.put("author", genTable.getAuthor());
        map.put("parent", genTable.getParent());
        map.put("moduleName", genTable.getModuleName());

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
        map.putAll(PackageUtils.getImportPackage(dbType, genTable, generatorConfig, allFields, map));

        map.putAll(getSqlParams(genTable, uidGenerator));


        return map;
    }

    // #Todo 生成sql的参数 未完成
    private static Map<String, Object> getSqlParams(GenTable genTable, UidGenerator uidGenerator) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("uid", uidGenerator.getUid());
        return objectMap;
    }

    private static Map<String, Object> serviceMap(GenTable genTable, GeneratorConfig generatorConfig) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("superServiceClass", genTable.getSuperClass().getService());
        objectMap.put("superServiceImplClass", genTable.getSuperClass().getServiceImpl());
        switch (genTable.getSuperClass()) {
            case SUPER_CLASS:
                objectMap.put("superServiceClassPackage", SuperService.class.getPackage());
                objectMap.put("superServiceImplClassPackage", SuperServiceImpl.class.getPackage());
                break;
            case SUPER_CACHE_CLASS:
                objectMap.put("superServiceClassPackage", SuperCacheService.class.getPackage());
                objectMap.put("superServiceImplClassPackage", SuperCacheServiceImpl.class.getPackage());
                break;
        }

        return objectMap;
    }

    private static Map<String, ?> controllerMap(GenTable genTable, GeneratorConfig generatorConfig) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("superControllerClass", genTable.getSuperClass().getController());

        switch (genTable.getSuperClass()) {
            case SUPER_CLASS:
                objectMap.put("superControllerClassPackage", SuperController.class.getPackage());
                break;
            case SUPER_CACHE_CLASS:
                objectMap.put("superControllerClassPackage", SuperCacheController.class.getPackage());
                Set<String> controllerImport = new HashSet<>();
                objectMap.put("controllerImport", controllerImport);
                break;
        }

        return objectMap;
    }

    //#Todo getDefGenTableColumn 未完成
    private static List<GenTableColumn> getDefGenTableColumn(DatabaseProperties databaseProperties, GenTable genTable, List<GenTableColumn> allFieldList, Map<String, Object> map) {
        List<GenTableColumn> allFields = new ArrayList<>();

        return allFields;

    }
}
