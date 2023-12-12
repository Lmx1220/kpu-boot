package cn.lmx.kpu.model.constant;

public interface EchoDictType {
    interface Base {
        // @kpu.generator auto insert Base
        String INTERFACE_EXEC_MODE = "INTERFACE_EXEC_MODE";
        String MSG_TEMPLATE_TYPE = "MSG_TEMPLATE_TYPE";
        String NOTICE_TARGET = "NOTICE_TARGET";
        String NOTICE_REMIND_MODE = "NOTICE_REMIND_MODE";
    }
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
