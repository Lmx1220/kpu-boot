package cn.lmx.kpu.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.kpu.authority.dao.auth.ApplicationMapper;
import cn.lmx.kpu.authority.dto.auth.ApplicationPageQuery;
import cn.lmx.kpu.authority.dto.auth.ApplicationResultVO;
import cn.lmx.kpu.authority.dto.auth.ApplicationSaveVO;
import cn.lmx.kpu.authority.dto.auth.ApplicationUpdateVO;
import cn.lmx.kpu.authority.entity.auth.Application;
import cn.lmx.kpu.authority.manager.auth.ApplicationManager;
import cn.lmx.kpu.authority.service.auth.ApplicationService;
import cn.lmx.kpu.common.cache.auth.ApplicationCacheKeyBuilder;
import cn.lmx.kpu.common.cache.auth.ApplicationClientCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

/**
 * <p>
 * 业务实现类
 * 应用
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ApplicationServiceImpl extends SuperCacheServiceImpl<ApplicationManager, Long, Application, ApplicationSaveVO, ApplicationUpdateVO, ApplicationPageQuery, ApplicationResultVO> implements ApplicationService {


    @Override
    public Application getByClient(String clientId, String clientSecret) {

        return superManager.getByClient(clientId, clientSecret);
    }

}
