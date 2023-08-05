package cn.lmx.kpu.authority.service.core.impl;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.annotation.echo.EchoResult;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.kpu.authority.dto.core.StationPageQuery;
import cn.lmx.kpu.authority.dto.core.StationResultVO;
import cn.lmx.kpu.authority.dto.core.StationSaveVO;
import cn.lmx.kpu.authority.dto.core.StationUpdateVo;
import cn.lmx.kpu.authority.entity.core.Station;
import cn.lmx.kpu.authority.manager.core.StationManager;
import cn.lmx.kpu.authority.service.core.StationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 岗位
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StationServiceImpl extends SuperCacheServiceImpl<StationManager, Long, Station, StationSaveVO, StationUpdateVo, StationPageQuery, StationResultVO> implements StationService {
    @Override
    public boolean check(Long id, String name) {
        return superManager.check(id, name);
    }


    @Override
    @EchoResult
    public IPage<Station> findStationPage(IPage<Station> page, PageParams<StationPageQuery> params) {
        return superManager.findStationPage(page, params);
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return CollHelper.uniqueIndex(findStation(ids), Station::getId, Station::getName);
    }

    private List<Station> findStation(Set<Serializable> ids) {
        // 强转， 防止数据库隐式转换，  若你的id 是string类型，请勿强转
        return findByIds(ids,
                missIds -> super.listByIds(missIds.stream().filter(Objects::nonNull).map(Convert::toLong).collect(Collectors.toList()))
        );
    }

}
