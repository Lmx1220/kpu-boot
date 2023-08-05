package cn.lmx.kpu.authority.service.common.impl;

import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.authority.dto.common.*;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.authority.manager.common.DictManager;
import cn.lmx.kpu.authority.service.common.DictItemService;
import cn.lmx.kpu.authority.service.common.DictService;
import cn.lmx.kpu.model.vo.query.CodeQueryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictItemServiceImpl extends SuperServiceImpl<DictManager, Long, Dict, DictItemSaveVO, DictItemUpdateVO, DictItemPageQuery, DictItemResultVO> implements DictItemService {
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
    @Transactional(rollbackFor = Exception.class)
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
