package cn.lmx.kpu.authority.service.core.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.kpu.authority.dao.auth.UserMapper;
import cn.lmx.kpu.authority.dao.core.OrgMapper;
import cn.lmx.kpu.authority.dto.core.OrgPageQuery;
import cn.lmx.kpu.authority.dto.core.OrgPageResultVO;
import cn.lmx.kpu.authority.dto.core.OrgSaveVO;
import cn.lmx.kpu.authority.dto.core.OrgUpdateVo;
import cn.lmx.kpu.authority.entity.auth.RoleOrg;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.entity.core.Org;
import cn.lmx.kpu.authority.manager.core.OrgManager;
import cn.lmx.kpu.authority.service.auth.RoleOrgService;
import cn.lmx.kpu.authority.service.core.OrgService;
import cn.lmx.kpu.common.cache.core.OrgCacheKeyBuilder;
import cn.lmx.kpu.common.constant.DefValConstants;
import cn.lmx.kpu.model.enumeration.base.OrgTypeEnum;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 组织
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrgServiceImpl extends SuperCacheServiceImpl<OrgManager, Long, Org, OrgSaveVO, OrgUpdateVo, OrgPageQuery, OrgPageResultVO> implements OrgService {
    private final RoleOrgService roleOrgService;
    private final UserMapper userMapper;

    @Override
    public boolean check(Long id, String name) {
        return superManager.check(id, name);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Org model) {
        ArgumentAssert.isFalse(check(null, model.getName()), StrUtil.format("组织[{}]已经存在", model.getName()));
        return superManager.save(model);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Org model) {
        ArgumentAssert.isFalse(check(model.getId(), model.getName()), StrUtil.format("组织[{}]已经存在", model.getName()));
        return superManager.updateById(model);
    }

    @Override
    public List<Org> findChildren(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // MySQL 全文索引
        String applySql = String.format(" MATCH(tree_path) AGAINST('%s' IN BOOLEAN MODE) ", CollUtil.join(ids, " "));

        return super.list(Wraps.<Org>lbQ().in(Org::getId, ids).or(query -> query.apply(applySql)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        Long userCount = userMapper.selectCount(Wraps.<User>lbQ().in(User::getOrgId, ids));
        ArgumentAssert.isFalse(userCount > 0, "您选择的组织下还存在用户，禁止删除！请先情况改组织下所有用户后，在进行删除！");

        List<Org> list = this.findChildren(ids);
        List<Long> idList = list.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());

        boolean bool = superManager.removeByIds(idList);

        // 删除自定义类型的数据权限范围
        roleOrgService.remove(Wraps.<RoleOrg>lbQ().in(RoleOrg::getOrgId, idList));
        return bool;
    }

    private List<Org> findOrg(Set<Serializable> ids) {
        return findByIds(ids,
                missIds -> super.listByIds(missIds.stream().filter(Objects::nonNull).map(Convert::toLong).collect(Collectors.toList()))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return CollHelper.uniqueIndex(findOrg(ids).stream().filter(Objects::nonNull).collect(Collectors.toList()), Org::getId, org -> org);
    }


    @Override
    public Long getMainDeptIdByUserId(Long userId) {
        return superManager.getMainDeptIdByUserId(userId);
    }

    @Override
    public List<Long> findDeptAndChildrenIdByUserId(Long userId) {
        return superManager.findDeptAndChildrenIdByUserId(userId);
    }

    @Override
    public Long getMainCompanyIdByUserId(Long userId) {
        Org mainCompany = getMainCompanyByUserId(userId);
        return mainCompany != null ? mainCompany.getId() : null;
    }

    @Override
    public Org getMainCompanyByUserId(Long userId) {
        // 用户所在部门
        return superManager.getMainCompanyByUserId(userId);
    }


    @Override
    public List<Long> findCompanyAndChildrenIdByUserId(Long userId) {
        return superManager.findCompanyAndChildrenIdByUserId(userId);
    }

}
