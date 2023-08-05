package cn.lmx.kpu.authority.service.common.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.OS;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.basic.utils.DateUtils;
import cn.lmx.kpu.authority.dao.common.LoginLogMapper;
import cn.lmx.kpu.authority.dto.common.LoginLogUpdateVo;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.entity.common.LoginLog;
import cn.lmx.kpu.authority.manager.common.LoginLogManager;
import cn.lmx.kpu.authority.service.auth.UserService;
import cn.lmx.kpu.authority.service.common.LoginLogService;
import cn.lmx.kpu.common.cache.common.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl extends SuperServiceImpl<LoginLogManager, Long, LoginLog, LoginLog, LoginLogUpdateVo, LoginLog, LoginLog> implements LoginLogService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginLog save(Long userId, String username, String ua, String ip, String location, String description, String status) {
        return superManager.save(userId, username, ua, ip, location, description, status);
    }

    @Override
    public void pvIncr() {
        superManager.pvIncr();
    }

    @Override
    public Long getTotalPv() {
        return superManager.getTotalPv();
    }

    @Override
    public Long getTodayPv() {
        return superManager.getTodayPv();
    }

    @Override
    public Long getTotalLoginPv() {
        return superManager.getTotalLoginPv();
    }

    @Override
    public Long getTodayLoginPv() {
        return superManager.getTodayLoginPv();
    }

    @Override
    public Long getTotalLoginIv() {
        return superManager.getTotalLoginIv();
    }

    @Override
    public Long getTodayLoginIv() {
        return superManager.getTodayLoginIv();
    }

    @Override
    public List<Map<String, String>> findLastTenDaysVisitCount(String username) {
        return superManager.findLastTenDaysVisitCount(username);
    }

    @Override
    public List<Map<String, Object>> findByBrowser() {
        return superManager.findByBrowser();
    }

    @Override
    public List<Map<String, Object>> findByOperatingSystem() {
        return superManager.findByOperatingSystem();
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return superManager.clearLog(clearBeforeTime, clearBeforeNum);
    }
}
