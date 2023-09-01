package cn.lmx.kpu.generator.utils;

import cn.lmx.basic.utils.StrPool;

public interface GenCodeConstant {
    String TEMPLATE_ENTITY_JAVA = "templates/backend/java/entity.java.ftl";
    String TEMPLATE_SAVE_VO = "templates/backend/java/saveVo.java.ftl";
    String TEMPLATE_UPDATE_VO = "templates/backend/java/updateVo.java.ftl";
    String TEMPLATE_PAGE_QUERY = "templates/backend/java/pageQuery.java.ftl";
    String TEMPLATE_RESULT_VO = "templates/backend/java/resultVo.java.ftl";

    String TEMPLATE_MAPPER = "templates/backend/java/mapper.java.ftl";
    String TEMPLATE_MAPPER_XML = "templates/backend/resources/mapper.xml.ftl";

    String TEMPLATE_MANAGER = "templates/backend/java/superManager.java.ftl";
    String TEMPLATE_MANAGER_IMPL = "templates/backend/java/superManagerImpl.java.ftl";

    String TEMPLATE_SERVICE = "templates/backend/java/superService.java.ftl";
    String TEMPLATE_SERVICE_IMPL = "templates/backend/java/superServiceImpl.java.ftl";

    String TEMPLATE_CONTROLLER = "templates/backend/java/controller.java.ftl";

    String ENUM = "enum";
    String[] NOT_QUERY = {"create_time", "update_time", "create_by", "update_by", "del_flag"};
    String[] NOT_LIST = {"create_time", "update_time", "create_by", "update_by", "del_flag"};
    String[] NOT_EDIT = {"create_time", "update_time", "create_by", "update_by", "del_flag"};
    String UTF8 = "UTF-8";
    String SRC_MAIN_JAVA = "src/main/java";
    String JAVA_SUFFIX = StrPool.DOT_JAVA;
    String XML_SUFFIX = ".xml";
    String FTL_SUFFIX = ".ftl";
    String SQL_SUFFIX = ".sql";


    String TEMPLATE_SQL = "templates/backend/resources/sql.sql.ftl";
    String ENTITY_SERVICE_SUFFIX = "kpu";
    String SRC_MANI_RESOURCES = "src/main/resources";
    String BIZ_SERVICE_SUFFIX = "biz";
    String CONTROLLER_SERVICE_SUFFIX = "controller";
    String CONTROLLER = "Controller";
    String SERVICE_IMPL = "ServiceImpl";
    String SERVICE = "Service";
    String MANAGER_IMPL = "ManagerImpl";
    String MANAGER = "Manager";
    String MAPPER = "Mapper";
    String RESULT_VO = "ResultVO";
    String UPDATE_VO = "UpdateVO";
    String SAVE_VO = "SaveVO";
    String PAGE_QUERY = "PageQuery";
    String ENTITY_FILE_OVERRIDE = "entityFileOverride";
    String SQL_FILE_OVERRIDE = "sqlFileOverride";
    String MAPPER_FILE_OVERRIDE = "mapperFileOverride";
    String SERVICE_FILE_OVERRIDE = "serviceFileOverride";
    String MANAGER_FILE_OVERRIDE = "managerFileOverride";
    String CONTROLLER_FILE_OVERRIDE = "controllerFileOverride";
}
