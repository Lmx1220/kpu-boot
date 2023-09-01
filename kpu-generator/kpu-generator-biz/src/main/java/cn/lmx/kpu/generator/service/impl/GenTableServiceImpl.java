package cn.lmx.kpu.generator.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.exception.ArgumentException;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.generator.config.GeneratorConfig;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import cn.lmx.kpu.generator.manager.GenTableColumnManager;
import cn.lmx.kpu.generator.manager.GenTableManager;
import cn.lmx.kpu.generator.service.GenTableService;
import cn.lmx.kpu.generator.utils.GenCodeConstant;
import cn.lmx.kpu.generator.utils.OutputFileUtils;
import cn.lmx.kpu.generator.utils.SourceCodeUtil;
import cn.lmx.kpu.generator.utils.TemplateUtils;
import cn.lmx.kpu.generator.vo.query.GenTablePageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableSaveVO;
import cn.lmx.kpu.generator.vo.save.GenVO;
import cn.lmx.kpu.generator.vo.update.GenTableUpdateVO;
import com.baidu.fsg.uid.UidGenerator;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  15:31
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GenTableServiceImpl extends SuperServiceImpl<GenTableManager, Long, GenTable, GenTableSaveVO,
        GenTableUpdateVO, GenTablePageQuery, GenTableResultVO> implements GenTableService {
    private final GenTableColumnManager genTableColumnManager;
    private final GeneratorConfig generatorConfig;
    private final DatabaseProperties databaseProperties;
    private final UidGenerator uidGenerator;
    @Value("${spring.profiles.active}")
    private String profile;

    public Map<String, String> previewCode(Long id, TemplateEnum template) {
        GenTable genTable = previewCheck(id);
        List<GenTableColumn> fileList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, id));
        Map<String, Object> subObjectMap = new HashMap();
        Map<String, Object> objectMap = SourceCodeUtil.getObjMap(generatorConfig, databaseProperties, uidGenerator, subObjectMap, genTable, fileList, databaseProperties.getDbType());
        List<String> templatePathList = TemplateUtils.getTemplateList(template);
        Map<String, String> fileCodeMap = preview(objectMap, templatePathList, template);
//        if (CollUtil.isNotEmpty(fileCodeMap)){
//            suf.forEach((key,value) -> fileCodeMap.put("sub-"+key,value.toString()));
//        }
        return fileCodeMap;
    }

    public void previewCode(GenVO genVO) {
        List<Long> ids = genVO.getIds();
        TemplateEnum templateEnum = genVO.getTemplateEnum();
        if (!StrPool.DEV.equals(profile)) {
            throw new BizException("只有本地环境(spring.profiles.active=dev)能直接生成代码到指定路径，线上环境请使用打包功能");
        }
        for (Long id : ids) {
            GenTable genTable = previewCheck(id);
            if (TemplateEnum.BACKEND.eq(templateEnum)) {
                ArgumentAssert.notEmpty(genTable.getOutputDir(), "请先配置代码生成绝对路径");
            } else {
                ArgumentAssert.notEmpty(genTable.getFrontOutputDir(), "请先配置代码生成绝对路径");
            }

            List<GenTableColumn> fileList = genTableColumnManager.list(Wraps.<GenTableColumn>lbQ().eq(GenTableColumn::getTableId, id));
            Map<String, Object> objMap = SourceCodeUtil.getObjMap(generatorConfig, databaseProperties, uidGenerator, null, genTable, fileList, databaseProperties.getDbType());
            List<String> templatePathList = TemplateUtils.getTemplateList(templateEnum);
            generatorByTemplate(genTable, templatePathList, objMap, genVO);
        }

    }

    private void generatorByTemplate(GenTable genTable, List<String> templatePathList, Map<String, Object> objMap, GenVO genVO) {
        TemplateEnum template = genVO.getTemplateEnum();
        for (String templatePath : templatePathList) {
            wireFile(genTable, templatePath, objMap, null, genVO);
        }
    }

    private void wireFile(GenTable genTable, String templatePath, Map<String, Object> objMap, String enumName, GenVO genVO) {
        try {
            TemplateEnum template = genVO.getTemplateEnum();
            Map<String, FileOverrideStrategyEnum> fileOverrideConfig = genVO.getFileOverrideConfig();
            String outputFile = OutputFileUtils.getOutputFile(generatorConfig, genTable, templatePath, enumName, template);
            File file = new File(outputFile);
            FileOverrideStrategyEnum fileOverride = OutputFileUtils.getFileOverride(generatorConfig, genTable, templatePath, enumName, template);
            if (!FileOverrideStrategyEnum.IGNORE.eq(fileOverride)) {
                if (file.exists()) {
                    if (FileOverrideStrategyEnum.EXIST_IGNORE.eq(fileOverride)) {
                        log.info("存在时忽略：{}，{}", templatePath, genTable.getName());
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
                    tpl.process(objMap, new OutputStreamWriter(fileOutputStream, GenCodeConstant.UTF8));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GenTable previewCheck(Long id) {
        GenTable genTable = getById(id);
        if (genTable == null) {
            throw new ArgumentException("表不存在");
        }
        return genTable;
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
                log.error("代码商城异常，出错原因可能hi的表结构没有按照规范编写", e);
                fileCodeMap.put(templatePath, e.getMessage());
            }
        }
        // #TODO 生成其他常量等操作...
        if (TemplateEnum.BACKEND.equals(template)) {

        }
        return fileCodeMap;
    }

}
