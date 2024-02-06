package cn.lmx.kpu.base.service.system.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Multimap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbQueryWrap;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.basic.utils.StrHelper;
import cn.lmx.kpu.base.entity.system.BaseRole;
import cn.lmx.kpu.base.entity.system.BaseRoleResourceRel;
import cn.lmx.kpu.base.entity.user.BaseEmployeeRoleRel;
import cn.lmx.kpu.base.manager.system.BaseRoleManager;
import cn.lmx.kpu.base.manager.system.BaseRoleResourceRelManager;
import cn.lmx.kpu.base.manager.user.BaseEmployeeRoleRelManager;
import cn.lmx.kpu.base.manager.user.BaseOrgRoleRelManager;
import cn.lmx.kpu.base.service.system.BaseRoleService;
import cn.lmx.kpu.base.vo.save.system.BaseRoleResourceRelSaveVO;
import cn.lmx.kpu.base.vo.save.system.BaseRoleSaveVO;
import cn.lmx.kpu.base.vo.save.system.RoleEmployeeSaveVO;
import cn.lmx.kpu.base.vo.update.system.BaseRoleUpdateVO;
import cn.lmx.kpu.common.cache.base.system.RoleResourceCacheKeyBuilder;
import cn.lmx.kpu.common.cache.base.user.EmployeeRoleCacheKeyBuilder;
import cn.lmx.kpu.model.enumeration.base.RoleCategoryEnum;
import cn.lmx.kpu.model.enumeration.system.DataTypeEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务实现类
 * 角色
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseRoleServiceImpl extends SuperCacheServiceImpl<BaseRoleManager, Long, BaseRole> implements BaseRoleService {
    private final BaseEmployeeRoleRelManager baseEmployeeRoleRelManager;
    private final BaseRoleResourceRelManager baseRoleResourceRelManager;
    private final BaseOrgRoleRelManager baseOrgRoleRelManager;

    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写角色编码");
        LbQueryWrap<BaseRole> wrap = Wraps.<BaseRole>lbQ()
                .eq(BaseRole::getCode, code).ne(BaseRole::getId, id);
        return superManager.count(wrap) > 0;
    }

    @Override
    protected <SaveVO> BaseRole saveBefore(SaveVO saveVO) {
        BaseRoleSaveVO data = (BaseRoleSaveVO) saveVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(data.getCode()) &&
                check(data.getCode(), null), "角色编码{}已存在", data.getCode());
        BaseRole baseRole = super.saveBefore(data);
        baseRole.setCode(StrHelper.getOrDef(data.getCode(), RandomUtil.randomString(8)));
        baseRole.setType(DataTypeEnum.BUSINESS.getCode());
        baseRole.setReadonly(false);
        return baseRole;
    }

    @Override
    protected <UpdateVO> BaseRole updateBefore(UpdateVO updateVO) {
        BaseRoleUpdateVO data = (BaseRoleUpdateVO) updateVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(data.getCode()) &&
                check(data.getCode(), data.getId()), "角色编码{}已存在", data.getCode());
        BaseRole baseRole = super.updateBefore(data);
        baseRole.setCode(StrHelper.getOrDef(data.getCode(), RandomUtil.randomString(8)));
        baseRole.setReadonly(false);
        return baseRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<Long> idList) {
        if (idList.isEmpty()) {
            return true;
        }
        // 员工的角色
        baseEmployeeRoleRelManager.deleteByRole(idList);
        // 组织的角色
        baseOrgRoleRelManager.deleteByRole(idList);
        // 角色的资源
        baseRoleResourceRelManager.deleteByRole(idList);
        return superManager.removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> saveRoleEmployee(RoleEmployeeSaveVO saveVO) {
        if (saveVO.getFlag() == null) {
            saveVO.setFlag(true);
        }

        baseEmployeeRoleRelManager.remove(Wraps.<BaseEmployeeRoleRel>lbQ().eq(BaseEmployeeRoleRel::getRoleId, saveVO.getRoleId())
                .in(BaseEmployeeRoleRel::getEmployeeId, saveVO.getEmployeeIdList()));
        if (saveVO.getFlag()) {
            List<BaseEmployeeRoleRel> list = saveVO.getEmployeeIdList().stream().map(employeeId ->
                    BaseEmployeeRoleRel.builder().employeeId(employeeId).roleId(saveVO.getRoleId()).build()).toList();
            baseEmployeeRoleRelManager.saveBatch(list);
        }

        CacheKey[] cacheKeys = saveVO.getEmployeeIdList().stream().map(EmployeeRoleCacheKeyBuilder::build).toArray(CacheKey[]::new);
        cacheOps.del(cacheKeys);
        return findEmployeeIdByRoleId(saveVO.getRoleId());
    }

    @Override
    public List<Long> findEmployeeIdByRoleId(Long roleId) {
        return baseEmployeeRoleRelManager.listObjs(Wraps.<BaseEmployeeRoleRel>lbQ()
                        .select(BaseEmployeeRoleRel::getEmployeeId).eq(BaseEmployeeRoleRel::getRoleId, roleId),
                Convert::toLong);
    }

    @Override
    public Map<Long, Collection<Long>> findResourceIdByRoleId(Long roleId, RoleCategoryEnum category) {
        if (category == null) {
            category = RoleCategoryEnum.FUNCTION;
        }
        List<BaseRoleResourceRel> list = baseRoleResourceRelManager.findByRoleIdAndCategory(roleId, category.getCode());
        Multimap<Long, Long> map = CollHelper.iterableToMultiMap(list, BaseRoleResourceRel::getApplicationId, BaseRoleResourceRel::getResourceId);
        return map.asMap();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveRoleResource(BaseRoleResourceRelSaveVO saveVO) {
        Map<Long, List<Long>> applicationResourceMap = saveVO.getApplicationResourceMap();
        baseRoleResourceRelManager.remove(Wraps.<BaseRoleResourceRel>lbQ().eq(BaseRoleResourceRel::getRoleId, saveVO.getRoleId()));
        if (CollUtil.isEmpty(applicationResourceMap)) {
            return false;
        }

        List<BaseRoleResourceRel> list = new ArrayList<>();
        List<CacheKey> keys = new ArrayList<>();
        applicationResourceMap.forEach((applicationId, resourceList) -> {
            if (CollUtil.isNotEmpty(resourceList)) {
                for (Long resourceId : resourceList) {
                    BaseRoleResourceRel baseRoleResRel = new BaseRoleResourceRel();
                    baseRoleResRel.setRoleId(saveVO.getRoleId());
                    baseRoleResRel.setResourceId(resourceId);
                    baseRoleResRel.setApplicationId(applicationId);
                    list.add(baseRoleResRel);
                }
            }
            keys.add(RoleResourceCacheKeyBuilder.build(applicationId, saveVO.getRoleId()));
        });
        boolean flag = baseRoleResourceRelManager.saveBatch(list);

        cacheOps.del(keys);
        return flag;
    }

    @Override
    public List<Long> findResourceIdByEmployeeId(Long applicationId, Long employeeId) {
        return superManager.findResourceIdByEmployeeId(applicationId, employeeId);
    }

    @Override
    public boolean checkRole(Long employeeId, String... codes) {
        return superManager.checkRole(employeeId, codes);
    }

    @Override
    public List<String> findRoleCodeByEmployeeId(Long employeeId) {
        List<BaseRole> list = superManager.findRoleByEmployeeId(employeeId);
        return list.stream().map(BaseRole::getCode).toList();
    }
}
