package cn.lmx.kpu.model.constant;

public interface EchoDictType {
    //  @kpu.generator auto insert EchoDictType

    interface Tenant {
        // @kpu.generator auto insert Tenant
        /**
         * 地区来源
         */
        String AREA_SOURCE = "TENANT_AREA_SOURCE";
    }
    interface Global {
        // @kpu.generator auto insert Global
        /**
         * 性别
         */
        String SEX = "GLOBAL_SEX";
        /**
         * 行政级别
         */
        String AREA_LEVEL = "GLOBAL_AREA_LEVEL";
    }
}
