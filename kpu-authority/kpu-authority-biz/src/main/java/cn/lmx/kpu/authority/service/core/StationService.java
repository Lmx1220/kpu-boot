package cn.lmx.kpu.authority.service.core;

import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.basic.interfaces.echo.LoadService;
import cn.lmx.kpu.authority.dto.core.StationPageQuery;
import cn.lmx.kpu.authority.dto.core.StationResultVO;
import cn.lmx.kpu.authority.dto.core.StationSaveVO;
import cn.lmx.kpu.authority.dto.core.StationUpdateVo;
import cn.lmx.kpu.authority.entity.core.Station;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 业务接口
 * 岗位
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface StationService extends SuperCacheService<Long, Station, StationSaveVO, StationUpdateVo, StationPageQuery, StationResultVO>, LoadService {
    /**
     * 按权限查询岗位的分页信息
     *
     * @param page   分页对象
     * @param params 分页参数
     * @return 分页数据
     */
    IPage<Station> findStationPage(IPage<Station> page, PageParams<StationPageQuery> params);

    /**
     * 检测名称是否存在
     *
     * @param id   id
     * @param name name
     * @return boolean
     * @author lmx
     * @date 2023/7/4 14:27
     * @create [2023/7/4 14:27 ] [lmx] [初始创建]
     */
    boolean check(Long id, String name);
}
