package cn.lmx.kpu.system.manager.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.model.cache.CacheKeyBuilder;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.kpu.common.cache.tenant.application.ApplicationCacheKeyBuilder;
import cn.lmx.kpu.system.entity.application.DefApplication;
import cn.lmx.kpu.system.manager.application.DefApplicationManager;
import cn.lmx.kpu.system.mapper.application.DefApplicationMapper;
import cn.lmx.kpu.system.vo.result.application.DefApplicationResultVO;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 应用管理
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 下午
 * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
 */
@RequiredArgsConstructor
@Service
public class DefApplicationManagerImpl extends SuperCacheManagerImpl<DefApplicationMapper, DefApplication> implements DefApplicationManager {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ApplicationCacheKeyBuilder();
    }


    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return CollHelper.uniqueIndex(find(ids), DefApplication::getId, DefApplication::getName);
    }

    public List<DefApplication> find(Set<Serializable> ids) {
//         强转， 防止数据库隐式转换，  若你的id 是string类型，请勿强转
        return findByIds(ids, null).stream().filter(Objects::nonNull).toList();
    }

    @Override
    public List<DefApplicationResultVO> findMyApplication(String name) {
        return baseMapper.findMyApplication(name);
    }

    @Override
    public List<DefApplicationResultVO> findRecommendApplication(String name) {
        return Collections.emptyList();
    }

    @Override
    public List<DefApplication> findGeneral() {
        return list(Wraps.<DefApplication>lbQ().eq(DefApplication::getIsGeneral, true));
    }

}
