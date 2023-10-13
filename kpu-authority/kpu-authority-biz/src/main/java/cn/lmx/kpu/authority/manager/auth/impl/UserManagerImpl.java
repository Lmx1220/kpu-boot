package cn.lmx.kpu.authority.manager.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.authority.dao.auth.UserMapper;
import cn.lmx.kpu.authority.dto.auth.UserUpdateAvatarDTO;
import cn.lmx.kpu.authority.dto.auth.UserUpdatePasswordDTO;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.manager.auth.UserManager;
import cn.lmx.kpu.authority.manager.auth.UserRoleManager;
import cn.lmx.kpu.common.cache.auth.UserCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserResourceCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserRoleCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.UserUsernameCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.lmx.kpu.common.constant.BizConstant.DEF_PASSWORD;


/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/26  19:06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserManagerImpl extends SuperCacheManagerImpl<UserMapper, User> implements UserManager {
    private final UserRoleManager userRoleManager;

    @Override
    public CacheKeyBuilder cacheKeyBuilder() {
        return new UserCacheKeyBuilder();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updatePassword(UserUpdatePasswordDTO data) {
        ArgumentAssert.notEmpty(data.getOldPassword(), "当前密码不能为空");
        User user = getById(data.getId());
        ArgumentAssert.notNull(user, "用户不存在");
        ArgumentAssert.equals(user.getId(), ContextUtil.getUserId(), "只能修改自己的密码");
        String oldPassword = SecureUtil.sha256(data.getOldPassword() + user.getSalt());
        ArgumentAssert.equals(user.getPassword(), oldPassword, "旧密码错误");

        return reset(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resetPassErrorNum(Long id) {
        int count = baseMapper.resetPassErrorNum(id, LocalDateTime.now());
        delCache(id);
        return count;
    }

    @Override
    public List<Long> findAllUserId() {
        return null;
    }

    @Override
    public boolean initUser(User user) {
        return false;
    }

    @Override
    public List<User> findUser(Set<Serializable> ids) {
        // 强转， 防止数据库隐式转换，  若你的id 是string类型，请勿强转
        return findByIds(ids,
                missIds -> super.listByIds(missIds.stream().filter(Objects::nonNull).map(Convert::toLong).collect(Collectors.toList()))
        );
    }


    @Override
    public List<User> findUserById(List<Long> ids) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Long todayUserCount() {
        return count(Wraps.<User>lbQ().leFooter(User::getCreatedTime, LocalDateTime.now()).geHeader(User::getCreatedTime, LocalDateTime.now()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reset(UserUpdatePasswordDTO data) {
        ArgumentAssert.equals(data.getConfirmPassword(), data.getPassword(), "密码和重复密码不一致");
        User user = getById(data.getId());
        ArgumentAssert.notNull(user, "用户不存在");
        String defPassword = SecureUtil.sha256(data.getPassword() + user.getSalt());
        super.update(Wraps.<User>lbU()
                .set(User::getPassword, defPassword)
                .set(User::getPasswordErrorNum, 0L)
                // 置空
                .set(User::getPasswordErrorLastTime, null)
                .eq(User::getId, data.getId())
        );
        delCache(data.getId());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User user) {
        // 不允许修改用户信息时修改密码，请单独调用修改密码接口
        user.setPassword(null);
        user.setSalt(null);
        updateById(user);
        return user;
    }

    @Override
    public boolean remove(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        userRoleManager.remove(Wraps.<UserRole>lbQ().in(UserRole::getUserId, ids));
        cacheOps.del(ids.stream().map(new UserRoleCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        cacheOps.del(ids.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        return removeByIds(ids);
    }

    @Override
    public User getByUsername(String username) {
        Function<CacheKey, Object> loader = k -> getObj(Wraps.<User>lbQ().select(User::getId).eq(User::getUsername, username), Convert::toLong);
        CacheKeyBuilder builder = new UserUsernameCacheKeyBuilder();
        return getByKey(builder.key(username), loader);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User saveUser(User user) {
        ArgumentAssert.isFalse(check(null, user.getUsername()), "账号{}已经存在", user.getUsername());
        user.setSalt(RandomUtil.randomString(20));
        if (StrUtil.isEmpty(user.getPassword())) {
            user.setPassword(DEF_PASSWORD);
        }
        user.setPassword(SecureUtil.sha256(user.getPassword() + user.getSalt()));
        user.setPasswordErrorNum(0);
        super.save(user);
        CacheKeyBuilder builder = new UserUsernameCacheKeyBuilder();
        cacheOps.del(builder.key(user.getUsername()));
        return user;
    }

    @Override
    public Boolean updateAvatar(UserUpdateAvatarDTO data) {
        return null;
    }

    @Override
    public Map<String, Object> getDataScopeById(Long userId) {
        return null;
    }

    @Override
    public boolean check(Long id, String username) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incrPasswordErrorNumById(Long id) {
        baseMapper.incrPasswordErrorNumById(id, LocalDateTime.now());
        delCache(id);
    }
}
