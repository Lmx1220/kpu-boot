package cn.lmx.kpu.authority.strategy.impl;

import cn.lmx.kpu.authority.dao.auth.UserMapper;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.entity.core.Org;
import cn.lmx.kpu.authority.service.core.OrgService;
import cn.lmx.kpu.authority.strategy.AbstractDataScopeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 本级以及子级
 *
 * @author lmx
 * @version 1.0
 * @date 2023/7/4 14:27
 */
@Component("THIS_LEVEL_CHILDREN")
@RequiredArgsConstructor
public class ThisLevelChildrenDataScope implements AbstractDataScopeHandler {
    private final UserMapper userMapper;
    private final OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        Long orgId = user.getOrgId();
        List<Org> children = orgService.findChildren(Arrays.asList(orgId));
        return children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
    }

}
