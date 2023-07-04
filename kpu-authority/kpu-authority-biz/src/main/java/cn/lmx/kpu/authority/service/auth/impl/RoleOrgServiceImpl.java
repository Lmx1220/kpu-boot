package cn.lmx.kpu.authority.service.auth.impl;


import cn.lmx.basic.base.service.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.authority.dao.auth.RoleOrgMapper;
import cn.lmx.kpu.authority.entity.auth.RoleOrg;
import cn.lmx.kpu.authority.service.auth.RoleOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 角色组织关系
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service

public class RoleOrgServiceImpl extends SuperServiceImpl<RoleOrgMapper, RoleOrg> implements RoleOrgService {
    @Override
    public List<Long> listOrgByRoleId(Long roleId) {
        List<RoleOrg> list = super.list(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, roleId));
        return list.stream().mapToLong(RoleOrg::getOrgId).boxed().collect(Collectors.toList());
    }
}
