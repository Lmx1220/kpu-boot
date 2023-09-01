package cn.lmx.kpu.generator.utils;

import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/22  15:21
 */
public class TemplateUtils {
    private static Configuration configuration;

    public TemplateUtils() {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(ConstVal.UTF8);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);
    }

    public static Template getTemplate(String templatePath) throws IOException {
        return configuration.getTemplate(templatePath);
    }

    public static List<String> getTemplateList(TemplateEnum template) {

        List<String> templateList = new ArrayList<>();
        templateList.add(GenCodeConstant.TEMPLATE_CONTROLLER);
        templateList.add(GenCodeConstant.TEMPLATE_SERVICE);
        templateList.add(GenCodeConstant.TEMPLATE_SERVICE_IMPL);
        templateList.add(GenCodeConstant.TEMPLATE_MANAGER);
        templateList.add(GenCodeConstant.TEMPLATE_MANAGER_IMPL);
        templateList.add(GenCodeConstant.TEMPLATE_MAPPER);
        templateList.add(GenCodeConstant.TEMPLATE_MAPPER_XML);
        templateList.add(GenCodeConstant.TEMPLATE_ENTITY_JAVA);
        templateList.add(GenCodeConstant.TEMPLATE_SAVE_VO);
        templateList.add(GenCodeConstant.TEMPLATE_UPDATE_VO);
        templateList.add(GenCodeConstant.TEMPLATE_PAGE_QUERY);
        templateList.add(GenCodeConstant.TEMPLATE_RESULT_VO);

        return templateList;
    }
}
