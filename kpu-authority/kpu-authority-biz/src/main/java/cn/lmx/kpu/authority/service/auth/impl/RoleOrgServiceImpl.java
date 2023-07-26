package cn.lmx.kpu.authority.service.auth.impl;


import cn.lmx.basic.base.service.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.authority.dao.auth.RoleOrgMapper;
import cn.lmx.kpu.authority.dto.auth.RoleOrgSaveVO;
import cn.lmx.kpu.authority.entity.auth.RoleOrg;
import cn.lmx.kpu.authority.service.auth.RoleOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Long> findRoleByOrgId(Long orgId) {
        List<RoleOrg> list = super.list(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getOrgId, orgId));
        return list.stream().mapToLong(RoleOrg::getRoleId).boxed().collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> saveRoleOrg(RoleOrgSaveVO saveVO) {
        ArgumentAssert.notEmpty(saveVO.getRoleIdList(), "请选择角色");
        if (saveVO.getFlag() == null) {
            saveVO.setFlag(true);
        }

        this.remove(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getOrgId, saveVO.getOrgId()).in(RoleOrg::getRoleId, saveVO.getRoleIdList()));
        if (saveVO.getFlag()) {
            List<RoleOrg> list = saveVO.getRoleIdList().stream().map(roleId ->
                    RoleOrg.builder().roleId(roleId).orgId(saveVO.getOrgId()).build()).collect(Collectors.toList());
            this.saveBatch(list);
        }
        return findRoleByOrgId(saveVO.getOrgId());
    }
}
