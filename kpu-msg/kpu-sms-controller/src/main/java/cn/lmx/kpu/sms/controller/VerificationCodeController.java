package cn.lmx.kpu.sms.controller;

import cn.hutool.core.util.RandomUtil;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.model.Kv;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.kpu.common.cache.VerificationCodeCacheKeyBuilder;
import cn.lmx.kpu.sms.enumeration.SourceType;
import cn.lmx.kpu.sms.service.ESmsTaskService;
import cn.lmx.kpu.sms.vo.save.ESmsTaskSaveVO;
import cn.lmx.kpu.sms.vo.save.VerificationCodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通用验证码
 *
 * @author zuihou
 * @date 2019/08/06
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/verification")
@Api(value = "VerificationCode", tags = "验证码")
@RequiredArgsConstructor
public class VerificationCodeController {

    private final CacheOps cacheOps;
    private final ESmsTaskService smsTaskService;

    /**
     * 通用的发送验证码功能
     * <p>
     * 一个系统可能有很多地方需要发送验证码（注册、找回密码等），每增加一个场景，VerificationCodeType 就需要增加一个枚举值
     */
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @PostMapping(value = "/send")
    public R<Boolean> send(@Validated @RequestBody VerificationCodeVO data) {
        String code = RandomUtil.randomNumbers(6);

        ESmsTaskSaveVO smsTask = ESmsTaskSaveVO.builder().build();
        smsTask.setSourceType(SourceType.SERVICE);
        List<Kv> params = new ArrayList<>();
        params.add(Kv.builder().key("code").value(code).build());
        smsTask.setTemplateParam(params);
        smsTask.setTelNum(Arrays.asList(data.getMobile()));
        // 请自行在SmsTemplate 表配置id=1的短信模板
        smsTask.setTemplateId(1L);
        smsTask.setDraft(false);
        smsTaskService.saveTask(smsTask);

        CacheKey cacheKey = new VerificationCodeCacheKeyBuilder().key(data.getType().name(), data.getMobile());
        cacheOps.set(cacheKey, code);
        return R.success();
    }

    /**
     * 对某种类型的验证码进行校验
     */
    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @PostMapping
    public R<Boolean> verification(@Validated(SuperEntity.Update.class) @RequestBody VerificationCodeVO data) {
        CacheKey cacheKey = new VerificationCodeCacheKeyBuilder().key(data.getType().name(), data.getMobile());
        String code = cacheOps.get(cacheKey);
        return R.success(data.getCode().equals(code));
    }
}
