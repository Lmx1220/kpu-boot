package cn.lmx.kpu.msg.service.impl;

import cn.hutool.core.util.StrUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.jackson.JsonUtil;
import cn.lmx.basic.model.Kv;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.StrPool;

import cn.lmx.kpu.msg.entity.DefMsgTemplate;
import cn.lmx.kpu.msg.manager.DefMsgTemplateManager;
import cn.lmx.kpu.msg.service.DefMsgTemplateService;
import cn.lmx.kpu.msg.vo.save.DefMsgTemplateSaveVO;
import cn.lmx.kpu.msg.vo.update.DefMsgTemplateUpdateVO;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 业务实现类
 * 消息模板
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DefMsgTemplateServiceImpl extends SuperServiceImpl<DefMsgTemplateManager, Long, DefMsgTemplate> implements DefMsgTemplateService {
    @Override
    public DefMsgTemplate getByCode(String code) {
        return superManager.getByCode(code);
    }

    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写模板标识");
        return superManager.count(Wraps.<DefMsgTemplate>lbQ().eq(DefMsgTemplate::getCode, code)
                .ne(DefMsgTemplate::getId, id)) > 0;
    }

    /**
     * 解析占位符 ${xxx}
     */
    private final static Pattern REG_EX = Pattern.compile("\\$\\{([^}]+)}");

    private static String getParamByContent(String title, String content) {


        // 查找字符串中是否有匹配正则表达式的字符/字符串//有序， 目的是为了兼容 腾讯云参数
        Set<Kv> list = new LinkedHashSet<>();
        if (StrUtil.isNotEmpty(title)) {
            //编译正则表达式
            //忽略大小写的写法:
            Matcher matcher = REG_EX.matcher(title);
            while (matcher.find()) {
                String key = matcher.group(1);
                list.add(Kv.builder().key(key).value(StrPool.EMPTY).build());
            }
        }

        if (StrUtil.isNotEmpty(content)) {
            //编译正则表达式
            //忽略大小写的写法:
            Matcher matcher = REG_EX.matcher(content);
            while (matcher.find()) {
                String key = matcher.group(1);
                list.add(Kv.builder().key(key).value(StrPool.EMPTY).build());
            }
        }

        return JsonUtil.toJson(list);
    }

    @Override
    protected <SaveVO> DefMsgTemplate saveBefore(SaveVO saveVO) {
        DefMsgTemplateSaveVO extendMsgTemplateSaveVO = (DefMsgTemplateSaveVO) saveVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(extendMsgTemplateSaveVO.getCode()) &&
                check(extendMsgTemplateSaveVO.getCode(), null), "模板标识{}已存在", extendMsgTemplateSaveVO.getCode());
        extendMsgTemplateSaveVO.setParam(getParamByContent(extendMsgTemplateSaveVO.getTitle(), extendMsgTemplateSaveVO.getContent()));
        return super.saveBefore(extendMsgTemplateSaveVO);
    }

    @Override
    protected <UpdateVO> DefMsgTemplate updateBefore(UpdateVO updateVO) {
        DefMsgTemplateUpdateVO extendMsgTemplateUpdateVO = (DefMsgTemplateUpdateVO) updateVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(extendMsgTemplateUpdateVO.getCode()) &&
                        check(extendMsgTemplateUpdateVO.getCode(), extendMsgTemplateUpdateVO.getId()),
                "模板标识{}已存在", extendMsgTemplateUpdateVO.getCode());
        extendMsgTemplateUpdateVO.setParam(getParamByContent(extendMsgTemplateUpdateVO.getTitle(), extendMsgTemplateUpdateVO.getContent()));
        return super.updateBefore(extendMsgTemplateUpdateVO);
    }
}


