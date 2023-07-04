package cn.lmx.kpu.authority.service.auth;

import cn.lmx.kpu.authority.dto.auth.Online;

import java.util.List;

/**
 * <p>
 * 业务接口
 * token
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface OnlineService {
    /**
     * 重置用户登录
     *
     * @return 是否成功
     */
    boolean reset();

    /**
     * 查询在线用户
     *
     * @param name 姓名
     * @return
     */
    List<Online> list(String name);

    /**
     * 保存在线用户
     *
     * @param model
     * @return
     */
    boolean save(Online model);

    /**
     * 清理在线用户
     *
     * @param token
     * @param userId
     * @param clientId
     * @return
     */
    boolean clear(String token, Long userId, String clientId);
}
