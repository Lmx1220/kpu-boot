package cn.lmx.kpu.common.constant;

/**
 * 业务常量
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BizConstant {
    /**
     * 工具类 需要扫描的包
     */
    String UTIL_PACKAGE = "cn.lmx.basic";
    /**
     * 业务项目 需要扫描的包
     */
    String BUSINESS_PACKAGE = "cn.lmx.kpu";
    /**
     * 被T
     */
    String LOGIN_STATUS = "T";

    String BASE = "kpu-base-server";
    String FILE = "kpu-file-server";
    String MSG = "kpu-msg-server";
    String OAUTH = "kpu-oauth-server";
    String GATE = "kpu-gateway-server";
    String TENANT = "kpu-system-server";
    String BASE_EXECUTOR = "kpu-base-executor";
    String EXTEND_EXECUTOR = "kpu-extend-executor";
    String ORDER = "kpu-example-server";
    String DEMO = "kpu-demo-server";

    /**
     * 初始化数据源时json的参数，
     * method 的可选值为 {INIT_DS_PARAM_METHOD_INIT} 和 {INIT_DS_PARAM_METHOD_REMOVE}
     */
    String INIT_DS_PARAM_METHOD = "method";
    /**
     * 初始化数据源时json的参数，
     * tenant 的值为 需要初始化的租户编码
     */
    String INIT_DS_PARAM_TENANT = "tenant";
    /**
     * 初始化数据源时，需要执行的方法
     * init 表示初始化数据源
     * remove 表示删除数据源
     */
    String INIT_DS_PARAM_METHOD_INIT = "init";
    /**
     * 初始化数据源时，需要执行的方法
     * init 表示初始化数据源
     * remove 表示删除数据源
     */
    String INIT_DS_PARAM_METHOD_REMOVE = "remove";
    /**
     * 框架布局
     */
    String IFRAME = "IFRAME";
    /**
     * 页面布局
     */
    String LAYOUT = "LAYOUT";
    /**
     * 绑定范围类型 机构
     */
    String SCOPE_TYPE_ORG = "2";
    /**
     * 绑定范围类型 员工
     */
    String SCOPE_TYPE_EMPLOYEE = "1";
    /**
     * 绑定范围 已绑定
     */
    String SCOPE_BIND = "1";
    /**
     * 绑定范围 未绑定
     */
    String SCOPE_UN_BIND = "2";
}
