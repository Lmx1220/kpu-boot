package cn.lmx.kpu.authority.service.common.impl;

import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.utils.TreeUtil;
import cn.lmx.kpu.authority.dto.common.AreaPageQuery;
import cn.lmx.kpu.authority.dto.common.AreaResultVO;
import cn.lmx.kpu.authority.dto.common.AreaSaveVO;
import cn.lmx.kpu.authority.dto.common.AreaUpdateVO;
import cn.lmx.kpu.authority.entity.common.Area;
import cn.lmx.kpu.authority.manager.common.AreaManager;
import cn.lmx.kpu.authority.service.common.AreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 地区表
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AreaServiceImpl extends SuperCacheServiceImpl<AreaManager, Long, Area, AreaSaveVO, AreaUpdateVO, AreaPageQuery, AreaResultVO> implements AreaService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recursively(List<Long> ids) {
        return superManager.recursively(ids);
    }

    @Override
    public List<Area> findTree(AreaPageQuery query) {
        List<Area> list = superManager.list(Wraps.<Area>lbQ().orderByAsc(Area::getSortValue));
        return TreeUtil.buildTree(list);
    }
}
