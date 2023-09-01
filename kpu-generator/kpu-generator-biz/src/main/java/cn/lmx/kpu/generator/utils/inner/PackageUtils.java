package cn.lmx.kpu.generator.utils.inner;

import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.base.entity.TreeEntity;
import cn.lmx.kpu.generator.config.GeneratorConfig;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import com.baomidou.mybatisplus.annotation.DbType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  16:30
 */
public class PackageUtils {
    public static Map<String, Object> getPackage(GenTable genTable, GeneratorConfig generatorConfig) {
        return null;
    }

    public static Map<String, Object> getSuperClassPackage(GenTable genTable) {
        HashMap<String, Object> objMap = new HashMap<>();
        genTable.getEntitySuperClass().getClazzName();
        objMap.put("superEntityClass", genTable.getEntitySuperClass().getClazzName());
        switch (genTable.getEntitySuperClass()) {
            case ENTITY:
                objMap.put("superEntityPackage", Entity.class.getPackage());
                break;
            case TREE_ENTITY:
                objMap.put("superEntityPackage", TreeEntity.class.getPackage());
                break;
            case SUPER_ENTITY:
                objMap.put("superEntityPackage", SuperEntity.class.getPackage());
                break;
        }
        return objMap;
    }

    public static Map<String, ?> getMvcPackage(GenTable genTable, GeneratorConfig generatorConfig, Map<String, Object> packageMap) {
        HashMap<String, Object> objMap = new HashMap<>();

        return objMap;
    }

    public static Map<String, ?> getImportPackage(DbType dbType, GenTable genTable, GeneratorConfig generatorConfig, List<GenTableColumn> allFields, Map<String, Object> map) {
        HashMap<String, Object> objMap = new HashMap<>();
        return objMap;
    }
}
