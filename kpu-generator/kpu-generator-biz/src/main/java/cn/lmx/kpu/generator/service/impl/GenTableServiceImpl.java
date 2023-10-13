package cn.lmx.kpu.generator.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.lmx.basic.base.request.DownloadVO;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.constant.Constants;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.exception.ArgumentException;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.DbPlusUtil;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.generator.config.FileOverrideStrategy;
import cn.lmx.kpu.generator.config.GeneratorConfig;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import cn.lmx.kpu.generator.enumeration.TplEnum;
import cn.lmx.kpu.generator.manager.GenTableColumnManager;
import cn.lmx.kpu.generator.manager.GenTableManager;
import cn.lmx.kpu.generator.rules.enumeration.EnumType;
import cn.lmx.kpu.generator.service.GenTableService;
import cn.lmx.kpu.generator.utils.*;
import cn.lmx.kpu.generator.vo.query.GenTablePageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableImportVO;
import cn.lmx.kpu.generator.vo.save.GenTableSaveVO;
import cn.lmx.kpu.generator.vo.save.GenVO;
import cn.lmx.kpu.generator.vo.update.GenTableUpdateVO;
import cn.lmx.kpu.model.constant.EchoApi;
import cn.lmx.kpu.model.constant.EchoDictType;
import cn.lmx.kpu.model.constant.EchoRef;
import com.baidu.fsg.uid.UidGenerator;
import com.baomidou.mybatisplus.annotation.DbType;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static cn.lmx.kpu.generator.utils.GenCodeConstant.*;

