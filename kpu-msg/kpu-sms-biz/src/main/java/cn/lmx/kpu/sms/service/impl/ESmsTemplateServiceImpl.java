package cn.lmx.kpu.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.jackson.JsonUtil;
import cn.lmx.basic.model.Kv;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.sms.entity.ESmsTemplate;
import cn.lmx.kpu.sms.manager.ESmsTemplateManager;
import cn.lmx.kpu.sms.service.ESmsTemplateService;
import cn.lmx.kpu.sms.vo.query.ESmsTemplatePageQuery;
import cn.lmx.kpu.sms.vo.result.ESmsTemplateResultVO;
import cn.lmx.kpu.sms.vo.save.ESmsTemplateSaveVO;
import cn.lmx.kpu.sms.vo.update.ESmsTemplateUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 业务实现类
 * 短信模板
 * </p>
 *
 * @author lmx
 * @date 2023-11-14 11:08:02
 * @create [2023-11-14 11:08:02] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ESmsTemplateServiceImpl extends SuperServiceImpl<ESmsTemplateManager, Long, ESmsTemplate, ESmsTemplateSaveVO,
        ESmsTemplateUpdateVO, ESmsTemplatePageQuery, ESmsTemplateResultVO> implements ESmsTemplateService {

    @Transactional(readOnly = true)
    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return CollHelper.uniqueIndex(listByIds(ids), ESmsTemplate::getId, ESmsTemplate::getName);
    }

    private static String getParamByContent(String content, String regEx) {
        //编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        //忽略大小写的写法:
        Matcher matcher = pattern.matcher(content);

        // 查找字符串中是否有匹配正则表达式的字符/字符串//有序， 目的是为了兼容 腾讯云参数
        List<Kv> list = new ArrayList<>();
        while (matcher.find()) {
            String key = matcher.group(1);
            list.add(Kv.builder().key(key).value(StrPool.EMPTY).build());
        }
        if (list.isEmpty()) {
            throw BizException.wrap("模板内容解析失败，请认真详细内容格式");
        }

        return JsonUtil.toJson(list);
    }

    @Override
    protected ESmsTemplate saveBefore(ESmsTemplateSaveVO eSmsTemplateSaveVO) {
        String content = eSmsTemplateSaveVO.getContent();
        if (StrUtil.isNotEmpty(content)) {
            String param = getParamByContent(content, eSmsTemplateSaveVO.getProviderType().getRegex());
            eSmsTemplateSaveVO.setTemplateParams(param);
        }
        return super.saveBefore(eSmsTemplateSaveVO);
    }
    @Override
    protected ESmsTemplate updateBefore(ESmsTemplateUpdateVO eSmsTemplateUpdateVO) {
        String content = eSmsTemplateUpdateVO.getContent();
        if (StrUtil.isNotEmpty(content)) {
            String param = getParamByContent(content, eSmsTemplateUpdateVO.getProviderType().getRegex());
            eSmsTemplateUpdateVO.setTemplateParams(param);
        }
        return super.updateBefore(eSmsTemplateUpdateVO);
    }
}


