package cn.lmx.kpu.authority.manager.core;

import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.kpu.authority.dto.core.StationPageQuery;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.entity.core.Station;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * 账号
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface StationManager extends SuperCacheManager<Station> {

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
