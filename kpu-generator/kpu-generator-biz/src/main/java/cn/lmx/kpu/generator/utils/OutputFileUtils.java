package cn.lmx.kpu.generator.utils;

import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.generator.config.*;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;

import java.io.File;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  17:51
 */
public class OutputFileUtils {
    public static FileOverrideStrategyEnum getFileOverride(GeneratorConfig generatorConfig, GenTable genTable, String templatePath, String enumName, TemplateEnum template) {
        Map<String, FileOverrideStrategyEnum> fileOverrideStrategy = generatorConfig.getFileOverrideStrategy();
        if (TemplateEnum.BACKEND.eq(template)) {
            switch (templatePath) {
                case GenCodeConstant.TEMPLATE_PAGE_QUERY:
                case GenCodeConstant.TEMPLATE_SAVE_VO:
                case GenCodeConstant.TEMPLATE_UPDATE_VO:
                case GenCodeConstant.TEMPLATE_RESULT_VO:
                case GenCodeConstant.TEMPLATE_ENTITY_JAVA:
                    return fileOverrideStrategy.get(GenCodeConstant.ENTITY_FILE_OVERRIDE);
                case GenCodeConstant.TEMPLATE_SQL:
                    return fileOverrideStrategy.get(GenCodeConstant.SQL_FILE_OVERRIDE);
                case GenCodeConstant.TEMPLATE_MAPPER:
                case GenCodeConstant.TEMPLATE_MAPPER_XML:
                    return fileOverrideStrategy.get(GenCodeConstant.MAPPER_FILE_OVERRIDE);
                case GenCodeConstant.TEMPLATE_MANAGER:
                case GenCodeConstant.TEMPLATE_MANAGER_IMPL:
                    return fileOverrideStrategy.get(GenCodeConstant.MANAGER_FILE_OVERRIDE);
                case GenCodeConstant.TEMPLATE_SERVICE:
                case GenCodeConstant.TEMPLATE_SERVICE_IMPL:
                    return fileOverrideStrategy.get(GenCodeConstant.SERVICE_FILE_OVERRIDE);
                case GenCodeConstant.TEMPLATE_CONTROLLER:
                    return fileOverrideStrategy.get(GenCodeConstant.CONTROLLER_FILE_OVERRIDE);
            }
        }
        return FileOverrideStrategyEnum.ADD;
    }

    public static String getOutputFile(GeneratorConfig generatorConfig, GenTable genTable, String templatePath, String enumName, TemplateEnum template) {
        if (TemplateEnum.BACKEND.eq(template)) {
            return getOutputFile(generatorConfig, genTable, templatePath, true, enumName);
        } else {
//            return getFrontOutputFile(genTable, templatePath,true);
            return null;
        }

    }