/**
 * <p>
 * 业务实现类
 * 代码生成
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@EnableConfigurationProperties(GeneratorConfig.class)
public class GenTableServiceImpl extends SuperServiceImpl<GenTableManager, Long, GenTable, GenTableSaveVO, GenTableUpdateVO, GenTablePageQuery, GenTableResultVO> implements GenTableService {
    private static final Map<String, String> TEMPLATE_FIELD_MAP = MapUtil.newHashMap();

    static {
        TEMPLATE_FIELD_MAP.put("enum", TEMPLATE_ENUM);
        TEMPLATE_FIELD_MAP.put("saveVo", TEMPLATE_SAVE_VO);
        TEMPLATE_FIELD_MAP.put("updateVo", TEMPLATE_UPDATE_VO);
        TEMPLATE_FIELD_MAP.put("resultVo", TEMPLATE_RESULT_VO);
        TEMPLATE_FIELD_MAP.put("pageQuery", TEMPLATE_PAGE_QUERY);
        TEMPLATE_FIELD_MAP.put("sql", TEMPLATE_SQL);
        TEMPLATE_FIELD_MAP.put("entity", TEMPLATE_ENTITY_JAVA);
        TEMPLATE_FIELD_MAP.put("xml", TEMPLATE_XML);
        TEMPLATE_FIELD_MAP.put("mapper", TEMPLATE_MAPPER);
        TEMPLATE_FIELD_MAP.put("manager", TEMPLATE_MANAGER);
        TEMPLATE_FIELD_MAP.put("managerImpl", TEMPLATE_MANAGER_IMPL);
        TEMPLATE_FIELD_MAP.put("service", TEMPLATE_SERVICE);
        TEMPLATE_FIELD_MAP.put("serviceImpl", TEMPLATE_SERVICE_IMPL);
        TEMPLATE_FIELD_MAP.put("controller", TEMPLATE_CONTROLLER);

        TEMPLATE_FIELD_MAP.put("api", TEMPLATE_WEB_PRO_SIMPLE_API);
        TEMPLATE_FIELD_MAP.put("model", TEMPLATE_WEB_PRO_SIMPLE_MODEL);
        TEMPLATE_FIELD_MAP.put("langEn", TEMPLATE_WEB_PRO_SIMPLE_LANG_EN);
        TEMPLATE_FIELD_MAP.put("langZh", TEMPLATE_WEB_PRO_SIMPLE_LANG_ZH);
        TEMPLATE_FIELD_MAP.put("data", TEMPLATE_WEB_PRO_SIMPLE_DATA);
        TEMPLATE_FIELD_MAP.put("index", TEMPLATE_WEB_PRO_SIMPLE_INDEX);
        TEMPLATE_FIELD_MAP.put("edit", TEMPLATE_WEB_PRO_SIMPLE_EDIT);
        TEMPLATE_FIELD_MAP.put("jumpEdit", TEMPLATE_WEB_PRO_SIMPLE_JUMP_EDIT);
        TEMPLATE_FIELD_MAP.put("treeIndex", TEMPLATE_WEB_PRO_TREE_INDEX);
        TEMPLATE_FIELD_MAP.put("treeEdit", TEMPLATE_WEB_PRO_TREE_EDIT);
        TEMPLATE_FIELD_MAP.put("treeTree", TEMPLATE_WEB_PRO_TREE_TREE);
    }

    private final GenTableColumnManager genTableColumnManager;
    private final GeneratorConfig generatorConfig;
    private final DatabaseProperties databaseProperties;
    private final UidGenerator uidGenerator;
    @Value("${spring.profiles.active}")
    private String active;
    @Value("${info.version}")
    private String version;

    @Override
    public List<GenTable> selectTableList(Long dsId) {

        DataSource ds = superManager.getDs(dsId);
        List<Table> tables = DbPlusUtil.getTables(ds);

        List<GenTable> list = new ArrayList<>();
        tables.forEach(table -> {
            GenTable genTable = new GenTable();
            genTable.setName(table.getTableName());
            genTable.setComment(table.getComment());
            list.add(genTable);
        });

        return list;
    }


    @Override
    public Boolean importCheck(List<String> tableNames) {
        ArgumentAssert.notEmpty(tableNames, "请先勾选需要导入的表");
        List<GenTable> list = superManager.list(Wraps.<GenTable>lbQ().in(GenTable::getName, tableNames));
        if (CollUtil.isEmpty(list)) {
            return true;
        }
        String tableName = list.stream().map(GenTable::getName).collect(Collectors.joining(StrPool.COMMA));
        throw BizException.wrap("表：{} 已经存在，是否覆盖导入？", tableName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncField(Long id) {
        GenTable genTable = superManager.getById(id);
        ArgumentAssert.notNull(genTable, "请先选择需要导入的表");
        DataSource ds = superManager.getDs(genTable.getDsId());

        Table tableMeta = MetaUtil.getTableMeta(ds, genTable.getName());

        if (CollUtil.isEmpty(tableMeta.getColumns())) {
            throw BizException.wrap("请确保该表的数据源配置正确或该表中至少存在1个字段");
        }

        // 原来的字段
        List<GenTableColumn> fieldList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, id));
        // 原来的字段 map
        Map<String, GenTableColumn> tableFieldMap = fieldList.stream().collect(Collectors.toMap(GenTableColumn::getName, Function.identity()));

        List<GenTableColumn> insertList = new ArrayList<>();
//        List<GenTableColumn> updateList = new ArrayList<>();
        List<String> columnNameList = new ArrayList<>();

        // 新的字段
        for (Column column : tableMeta.getColumns()) {
            GenTableColumn tableColumn = GenUtils.initColumnField(generatorConfig, superManager.getDbType(), genTable, column);
            if (!tableFieldMap.containsKey(column.getName())) {
                insertList.add(tableColumn);
            }
            columnNameList.add(column.getName());
        }
        genTableColumnManager.saveBatch(insertList);

        List<GenTableColumn> delColumns = fieldList.stream().filter(field -> !columnNameList.contains(field.getName())).collect(Collectors.toList());
        genTableColumnManager.removeByIds(delColumns.stream().map(GenTableColumn::getId).collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importTable(GenTableImportVO importVO) {
        DataSource ds = superManager.getDs(importVO.getDsId());
        List<String> tableNames = importVO.getTableNames();
        ArgumentAssert.notEmpty(tableNames, "请先选择需要导入的表");

        DbType dbType = superManager.getDbType();
        tableNames.forEach(tn -> {
            Table tableMeta = MetaUtil.getTableMeta(ds, tn);
            GenTable genTable = GenUtils.initTable(generatorConfig, tableMeta);
            genTable.setDsId(importVO.getDsId());
            boolean flag = superManager.save(genTable);
            if (flag) {
                List<GenTableColumn> columnList = new ArrayList<>();
                int index = 0;
                for (Column column : tableMeta.getColumns()) {
                    GenTableColumn tableColumn = GenUtils.initColumnField(generatorConfig, dbType, genTable, column);
                    if (tableColumn != null) {
                        tableColumn.setSortValue(index++);
                        columnList.add(tableColumn);
                    }
                }
                genTableColumnManager.saveBatch(columnList);
            }
        });
        return true;
    }

    /**
     * 必填项检测
     *
     * @param id 表id
     * @return
     */
    public GenTable previewCheck(Long id) {
        GenTable genTable = superManager.getById(id);
        ArgumentAssert.notNull(genTable, "表不存在，请先导入表");
        ArgumentAssert.notEmpty(genTable.getServiceName(), "请先配置并保存【{}】表，生成信息页面的 [服务名]", genTable.getName());
        ArgumentAssert.notEmpty(genTable.getModuleName(), "请先配置并保存【{}】表，生成信息页面的  [模块名]", genTable.getName());
        ArgumentAssert.notEmpty(genTable.getPlusApplicationName(), "请先配置并保存【{}】表，生成信息页面的  [前端应用名]", genTable.getName());
        ArgumentAssert.notEmpty(genTable.getPlusModuleName(), "请先配置并保存【{}】表，生成信息页面的  [前端模块名]", genTable.getName());
//        ArgumentAssert.notNull(genTable.getMenuApplicationId(), "请先配置并保存【{}】表，生成信息页面的  [菜单所属应用]", genTable.getName());
        ArgumentAssert.notNull(genTable.getMenuParentId(), "请先配置并保存【{}】表，生成信息页面的  [上级菜单]", genTable.getName());
        if (TplEnum.MAIN_SUB.eq(genTable.getTplType())) {
            ArgumentAssert.notNull(genTable.getSubId(), "请先配置并保存【{}】表，生成信息页面的  [从表]", genTable.getName());
            ArgumentAssert.notNull(genTable.getSubJavaFieldName(), "请先配置并保存【{}】表，生成信息页面的  [从表字段]", genTable.getName());
        }
        return genTable;
    }


    @Override
    public Map<String, String> previewCode(Long id, TemplateEnum template) {
        GenTable genTable = previewCheck(id);
        Map<String, String> subFileCodeMap = null;
        // 从表
        Map<String, Object> subObjectMap = new HashMap<>();
        if (TplEnum.MAIN_SUB.eq(genTable.getTplType())) {
            GenTable subTable = previewCheck(genTable.getSubId());

            List<GenTableColumn> subFieldList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, genTable.getSubId()));
            subObjectMap = SourceCodeUtils.getObjectMap(generatorConfig, databaseProperties, uidGenerator, null, subTable, subFieldList, superManager.getDbType());
            List<String> templatePathList = TemplateUtils.getSubTemplateList(template);
            subFileCodeMap = preview(subObjectMap, templatePathList, template);
        }

