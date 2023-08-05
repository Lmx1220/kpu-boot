package cn.lmx.kpu.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.base.request.PageUtil;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.kpu.authority.dao.auth.UserMapper;
import cn.lmx.kpu.authority.dto.auth.*;
import cn.lmx.kpu.authority.entity.auth.Role;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.manager.auth.UserManager;
import cn.lmx.kpu.authority.service.auth.RoleOrgService;
import cn.lmx.kpu.authority.service.auth.RoleService;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import cn.lmx.kpu.authority.service.auth.UserService;
import cn.lmx.kpu.authority.service.core.OrgService;
import cn.lmx.kpu.authority.service.core.StationService;
import cn.lmx.kpu.common.cache.auth.UserCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserRoleCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserUsernameCacheKeyBuilder;
import cn.lmx.kpu.common.constant.BizConstant;
import cn.lmx.kpu.file.service.AppendixService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.lmx.kpu.common.constant.BizConstant.DEF_PASSWORD;


/**
 * <p>
 * 业务实现类
 * 账号
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends SuperCacheServiceImpl<UserManager, Long, User, UserSaveVO, UserUpdateVo, UserPageQuery, UserResultVO> implements UserService {

    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final RoleOrgService roleOrgService;
    private final OrgService orgService;
    private final AppendixService appendixService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updatePassword(UserUpdatePasswordDTO data) {
        return superManager.updatePassword(data);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reset(UserUpdatePasswordDTO data) {
        return superManager.reset(data);
    }

    @Override
    public User getByUsername(String username) {
        return superManager.getByUsername(username);
    }


    @Override
    public Map<String, Object> getDataScopeById(Long userId) {
        Map<String, Object> map = new HashMap<>(4);
        List<Long> orgIds = new ArrayList<>();

        List<Role> list = roleService.findRoleByUserId(userId);

        // 找到 dsType 最大的角色， dsType越大，角色拥有的权限最大
//        Optional<Role> max = list.stream().max(Comparator.comparingInt(item -> item.getDsType().getVal()));

//        DataScopeType dsType;
//        if (max.isPresent()) {
//            Role role = max.get();
//            dsType = role.getDsType();
//            map.put("dsType", dsType.getVal());
//            if (DataScopeType.CUSTOMIZE.eq(dsType)) {
//                LbqWrapper<RoleOrg> wrapper = Wraps.<RoleOrg>lbQ().select(RoleOrg::getOrgId).eq(RoleOrg::getRoleId, role.getId());
//                List<RoleOrg> roleOrgList = roleOrgService.list(wrapper);
//
//                orgIds = roleOrgList.stream().mapToLong(RoleOrg::getOrgId).boxed().collect(Collectors.toList());
//            } else if (DataScopeType.THIS_LEVEL.eq(dsType)) {
//                User user = getByIdCache(userId);
//                if (user != null) {
//                    Long orgId = user.getOrgId();
//                    if (orgId != null) {
//                        orgIds.add(orgId);
//                    }
//                }
//            } else if (DataScopeType.THIS_LEVEL_CHILDREN.eq(dsType)) {
//                User user = getByIdCache(userId);
//                if (user != null) {
//                    Long orgId = user.getOrgId();
//                    List<Org> orgList = orgService.findChildren(CollUtil.newArrayList(orgId));
//                    orgIds = orgList.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
//                }
//            }
//        }
        map.put("orgIds", orgIds);
        return map;
    }

    @Override
    public boolean check(Long id, String username) {
        //这里不能用缓存，否则会导致用户无法登录
        return superManager.count(Wraps.<User>lbQ().eq(User::getUsername, username).ne(User::getId, id)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incrPasswordErrorNumById(Long id) {
        superManager.incrPasswordErrorNumById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resetPassErrorNum(Long id) {
        return superManager.resetPassErrorNum(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User saveUser(User user) {
        return superManager.saveUser(user);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User user) {
        return superManager.updateUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(List<Long> ids) {
        return superManager.remove(ids);
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return CollHelper.uniqueIndex(superManager.findUser(ids), User::getId, User::getNickName);
    }


    @Override
    public List<User> findUserById(List<Long> ids) {
        return superManager.findUser(new HashSet<>(ids));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findAllUserId() {
        return getSuperManager().listObjs(Wraps.<User>lbQ().select(User::getId), Convert::toLong);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initUser(User user) {
        ArgumentAssert.isFalse(check(null, user.getUsername()), "账号{}已经存在", user.getUsername());
        this.saveUser(user);
        return userRoleService.initAdmin(user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Long todayUserCount() {
        return superManager.todayUserCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateAvatar(UserUpdateAvatarDTO data) {
        ArgumentAssert.isFalse(StrUtil.isEmpty(data.getAvatar()) && data.getAppendixAvatar() == null, "请上传或选择头像");
        if (StrUtil.isNotEmpty(data.getAvatar())) {
            return superManager.updateById(User.builder().avatar(data.getAvatar()).id(data.getId()).build());
        }
        return appendixService.save(data.getId(), data.getAppendixAvatar());
    }
}
