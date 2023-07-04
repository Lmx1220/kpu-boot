package cn.lmx.kpu.common.constant;

import cn.lmx.basic.utils.ArgumentAssert;

import java.util.Arrays;
import java.util.List;

/**
 * 附件表中事先约定的业务类型。
 * <p>
 * 预定方式为：  定义一个系统级别的接口   此接口内包含这个系统内的所有表中需要的附件类型
 * key定时方式为entity_field
 * value定义方式为： 系统简称-entity-field
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public final class AppendixType {

    /**
     * 后端代码不需要使用该 业务类型时， 无需使用常量，直接在ALL_TYPES数组中写字符串即可。
     * 命名规则:
     * 业务表名_字段名
     */
    public static final List<String> ALL_TYPES = Arrays.asList(Authority.APPLICATION_LOGO_URL,
            Authority.APPLICATION_TITLE_ICON);


    private AppendixType() {
    }

    public static void assertType(String type) {
        ArgumentAssert.contain(ALL_TYPES, type, "附件类型未定义");
    }

    /**
     * 权限管理系统中有关的的附件类型定义
     *
     * @author lmx
     */
    public interface Authority {
        /**
         * 权限管理系统中的应用表中的logo
         */
        String APPLICATION_LOGO_URL = "auth_application_logo";
        /**
         * 权限管理系统中的应用表中的应用标题图标
         */
        String APPLICATION_TITLE_ICON = "auth_application_titleIcon";
        /**
         * 基础库 用户头像
         *
         * @author lmx
         * @date 2023/7/4 14:27
         * @create [2023/7/4 14:27 ] [lmx] [初始创建]
         */
        String BASE_USER_AVATAR = "BASE_USER_AVATAR";
    }

    /**
     * 文件 业务类型定义
     */
    interface File {
        /**
         * 基础库 文件中心
         *
         * @author lmx
         * @date 2023/7/4 14:27
         * @create [2023/7/4 14:27 ] [lmx] [初始创建]
         */
        String BASE_FILE = "BASE_FILE";
    }


    /**
     * 消息系统 业务类型定义
     */
    interface Msg {
        /**
         * 扩展库 消息内容附件
         *
         * @author lmx
         * @date 2023/7/4 14:27
         * @create [2023/7/4 14:27 ] [lmx] [初始创建]
         */
        String EXTEND_MSG_CONTENT = "EXTEND_MSG_CONTENT";
    }


}
