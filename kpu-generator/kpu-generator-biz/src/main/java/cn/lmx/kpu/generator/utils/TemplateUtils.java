package cn.lmx.kpu.generator.utils;

import cn.lmx.kpu.generator.enumeration.PopupTypeEnum;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import cn.lmx.kpu.generator.enumeration.TplEnum;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板工具类
 *
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/13 14:27
 */
public class TemplateUtils {
    private static final Configuration configuration;

    static {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configuration.setClassForTemplateLoading(SourceCodeUtils.class, StringPool.SLASH);
    }

    public static Template getTemplate(String templatePath) throws IOException {
        return configuration.getTemplate(templatePath);
    }

    /**
     * 获取模板列表
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(TemplateEnum template, TplEnum tplType, PopupTypeEnum popupType) {
        List<String> templates = new ArrayList<>();
        if (TemplateEnum.BACKEND.eq(template)) {
            templates.add(GenCodeConstant.TEMPLATE_CONTROLLER);
            templates.add(GenCodeConstant.TEMPLATE_SERVICE);
            templates.add(GenCodeConstant.TEMPLATE_SERVICE_IMPL);
            templates.add(GenCodeConstant.TEMPLATE_MANAGER);
            templates.add(GenCodeConstant.TEMPLATE_MANAGER_IMPL);
            templates.add(GenCodeConstant.TEMPLATE_MAPPER);
            templates.add(GenCodeConstant.TEMPLATE_XML);
            templates.add(GenCodeConstant.TEMPLATE_ENTITY_JAVA);
            templates.add(GenCodeConstant.TEMPLATE_SAVE_VO);
            templates.add(GenCodeConstant.TEMPLATE_UPDATE_VO);
            templates.add(GenCodeConstant.TEMPLATE_RESULT_VO);
            templates.add(GenCodeConstant.TEMPLATE_PAGE_QUERY);
            templates.add(GenCodeConstant.TEMPLATE_SQL);
        } else if (TemplateEnum.WEB_PLUS.eq(template)) {
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_API);
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_MODEL);
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_LANG_EN);
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_LANG_ZH);


            if (TplEnum.TREE.eq(tplType)) {
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_INDEX);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_EDIT);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_TREE);
            } else if (TplEnum.MAIN_SUB.eq(tplType)) {
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_JUMP_EDIT);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_EDIT);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_INDEX);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_FORM_MODE);
                // 从表
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_SUB_INDEX);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_SUB_DATA);

            } else {
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_JUMP_EDIT);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_EDIT);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_INDEX);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_FORM_MODE);
            }
        }
        return templates;
    }

    /**
     * 获取模板列表
     *
     * @return 模板列表
     */
    public static List<String> getSubTemplateList(TemplateEnum template) {
        List<String> templates = new ArrayList<>();
        if (TemplateEnum.BACKEND.eq(template)) {
            templates.add(GenCodeConstant.TEMPLATE_MANAGER);
            templates.add(GenCodeConstant.TEMPLATE_MANAGER_IMPL);
            templates.add(GenCodeConstant.TEMPLATE_MAPPER);
            templates.add(GenCodeConstant.TEMPLATE_XML);
            templates.add(GenCodeConstant.TEMPLATE_ENTITY_JAVA);
            templates.add(GenCodeConstant.TEMPLATE_SAVE_VO);
            templates.add(GenCodeConstant.TEMPLATE_UPDATE_VO);
        }
        return templates;
    }

}