    private static String getOutputFile(GeneratorConfig generatorConfig, GenTable genTable, String templatePath, boolean isAbsolute, String enumName) {
        MapperConfig mapperConfig = generatorConfig.getMapperConfig();
        ManagerConfig managerConfig = generatorConfig.getManagerConfig();
        ServiceConfig serviceConfig = generatorConfig.getServiceConfig();
        ControllerConfig controllerConfig = generatorConfig.getControllerConfig();
        EntityConfig entityConfig = generatorConfig.getEntityConfig();
        PackageInfoConfig packageConfig = generatorConfig.getPackageInfo();

        String outputDir = isAbsolute ? genTable.getOutputDir() : StrPool.EMPTY;
        //
        String projectPrefix = generatorConfig.getProjectPrefix();
        String serviceName = genTable.getServiceName();
        String subModularSuffixName;

        //
        String service = projectPrefix + StrUtil.DASHED + serviceName;
        String mavenPath = GenCodeConstant.SRC_MAIN_JAVA;
        String moduleName = genTable.getModuleName();
        String basePackagePath = genTable.getParent() + File.separator + moduleName;
        String childPackageName = genTable.getChildPackageName() == null ? StrPool.EMPTY : genTable.getChildPackageName();
        String fileName;
        //
        String modular;
        String packagePath;
        switch (templatePath) {
            case GenCodeConstant.TEMPLATE_PAGE_QUERY:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getPageQuery(), childPackageName);
                fileName = getName(genTable.getEntityName(), entityConfig.getFormatPageQueryFileName(), GenCodeConstant.PAGE_QUERY, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_SAVE_VO:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getSaveVo(), childPackageName);
                fileName = getName(genTable.getEntityName(), entityConfig.getFormatPageQueryFileName(), GenCodeConstant.SAVE_VO, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_UPDATE_VO:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getUpdateVo(), childPackageName);
                fileName = getName(genTable.getEntityName(), entityConfig.getFormatPageQueryFileName(), GenCodeConstant.UPDATE_VO, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_RESULT_VO:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getResultVo(), childPackageName);
                fileName = getName(genTable.getName(), entityConfig.getFormatPageQueryFileName(), GenCodeConstant.RESULT_VO, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_ENTITY_JAVA:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getEntity(), childPackageName);
                fileName = genTable.getEntityName() + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_MAPPER:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getMapper(), childPackageName);
                fileName = getName(genTable.getEntityName(), mapperConfig.getFormatMapperFileName(), GenCodeConstant.MAPPER, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_MAPPER_XML:
                mavenPath = GenCodeConstant.SRC_MANI_RESOURCES;
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = "mapper_" + moduleName + File.separator + "base";
                fileName = getName(genTable.getEntityName(), mapperConfig.getFormatXmlFileName(), GenCodeConstant.MAPPER, GenCodeConstant.XML_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_MANAGER:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getManager(), childPackageName);
                fileName = getName(genTable.getEntityName(), managerConfig.getFormatManagerFileName(), GenCodeConstant.MANAGER, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_MANAGER_IMPL:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                if (StrUtil.contains(packageConfig.getManagerImpl(), StrPool.BRACE)) {
                    packagePath = buildPath(basePackagePath, StrUtil.format(packageConfig.getManagerImpl(), childPackageName));
                } else {
                    packagePath = buildPath(basePackagePath, packageConfig.getManagerImpl(), childPackageName);
                }
                fileName = getName(genTable.getEntityName(), managerConfig.getFormatManagerImplFileName(), GenCodeConstant.MANAGER_IMPL, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_SERVICE:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getService(), childPackageName);
                fileName = getName(genTable.getEntityName(), serviceConfig.getFormatServiceFileName(), GenCodeConstant.SERVICE, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_SERVICE_IMPL:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                if (StrUtil.contains(packageConfig.getServiceImpl(), StrPool.BRACE)) {
                    packagePath = buildPath(basePackagePath, StrUtil.format(packageConfig.getServiceImpl(), childPackageName));
                } else {
                    packagePath = buildPath(basePackagePath, packageConfig.getServiceImpl(), childPackageName);
                }
                fileName = getName(genTable.getEntityName(), serviceConfig.getFormatServiceImplFileName(), GenCodeConstant.SERVICE_IMPL, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_CONTROLLER:
                subModularSuffixName = GenCodeConstant.CONTROLLER_SERVICE_SUFFIX;
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getController(), childPackageName);
                fileName = getName(genTable.getEntityName(), controllerConfig.getFormatFileName(), GenCodeConstant.CONTROLLER, GenCodeConstant.JAVA_SUFFIX);
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_SQL:
                return buildPath(outputDir, service, "初始化SQL_" + genTable.getName() + GenCodeConstant.SQL_SUFFIX);
            default:
                break;
        }
        return outputDir;
    }

    private static String getName(String entityName, String formatFileName, String suffix, String javaSuffix) {
        return StrUtil.format(formatFileName, entityName, suffix, javaSuffix);
    }

    private static String getModular(String projectPrefix, String serviceName, String subModularSuffixName) {
        return projectPrefix + StrUtil.DASHED + serviceName + StrUtil.DASHED + subModularSuffixName;
    }

    private static String buildPath(String first, String... more) {
        String firstPath = StrUtil.replace(first, StrUtil.DOT, File.separator);
        String[] paths = new String[more.length];
        for (int i = 0; i < more.length; i++) {
            if (StrUtil.endWithAny(more[i], GenCodeConstant.JAVA_SUFFIX, GenCodeConstant.XML_SUFFIX, GenCodeConstant.SQL_SUFFIX)) {
                paths[i] = more[i];
            } else {
                paths[i] = StrUtil.replace(more[i], StrUtil.DOT, File.separator);
            }
            paths[i] = paths[i] == null ? StrPool.EMPTY : paths[i];
        }
        return StrUtil.join(File.separator, firstPath, StrUtil.join(File.separator, paths));
    }

//    private static String getFrontOutputFile(GenTable genTable, String templatePath, Map<String, Object> objMap, String enumName, TemplateEnum template, Map<String, FileOverrideStrategyEnum> fileOverrideConfig) {
//    }
}
