package cn.lmx.kpu.authority.service.common.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.cache.repository.CacheOps;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.kpu.authority.dao.common.ParameterMapper;
import cn.lmx.kpu.authority.dto.common.ParameterPageQuery;
import cn.lmx.kpu.authority.dto.common.ParameterResultVO;
import cn.lmx.kpu.authority.dto.common.ParameterSaveVO;
import cn.lmx.kpu.authority.dto.common.ParameterUpdateVo;
import cn.lmx.kpu.authority.entity.common.Parameter;
import cn.lmx.kpu.authority.event.ParameterUpdateEvent;
import cn.lmx.kpu.authority.event.model.ParameterUpdate;
import cn.lmx.kpu.authority.manager.common.ParameterManager;
import cn.lmx.kpu.authority.service.common.ParameterService;
import cn.lmx.kpu.common.cache.common.ParameterKeyCacheKeyBuilder;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

/**
 * <p>
 * 业务实现类
 * 参数配置
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ParameterServiceImpl extends SuperServiceImpl<ParameterManager, Long, Parameter, ParameterSaveVO, ParameterUpdateVo, ParameterPageQuery, ParameterResultVO> implements ParameterService {

    private final CacheOps cacheOps;

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Parameter model) {
        return superManager.save(model);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Parameter model) {
        return superManager.updateById(model);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<?> idList) {
        return superManager.removeByIds(idList);
    }

    @Override
    public String getValue(String key, String defVal) {
        return superManager.getValue(key, defVal);
    }

    @Override
    public Map<String, String> findParams(List<String> keys) {
        return superManager.findParams(keys);
    }
}
