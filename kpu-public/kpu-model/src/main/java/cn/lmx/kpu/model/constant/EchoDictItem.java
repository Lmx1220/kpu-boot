package cn.lmx.kpu.model.constant;

/**
 * 存放系统中常用的类型
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface EchoDictItem {
    // @kpu.generator auto insert EchoDictItem
    /**
     * 职位状态
     */
    String POSITION_STATUS = "POSITION_STATUS";
    /**
     * 民族
     */
    String NATION = "NATION";
    /**
     * 学历
     */
    String EDUCATION = "EDUCATION";
    /**
     * 性别
     */
    String SEX = "SEX";
    /**
     * 行政区级
     */
    String AREA_LEVEL = "AREA_LEVEL";
    /**
     * 资源类型
     */
    String RESOURCE_TYPE = "RESOURCE_TYPE";
    /**
     * 角色类别
     */
    String ROLE_CATEGORY = "ROLE_CATEGORY";
    /**
     * 机构类型
     */
    String ORG_TYPE = "ORG_TYPE";
    /**
     * 授权类型
     */
    String AUTHORIZE_TYPE = "AUTHORIZE_TYPE";
    String[] ALL = new String[]{
            EDUCATION, NATION, POSITION_STATUS, SEX, AREA_LEVEL, ORG_TYPE, AUTHORIZE_TYPE
    };


}
