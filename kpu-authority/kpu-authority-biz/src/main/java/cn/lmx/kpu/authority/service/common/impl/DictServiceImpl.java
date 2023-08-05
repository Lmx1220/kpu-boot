package cn.lmx.kpu.authority.service.common.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.cache.repository.CachePlusOps;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.echo.properties.EchoProperties;
import cn.lmx.basic.model.cache.CacheHashKey;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.kpu.authority.dao.common.DictMapper;
import cn.lmx.kpu.authority.dto.common.DictPageQuery;
import cn.lmx.kpu.authority.dto.common.DictResultVO;
import cn.lmx.kpu.authority.dto.common.DictSaveVO;
import cn.lmx.kpu.authority.dto.common.DictUpdateVo;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.authority.manager.common.DictManager;
import cn.lmx.kpu.authority.service.common.DictService;
import cn.lmx.kpu.common.cache.common.DictItemCacheKeyBuilder;
import cn.lmx.kpu.common.constant.DefValConstants;
import cn.lmx.kpu.model.vo.query.CodeQueryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 业务实现类
 * 字典类型
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl extends SuperServiceImpl<DictManager, Long, Dict, DictSaveVO, DictUpdateVo, DictPageQuery, DictResultVO> implements DictService {
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Dict model) {
        return superManager.save(model);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllById(Dict model) {
        return superManager.updateAllById(model);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Dict model) {
        return superManager.updateById(model);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        return superManager.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<?> idList) {
        return superManager.removeByIds(idList);
    }


    @Override
    public Map<String, List<Dict>> listByItem(CodeQueryVO[] types) {
        return superManager.listByItem(types);
    }


    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> types) {
        return superManager.findByIds(types);
    }

}
