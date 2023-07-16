package cn.lmx.kpu.model.constant;

/**
 * 仅仅用于记录 RemoteField 注解相关的 接口和方法名称
 * <p>
 * 切记，该类下的接口和方法，一定要自己手动创建，否则会注入失败
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface EchoApi {
    // @kpu.generator auto insert EchoApi

    /**
     * 数据字典项 feign查询类 全类名
     */
    String DICTIONARY_ITEM_FEIGN_CLASS = "cn.lmx.kpu.common.api.DictApi";
    /**
     * 数据字典项 service查询类
     */
    String DICT_ITEM_CLASS = "dictServiceImpl";

    /**
     * 组织 service查询类
     */
    String ORG_ID_CLASS = "orgServiceImpl";
    /**
     * 组织 feign查询类
     */
    String ORG_ID_FEIGN_CLASS = "cn.lmx.kpu.common.api.OrgApi";

    /**
     * 用户 service查询类
     */
    String USER_ID_CLASS = "userServiceImpl";
    /**
     * 用户 feign查询类
     */
    String USER_ID_FEIGN_CLASS = "cn.lmx.kpu.common.api.UserApi";

    /**
     * 组织 service查询类
     */
    String STATION_ID_CLASS = "stationServiceImpl";
    /**
     * 组织 feign查询类
     */
    String STATION_ID_FEIGN_CLASS = "cn.lmx.kpu.common.api.StationApi";

    /**
     * 短信模板 service查询类
     */
    String SMS_TEMPLATE_ID_CLASS = "smsTemplateServiceImpl";

}
