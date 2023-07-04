package cn.lmx.kpu.security.properties;

/**
 * 调用用户信息的类型
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public enum UserType {
    /**
     * feign 远程调用
     */
    FEIGN,
    /**
     * Service 本地调用
     */
    SERVICE,
    ;
}
