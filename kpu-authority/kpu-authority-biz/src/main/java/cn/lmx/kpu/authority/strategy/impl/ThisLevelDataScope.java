package cn.lmx.kpu.authority.strategy.impl;

import cn.lmx.kpu.authority.dao.auth.UserMapper;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.strategy.AbstractDataScopeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 本级
 *
 * @author lmx
 * @version 1.0
 * @date 2023/7/4 14:27
 */
@Component("THIS_LEVEL")
@RequiredArgsConstructor
public class ThisLevelDataScope implements AbstractDataScopeHandler {
    private final UserMapper userMapper;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        Long orgId = user.getOrgId();
        return Arrays.asList(orgId);
    }
}
