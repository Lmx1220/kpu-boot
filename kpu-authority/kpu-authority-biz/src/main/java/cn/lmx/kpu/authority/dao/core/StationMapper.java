package cn.lmx.kpu.authority.dao.core;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.authority.entity.core.Station;
import cn.lmx.kpu.common.annotation.DataField;
import cn.lmx.kpu.common.annotation.DataScope;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 岗位
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
public interface StationMapper extends SuperMapper<Station> {
    /**
     * 分页查询岗位信息（含角色）
     *
     * @param page         分页
     * @param queryWrapper 参数包装器
     * @return 分页数据
     */
    @DataScope(value = {@DataField(alias = "s")})
    IPage<Station> findStationPage(IPage<Station> page, @Param(Constants.WRAPPER) Wrapper<Station> queryWrapper);
}
