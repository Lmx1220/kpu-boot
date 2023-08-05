package cn.lmx.kpu.authority.manager.common;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.authority.entity.common.LoginLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 系统日志
 *
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface LoginLogManager extends SuperManager<LoginLog> {


    /**
     * 记录登录日志
     *
     * @param userId      用户id
     * @param username    账号
     * @param ua          浏览器
     * @param ip          客户端IP
     * @param location    客户端地址
     * @param description 登陆描述消息
     * @param status      登陆状态
     * @return 登录日志
     */
    LoginLog save(Long userId, String username, String ua, String ip, String location, String description, String status);

    /**
     * 获取系统总 登录次数
     *
     * @return Long
     */
    Long getTotalLoginPv();

    /**
     * 获取系统今日 登录次数
     *
     * @return Long
     */
    Long getTodayLoginPv();

    /**
     * 获取系统总 登录IV
     *
     * @return Long
     */
    Long getTotalLoginIv();

    /**
     * 获取系统今日 登录IV
     *
     * @return Long
     */
    Long getTodayLoginIv();


    /**
     * 获取系统近十天来的访问记录
     *
     * @param username 账号
     * @return 系统近十天来的访问记录
     */
    List<Map<String, String>> findLastTenDaysVisitCount(String username);


    /**
     * 按浏览器来统计数量
     *
     * @return 浏览器访问量
     */
    List<Map<String, Object>> findByBrowser();

    /**
     * 按操作系统来统计数量
     *
     * @return 操作系统访问量
     */
    List<Map<String, Object>> findByOperatingSystem();

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);

    /**
     * 总 PV
     *
     * @return
     */
    Long getTotalPv();

    /**
     * 今日 PV
     *
     * @return
     */
    Long getTodayPv();

    /**
     * pv 计数
     */
    void pvIncr();
}