//        主表
        List<GenTableColumn> fieldList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, id));
        Map<String, Object> objectMap = SourceCodeUtils.getObjectMap(generatorConfig, databaseProperties, uidGenerator, subObjectMap, genTable, fieldList, superManager.getDbType());
        List<String> templatePathList = TemplateUtils.getTemplateList(template, genTable.getTplType(), genTable.getPopupType());
        Map<String, String> fileCodeMap = preview(objectMap, templatePathList, template);

        if (CollUtil.isNotEmpty(subFileCodeMap)) {
            subFileCodeMap.forEach((key, value) -> fileCodeMap.put("sub-" + key, value));
        }
        return fileCodeMap;
    }

    private Map<String, String> preview(Map<String, Object> objectMap, List<String> templatePathList, TemplateEnum template) {
        Map<String, String> fileCodeMap = new LinkedHashMap<>();

        for (String templatePath : templatePathList) {
            try {
                StringWriter sw = new StringWriter();
                Template tpl = TemplateUtils.getTemplate(templatePath);
                tpl.process(objectMap, sw);
                fileCodeMap.put(templatePath, sw.toString());
            } catch (TemplateException | IOException e) {
                log.info("代码生成异常, 出错原因可能是的表结构没有按照规范编写！", e);
                fileCodeMap.put(templatePath, e.getMessage());
            }
        }

        if (TemplateEnum.BACKEND.eq(template)) {
            // 预览枚举类
            Set<EnumType> etList = (Set<EnumType>) objectMap.get(GenCodeConstant.ET_LIST);
            if (CollUtil.isNotEmpty(etList)) {
                etList.forEach(et -> {
                    Map<String, Class<?>> constantsPackage = generatorConfig.getConstantsPackage();
                    if (constantsPackage.containsKey(et.getEnumName())) {
                        return;
                    }

                    String templatePath = GenCodeConstant.TEMPLATE_ENUM;
                    // 每个枚举的枚举值
                    objectMap.put("enumType", et);
                    try {
                        StringWriter sw = new StringWriter();
                        Template tpl = TemplateUtils.getTemplate(templatePath);
                        tpl.process(objectMap, sw);
                        fileCodeMap.put("/templates/java/" + et.getEnumName() + GenCodeConstant.JAVA_SUFFIX + GenCodeConstant.FTL_SUFFIX, sw.toString());
                    } catch (TemplateException | IOException e) {
                        log.info("枚举代码生成异常, 出错原因可能是的表结构中的注释没有按照规范编写！", e);
                        fileCodeMap.put(templatePath, e.getMessage());
                    }
                });
            }
            // 常量
            Set<String> apiList = (Set<String>) objectMap.get(GenCodeConstant.ECHO_API_LIST);
            Set<String> refList = (Set<String>) objectMap.get(GenCodeConstant.ECHO_REF_LIST);
            Map<String, Set<String>> dictMap = (Map<String, Set<String>>) objectMap.get(GenCodeConstant.ECHO_DICT_TYPE_MAP);

            if (CollUtil.isNotEmpty(refList)) {
                put(fileCodeMap, EchoRef.class, getMap(EchoRef.class, refList));
            }
            if (CollUtil.isNotEmpty(apiList)) {
                put(fileCodeMap, EchoApi.class, getMap(EchoApi.class, apiList));
            }

            if (CollUtil.isNotEmpty(dictMap)) {
                Map<String, String> dictRefMap = new HashMap<>();
                dictMap.forEach((position, list) -> {
                    String api = StrUtil.join(FileInsertUtil.getSeparator() + FileInsertUtil.repeatTab(2), list);
                    dictRefMap.put(position, api);
                });
//                put(fileCodeMap, EchoDictType.class, dictRefMap);
            }
        }
        return fileCodeMap;
    }

    private void put(Map<String, String> fileCodeMap, Class<?> clazz, Map<String, String> map) {
        if (CollUtil.isEmpty(map)) {
            return;
        }
        String zipOutputFile = getPath(StrPool.EMPTY, clazz.getName());
        String content = FileInsertUtil.of(zipOutputFile, map).replaceAll();
        fileCodeMap.put("/templates/java/" + clazz.getSimpleName() + GenCodeConstant.JAVA_SUFFIX + GenCodeConstant.FTL_SUFFIX, content);
    }


    @Override
    public DownloadVO downloadZip(List<Long> ids, TemplateEnum template) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        StringBuilder name = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            GenTable genTable = downloadSimple(ids.get(i), template, zip);
            name.append(genTable.getName());
            if (i != ids.size() - 1) {
                name.append("|");
            }
        }
        String zipName = Constants.PROJECT_PREFIX + "_" + template.getDesc() + "代码(" + name + ").zip";

        IoUtil.close(zip);
        return DownloadVO.builder()
                .data(outputStream.toByteArray()).fileName(zipName).build();
    }

    private GenTable downloadSimple(Long id, TemplateEnum template, ZipOutputStream zip) {
        GenTable genTable = previewCheck(id);

        Map<String, Object> subObjectMap = writeSubZip(template, zip, genTable);

        GenTable subTable = (GenTable) subObjectMap.get("table");
        List<GenTableColumn> fieldList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, id));
        Map<String, Object> objectMap = SourceCodeUtils.getObjectMap(generatorConfig, databaseProperties, uidGenerator, subObjectMap, genTable, fieldList, superManager.getDbType());
        List<String> templatePathList = TemplateUtils.getTemplateList(template, genTable.getTplType(), genTable.getPopupType());
        zipByTemplate(genTable, subTable, templatePathList, objectMap, template, zip);

        return genTable;
    }

    private Map<String, Object> writeSubZip(TemplateEnum template, ZipOutputStream zip, GenTable genTable) {
        if (TplEnum.MAIN_SUB.eq(genTable.getTplType())) {
            GenTable subTable = previewCheck(genTable.getSubId());
            List<GenTableColumn> subFieldList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, genTable.getSubId()));
            Map<String, Object> subObjectMap = SourceCodeUtils.getObjectMap(generatorConfig, databaseProperties, uidGenerator, null, subTable, subFieldList, superManager.getDbType());
            List<String> templatePathList = TemplateUtils.getSubTemplateList(template);
            zipByTemplate(subTable, null, templatePathList, subObjectMap, template, zip);
            return subObjectMap;
        }
        return Collections.emptyMap();
    }

    private void zipByTemplate(GenTable genTable, GenTable subTable, List<String> templatePathList, Map<String, Object> objectMap, TemplateEnum template, ZipOutputStream zip) {
        for (String templatePath : templatePathList) {
            writeZip(templatePath, objectMap, genTable, subTable, zip, null, template);
        }

        if (TemplateEnum.BACKEND.eq(template)) {
            // 枚举类
            Set<EnumType> etList = (Set<EnumType>) objectMap.get(GenCodeConstant.ET_LIST);
            if (CollUtil.isNotEmpty(etList)) {
                for (EnumType et : etList) {
                    objectMap.put("enumType", et);
                    writeZip(GenCodeConstant.TEMPLATE_ENUM, objectMap, genTable, subTable, zip, et.getEnumName(), template);
                }
            }
            // 常量
            Set<String> apiList = (Set<String>) objectMap.get(GenCodeConstant.ECHO_API_LIST);
            Set<String> refList = (Set<String>) objectMap.get(GenCodeConstant.ECHO_REF_LIST);
            Map<String, Set<String>> dictMap = (Map<String, Set<String>>) objectMap.get(GenCodeConstant.ECHO_DICT_TYPE_MAP);

            if (CollUtil.isNotEmpty(refList)) {
                Class<?> echoRefClass = EchoRef.class;
                writeZip(echoRefClass, getMap(echoRefClass, refList), zip);
            }

            if (CollUtil.isNotEmpty(apiList)) {
                Class<?> echoApiClass = EchoApi.class;
                writeZip(echoApiClass, getMap(echoApiClass, apiList), zip);
            }

            if (CollUtil.isNotEmpty(dictMap)) {
                Class<?> echoDictTypeClass = EchoDictType.class;
                Map<String, String> dictTypeMap = new HashMap<>();
                dictMap.forEach((position, list) -> {
                    String api = StrUtil.join(FileInsertUtil.getSeparator() + FileInsertUtil.repeatTab(2), list);
                    dictTypeMap.put(position, api);
                });
                writeZip(echoDictTypeClass, dictTypeMap, zip);
            }

        }
    }

    private Map<String, String> getMap(Class<?> clazz, Set<String> list) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return MapUtil.of(clazz.getSimpleName(), StrUtil.join(FileInsertUtil.getSeparator() + FileInsertUtil.repeatTab(), list));
    }

    private void writeZip(Class<?> clazz, Map<String, String> map, ZipOutputStream zip) {
        try {
            if (CollUtil.isEmpty(map)) {
                return;
            }

            String filePath = getPath(StrPool.EMPTY, clazz.getName());
            String zipOutputFile = getLocalPath(StrPool.EMPTY, clazz.getName());
            log.info("filePath={}, zipOutputFile={}", filePath, zipOutputFile);
            String content = FileInsertUtil.of(filePath, map).replaceAll();
            zip.putNextEntry(new ZipEntry(zipOutputFile));
            IOUtils.write(content, zip, StrPool.UTF8);
        } catch (IOException e) {
            log.info("代码生成异常, 出错原因可能是的表结构没有按照规范编写，导致模板解析出错！", e);
        } finally {
            try {
                zip.flush();
                zip.closeEntry();
            } catch (IOException ee) {
                log.error("ee=", ee);
            }
        }
    }

    private void writeZip(String templatePath, Map<String, Object> objectMap, GenTable genTable, GenTable subTable, ZipOutputStream zip, String enumName, TemplateEnum template) {
        try (StringWriter sw = new StringWriter()) {
            Template tpl = TemplateUtils.getTemplate(templatePath);
            tpl.process(objectMap, sw);
            String zipOutputFile = OutputFileUtils.getZipOutputFile(generatorConfig, genTable, subTable, templatePath, enumName, template);
            log.info("zipOutputFile={}", zipOutputFile);
            zip.putNextEntry(new ZipEntry(zipOutputFile));
            IOUtils.write(sw.toString(), zip, StrPool.UTF8);
        } catch (TemplateException | IOException e) {
            log.info("代码生成异常, 出错原因可能是的表结构没有按照规范编写，导致模板解析出错！", e);
        } finally {
            try {
                zip.flush();
                zip.closeEntry();
            } catch (IOException ee) {
                log.error("ee=", ee);
            }
        }
    }

    @Override
    public void generatorCode(GenVO genVO) {
        List<Long> ids = genVO.getIds();
        TemplateEnum template = genVO.getTemplate();
        if (!StrPool.DEV.equals(active)) {
            throw BizException.wrap("只有本地环境（spring.profiles.active=dev）能直接生成代码到指定路径，线上部署请将【生成方式】改为【打包下载】");
        }

        for (Long id : ids) {
            GenTable genTable = previewCheck(id);
            if (TemplateEnum.BACKEND.eq(template)) {
                ArgumentAssert.notEmpty(genTable.getOutputDir(), "请先配置代码生成的绝对路径");
            } else {
                ArgumentAssert.notEmpty(genTable.getFrontOutputDir(), "请先配置代码生成的绝对路径");
            }

            // 生成从表
            Map<String, Object> subObjectMap = generatorSubCode(genVO, template, genTable);

            // 生成主表
            GenTable subTable = (GenTable) subObjectMap.get("table");
            List<GenTableColumn> fieldList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, id));
            Map<String, Object> objectMap = SourceCodeUtils.getObjectMap(generatorConfig, databaseProperties, uidGenerator, subObjectMap, genTable, fieldList, superManager.getDbType());
            List<String> templatePathList = TemplateUtils.getTemplateList(template, genTable.getTplType(), genTable.getPopupType());
            generatorByTemplate(genTable, subTable, templatePathList, objectMap, genVO);
        }
    }

    private Map<String, Object> generatorSubCode(GenVO genVO, TemplateEnum template, GenTable genTable) {
        if (TplEnum.MAIN_SUB.eq(genTable.getTplType())) {
            GenTable subTable = previewCheck(genTable.getSubId());
            if (TemplateEnum.BACKEND.eq(template)) {
                ArgumentAssert.notEmpty(subTable.getOutputDir(), "请先配置代码生成的绝对路径");
            }

            List<GenTableColumn> subFieldList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, genTable.getSubId()));
            Map<String, Object> subObjectMap = SourceCodeUtils.getObjectMap(generatorConfig, databaseProperties, uidGenerator, null, subTable, subFieldList, superManager.getDbType());
            List<String> templatePathList = TemplateUtils.getSubTemplateList(template);
            generatorByTemplate(subTable, null, templatePathList, subObjectMap, genVO);
            return subObjectMap;
        }
        return Collections.emptyMap();
    }

    private String getPath(String outputDir, String clazzName) {
        return StrPool.SLASH + StrUtil.replace(clazzName, StrPool.DOT, StrPool.SLASH) + GenCodeConstant.JAVA_SUFFIX;
    }

    private void generatorByTemplate(GenTable genTable, GenTable subTable, List<String> templatePathList, Map<String, Object> objectMap, GenVO genVO) {
        TemplateEnum template = genVO.getTemplate();
        for (String templatePath : templatePathList) {
            writeFile(genTable, subTable, templatePath, objectMap, null, genVO);
        }
        if (TemplateEnum.BACKEND.eq(template)) {
            // 枚举类
            Set<EnumType> etList = (Set<EnumType>) objectMap.get(GenCodeConstant.ET_LIST);
            if (CollUtil.isNotEmpty(etList)) {
                for (EnumType et : etList) {
                    objectMap.put("enumType", et);
                    writeFile(genTable, subTable, GenCodeConstant.TEMPLATE_ENUM, objectMap, et.getEnumName(), genVO);
                }
            }

            // 常量
            Set<String> apiList = (Set<String>) objectMap.get(GenCodeConstant.ECHO_API_LIST);
            Set<String> refList = (Set<String>) objectMap.get(GenCodeConstant.ECHO_REF_LIST);
            Map<String, Set<String>> dictMap = (Map<String, Set<String>>) objectMap.get(GenCodeConstant.ECHO_DICT_TYPE_MAP);

            Map<String, FileOverrideStrategyEnum> fileOverrideConfig = genVO.getFileOverrideConfig();
            FileOverrideStrategyEnum echoStrategy = null;
            if (fileOverrideConfig != null) {
                echoStrategy = fileOverrideConfig.get(GenCodeConstant.ECHO_LIST);
            }
            echoStrategy = echoStrategy == null ? FileOverrideStrategyEnum.OVERRIDE : echoStrategy;

            if (FileOverrideStrategyEnum.OVERRIDE.eq(echoStrategy)) {
                String outputDir = genTable.getOutputDir();
                if (CollUtil.isNotEmpty(refList)) {
                    Class<?> echoRefClazz = EchoRef.class;
                    writeFile(outputDir, echoRefClazz, getMap(echoRefClazz, refList));
                }
                if (CollUtil.isNotEmpty(apiList)) {
                    Class<?> echoApiClazz = EchoApi.class;
                    writeFile(outputDir, echoApiClazz, getMap(echoApiClazz, apiList));
                }

                if (CollUtil.isNotEmpty(dictMap)) {
                    Map<String, String> dictTypeMap = new HashMap<>();
                    dictMap.forEach((position, list) -> {
                        String api = StrUtil.join(FileInsertUtil.getSeparator() + FileInsertUtil.repeatTab(2), list);
                        dictTypeMap.put(position, api);
                    });
                    Class<?> echoDictTypeClazz = EchoDictType.class;
                    writeFile(outputDir, echoDictTypeClazz, dictTypeMap);
                }
            }
        }
    }

    private void writeFile(String outputDir, Class<?> clazz, Map<String, String> map) {
        if (CollUtil.isEmpty(map)) {
            return;
        }
        String zipOutputFile = getLocalPath(outputDir, clazz.getName());
        FileInsertUtil.of(zipOutputFile, map).writeFile();
    }

    private String getLocalPath(String outputDir, String clazzName) {
        String clazzPath = StrUtil.replace(clazzName, StrPool.DOT, File.separator) + GenCodeConstant.JAVA_SUFFIX;
        String projectPrefix = generatorConfig.getProjectPrefix();
        String modelPath = StrUtil.format(GenCodeConstant.PUBLIC_MODEL_PATH, projectPrefix, projectPrefix);
        return Paths.get(outputDir, modelPath, clazzPath).toString();
    }

    private void writeFile(GenTable genTable, GenTable subTable, String templatePath, Map<String, Object> objectMap, String enumName, GenVO genVO) {
        try {
            TemplateEnum template = genVO.getTemplate();
            Map<String, FileOverrideStrategyEnum> fileOverrideConfig = genVO.getFileOverrideConfig();

            String outputFile = OutputFileUtils.getOutputFile(generatorConfig, genTable, subTable, templatePath, enumName, template);
            File file = new File(outputFile);

            FileOverrideStrategyEnum fileOverride = OutputFileUtils.getFileOverride(generatorConfig, fileOverrideConfig, templatePath, enumName);
            if (!FileOverrideStrategyEnum.IGNORE.eq(fileOverride)) {
                if (file.exists()) {
                    if (FileOverrideStrategyEnum.EXIST_IGNORE.eq(fileOverride)) {
                        log.info("存在时忽略：{}, {}", templatePath, genTable.getName());
                        return;
                    }
                    if (FileOverrideStrategyEnum.ADD.eq(fileOverride)) {
                        outputFile += ".add";
                    }
                } else {
                    File parentFile = file.getParentFile();
                    FileUtil.mkdir(parentFile);
                }

                Template tpl = TemplateUtils.getTemplate(templatePath);
                try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                    tpl.process(objectMap, new OutputStreamWriter(fileOutputStream, GenCodeConstant.UTF8));
                    log.info("成功生成={}", outputFile);
                }
            } else {
                log.info("直接忽略：{}, {}", templatePath, genTable.getName());
            }
        } catch (TemplateException | IOException e) {
            log.info("代码生成异常, 出错原因可能是的表结构没有按照规范编写，导致模板解析出错！", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<?> idList) {
        boolean flag = super.removeByIds(idList);
        genTableColumnManager.removeByTableIds(idList);
        return flag;
    }


    @Override
    public GenTableResultVO getDetail(Long id) {
        GenTable genTable = superManager.getById(id);

        return BeanUtil.toBean(genTable, GenTableResultVO.class);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public GenTable updateById(GenTableUpdateVO genTableUpdateVO) {
        if (genTableUpdateVO.getIsDs() == null && CollUtil.isEmpty(genTableUpdateVO.getTableIdList())) {
            throw new ArgumentException("ID不能为空");
        }
        if (CollUtil.isNotEmpty(genTableUpdateVO.getTableIdList())) {
            List<GenTable> list = genTableUpdateVO.getTableIdList().stream().map(id -> {
                GenTable table = new GenTable();
                BeanUtil.copyProperties(genTableUpdateVO, table);

                // 批量修改时，不允许修改这些参数
                if (genTableUpdateVO.getTableIdList().size() > 1) {
                    table.setEntityName(null);
                    table.setComment(null);
                    table.setSwaggerComment(null);
                    table.setMenuName(null);
                }
                table.setName(null);
                table.setDsId(null);
                table.setId(id);
                return table;
            }).collect(Collectors.toList());
            superManager.updateBatchById(list);
            return list.get(0);
        } else {
            return super.updateById(genTableUpdateVO);
        }
    }

//    @Override
//    public ProjectGeneratorVO getDef() {
//        ProjectGeneratorVO vo = new ProjectGeneratorVO();
//        BeanUtil.copyProperties(generatorConfig, vo);
//        vo.setType(generatorConfig.getProjectType());
//        vo.setParent(generatorConfig.getPackageInfoConfig().getParent());
//        vo.setGroupId(generatorConfig.getPackageInfoConfig().getParent());
//        vo.setUtilParent(generatorConfig.getPackageInfoConfig().getUtilParent());
//        vo.setUtilGroupId(generatorConfig.getPackageInfoConfig().getUtilParent());
//        vo.setServerPort(8080);
//        vo.setVersion(version);
//        return vo;
//    }

//    @Override
//    public Boolean generator(ProjectGeneratorVO projectGenerator) {
//        if (!StrPool.DEV.equals(active)) {
//            throw BizException.wrap("只有本地环境（spring.profiles.active=dev）能直接生成代码到指定路径，线上部署请将【生成方式】改为【打包下载】");
//        }
//        ProjectUtils.generator(projectGenerator, databaseProperties);
//        return true;
//    }
//
//    @Override
//    public DownloadVO download(ProjectGeneratorVO projectGenerator) {
//        return ProjectUtils.download(projectGenerator, databaseProperties);
//    }

    @Override
    public Map<String, FileOverrideStrategyEnum> getFileOverrideStrategy() {
        FileOverrideStrategy defStrategy = generatorConfig.getFileOverrideStrategy();

        Map<String, FileOverrideStrategyEnum> map = new HashMap<>();
        map.put("enum", defStrategy.getEntityFileOverride());
        map.put("saveVo", defStrategy.getEntityFileOverride());
        map.put("updateVo", defStrategy.getEntityFileOverride());
        map.put("resultVo", defStrategy.getEntityFileOverride());
        map.put("pageQuery", defStrategy.getEntityFileOverride());
        map.put("entity", defStrategy.getEntityFileOverride());
        map.put("sql", defStrategy.getSqlFileOverride());
        map.put("xml", defStrategy.getXmlFileOverride());
        map.put("mapper", defStrategy.getMapperFileOverride());
        map.put("manager", defStrategy.getManagerFileOverride());
        map.put("managerImpl", defStrategy.getManagerFileOverride());
        map.put("service", defStrategy.getServiceFileOverride());
        map.put("serviceImpl", defStrategy.getServiceFileOverride());
        map.put("controller", defStrategy.getControllerFileOverride());
        map.put(GenCodeConstant.ECHO_LIST, defStrategy.getEntityFileOverride());

        map.put("api", defStrategy.getApiModelFileOverride());
        map.put("model", defStrategy.getApiModelFileOverride());
        map.put("langEn", defStrategy.getLangFileOverride());
        map.put("langZh", defStrategy.getLangFileOverride());
        map.put("data", defStrategy.getDataFileOverride());
        map.put("index", defStrategy.getIndexEditTreeFileOverride());
        map.put("edit", defStrategy.getIndexEditTreeFileOverride());
        map.put("jumpEdit", defStrategy.getIndexEditTreeFileOverride());
        map.put("treeIndex", defStrategy.getIndexEditTreeFileOverride());
        map.put("treeEdit", defStrategy.getIndexEditTreeFileOverride());
        map.put("treeTree", defStrategy.getIndexEditTreeFileOverride());
        return map;
    }

    @Override
    public Map<String, String> getFieldTemplate() {
        return TEMPLATE_FIELD_MAP;
    }

    @Override
    public List<GenTableResultVO> findTableList(List<Long> idList) {
        List<GenTable> list = superManager.listByIds(idList);
        return BeanUtil.copyToList(list, GenTableResultVO.class);
    }

}
