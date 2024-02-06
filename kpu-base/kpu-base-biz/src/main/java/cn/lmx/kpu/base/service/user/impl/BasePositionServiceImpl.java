package cn.lmx.kpu.base.service.user.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbQueryWrap;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.base.entity.user.BasePosition;
import cn.lmx.kpu.base.manager.user.BasePositionManager;
import cn.lmx.kpu.base.service.user.BasePositionService;
import cn.lmx.kpu.base.vo.save.user.BasePositionSaveVO;
import cn.lmx.kpu.base.vo.update.user.BasePositionUpdateVO;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 岗位
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BasePositionServiceImpl extends SuperServiceImpl<BasePositionManager, Long, BasePosition> implements BasePositionService {

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return superManager.findByIds(ids.stream().map(Convert::toLong).collect(Collectors.toSet()));
    }

    @Override
    public boolean check(String name, Long orgId, Long id) {
        ArgumentAssert.notEmpty(name, "请填写名称");
        LbQueryWrap<BasePosition> wrap = Wraps.<BasePosition>lbQ().eq(BasePosition::getName, name).eq(BasePosition::getOrgId, orgId).ne(BasePosition::getId, id);
        return superManager.count(wrap) > 0;
    }

    @Override
    protected <SaveVO> BasePosition saveBefore(SaveVO saveVO) {
        BasePositionSaveVO positionSaveVO = (BasePositionSaveVO) saveVO;
        ArgumentAssert.isFalse(check(positionSaveVO.getName(), positionSaveVO.getOrgId(), null), StrUtil.format("岗位[{}]已经存在", positionSaveVO.getName()));
        return super.saveBefore(saveVO);
    }

    @Override
    protected <UpdateVO> BasePosition updateBefore(UpdateVO updateVO) {
        BasePositionUpdateVO positionUpdateVO = (BasePositionUpdateVO) updateVO;
        ArgumentAssert.isFalse(check(positionUpdateVO.getName(), positionUpdateVO.getOrgId(), positionUpdateVO.getId()), StrUtil.format("岗位[{}]已经存在", positionUpdateVO.getName()));
        return super.updateBefore(updateVO);
    }
}
