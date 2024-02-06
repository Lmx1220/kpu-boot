package cn.lmx.kpu.model.constant;

/**
 * Echo 注解中api的常量
 * <p>
 * 切记，该类下的接口和方法，一定要自己手动创建，否则会注入失败
 * <p>
 * 本类中的 @kpu.generator auto insert 请勿删除
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
public interface EchoApi {
    // @kpu.generator auto insert EchoApi

    /**
     * kpu-cloud 数据字典项 feign查询类 全类名
     */
    String DICTIONARY_ITEM_FEIGN_CLASS = "dictServiceImpl";
    /**
     * 组织 service查询类
     */
    String ORG_ID_CLASS = "baseOrgManagerImpl";
    /**
     * 岗位 service查询类
     */
    String POSITION_ID_CLASS = "basePositionManagerImpl";
    String DEF_TENANT_SERVICE_IMPL_CLASS = "defTenantManagerImpl";
    String DEF_APPLICATION_SERVICE_IMPL_CLASS = "defApplicationManagerImpl";

}
