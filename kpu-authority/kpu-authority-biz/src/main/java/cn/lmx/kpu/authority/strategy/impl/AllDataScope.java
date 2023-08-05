package cn.lmx.kpu.authority.strategy.impl;

import cn.lmx.kpu.authority.entity.core.Org;
import cn.lmx.kpu.authority.manager.core.OrgManager;
import cn.lmx.kpu.authority.service.core.OrgService;
import cn.lmx.kpu.authority.strategy.AbstractDataScopeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 所有
 *
 * @author lmx
 * @version 1.0
 * @date 2023/7/4 14:27
 */
@Component("ALL")
@RequiredArgsConstructor
public class AllDataScope implements AbstractDataScopeHandler {

    private final OrgManager orgManager;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        List<Org> list = orgManager.lambdaQuery().select(Org::getId).list();
        return list.stream().map(Org::getId).collect(Collectors.toList());
    }


}
