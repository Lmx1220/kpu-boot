package cn.lmx.kpu.msg.strategy.impl.sms;


import cn.hutool.core.bean.BeanUtil;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.entity.MsgRecipient;
import cn.lmx.kpu.msg.strategy.MsgStrategy;
import cn.lmx.kpu.msg.strategy.domain.*;
import cn.lmx.kpu.msg.strategy.domain.cl.AliSmsProperty;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import cn.lmx.basic.jackson.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ali 发送短信实现类
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Component("aliSmsMsgStrategyImpl")
@Slf4j
public class AliSmsMsgStrategyImpl implements MsgStrategy {

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    private final static Map<String, com.aliyun.dysmsapi20170525.Client> CACHE = new HashMap<>();

    @Override
    public MsgResult exec(MsgParam msgParam){
        // 代码省略...
        Msg msg = msgParam.getMsg();
        List<MsgRecipient> recipientList = msgParam.getRecipientList();
        Map<String, Object> propertyParams = msgParam.getPropertyParams();
        AliSmsProperty property = new AliSmsProperty();
        BeanUtil.fillBeanWithMap(propertyParams, property, true);
        property.initAndValid();
        if (property.getDebug()) {
            SendSmsResponse result = new SendSmsResponse();
            SendStatus ss = new SendStatus();
            result.setStatusCode(new SendStatus[]{ss});
            return MsgResult.builder().result(result).build();
        }

        return MsgResult.builder().build();

    }
//
//    public SmsAliStrategy(TaskMapper smsTaskMapper, SendStatusService smsSendStatusService) {
//        super(smsTaskMapper, smsSendStatusService);
//    }
//
//    /**
//     * 使用AK&SK初始化账号Client
//     *
//     * @return Client
//     * @throws Exception
//     */
//    @SneakyThrows
//    public static com.aliyun.dysmsapi20170525.Client createClient(SmsDO smsDO) {
//        String key = StrUtil.format("{}:{}:{}:{}", smsDO.getAppId(), smsDO.getAppSecret(),
//                PRODUCT, DOMAIN);
//
//        if (CACHE.containsKey(key)) {
//            return CACHE.get(key);
//        }
//
//        Config config = new Config()
//                // 您的 AccessKey ID
//                .setAccessKeyId(smsDO.getAppId())
//                // 您的 AccessKey Secret
//                .setAccessKeySecret(smsDO.getAppSecret())
//                .setEndpoint(DOMAIN);
//        if (StrUtil.isNotEmpty(PRODUCT)) {
//            config.setRegionId(PRODUCT);
//        }
//        com.aliyun.dysmsapi20170525.Client client = new com.aliyun.dysmsapi20170525.Client(config);
//        CACHE.put(key, client);
//        return client;
//    }
//
//
//    @Override
//    @SneakyThrows
//    protected SmsResult send(SmsDO smsDO) {
//
//        // 最多1000个手机
//        String phoneNumbers = smsDO.getTelNum();
//
//        //可自助调整超时时间 也可以配置在 propertyParams 中
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//        com.aliyun.dysmsapi20170525.Client client = createClient(smsDO);
//
//        //组装请求对象-具体描述见控制台-文档部分内容
//        SendSmsRequest request = new SendSmsRequest();
//        //必填:待发送手机号
//        request.setPhoneNumbers(phoneNumbers);
//        //必填:短信签名-可在短信控制台中找到
//        request.setSignName(smsDO.getSignName());
//        //必填:短信模板-可在短信控制台中找到
//        request.setTemplateCode(smsDO.getTemplateCode());
//        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        Map<String, String> map = parseParam(smsDO.getTemplateParams());
//        request.setTemplateParam(JsonUtil.toJson(map));
//
//        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId(String.valueOf(smsDO.getTaskId()));
//
//        log.info("阿里短信发送参数={}", JSONObject.toJSONString(request));
//        //hint 此处可能会抛出异常，注意catch
//        SendSmsResponse sendSmsResponse = client.sendSms(request);
//
//        log.info("阿里短信发送结果={}", JSONObject.toJSONString(sendSmsResponse));
//        return SmsResult.build(ProviderType.ALI, sendSmsResponse.getBody().code, sendSmsResponse.getBody().bizId,
//                sendSmsResponse.getBody().getRequestId(), sendSmsResponse.getBody().message, 0);
//
//
////        //可自助调整超时时间
////        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
////        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
////
////        try {
////            //初始化acsClient,暂不支持region化
////            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsDO.getAppId(), smsDO.getAppSecret());
////            DefaultProfile.addEndpoint("cn-hangzhou", PRODUCT, DOMAIN);
////            IAcsClient acsClient = new DefaultAcsClient(profile);
////
////            //组装请求对象-具体描述见控制台-文档部分内容
////            SendSmsRequest request = new SendSmsRequest();
////            //必填:待发送手机号
////            request.setPhoneNumbers(smsDO.getTelNum());
////            //必填:短信签名-可在短信控制台中找到
////            request.setSignName(smsDO.getSignName());
////            //必填:短信模板-可在短信控制台中找到
////            request.setTemplateCode(smsDO.getTemplateCode());
////            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
////            List<Kv> list = JsonUtil.parseArray(smsDO.getTemplateParams(), Kv.class);
////            Map<String, String> map = new LinkedHashMap<>();
////            for (Kv kv : list) {
////                map.put(kv.getKey(), kv.getValue());
////            }
////            request.setTemplateParam(JsonUtil.toJson(map));
////
////            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
////            request.setOutId(String.valueOf(smsDO.getTaskId()));
////
////            log.info("阿里短信发送参数={}", JSONObject.toJSONString(request));
////            //hint 此处可能会抛出异常，注意catch
////            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
////
////            log.info("阿里短信发送结果={}", JSONObject.toJSONString(sendSmsResponse));
////            return SmsResult.build(ProviderType.ALI, sendSmsResponse.getCode(), sendSmsResponse.getBizId(),
////                    sendSmsResponse.getRequestId(), sendSmsResponse.getMessage(), 0);
////        } catch (ClientException e) {
////            log.warn("阿里短信发送失败:" + smsDO.getTelNum(), e);
////            return SmsResult.fail(e.getMessage());
////        }
//
//    }
}
