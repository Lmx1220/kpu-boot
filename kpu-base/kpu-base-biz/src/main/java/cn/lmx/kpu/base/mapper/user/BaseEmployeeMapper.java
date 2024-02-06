package cn.lmx.kpu.base.mapper.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.base.entity.user.BaseEmployee;
import cn.lmx.kpu.base.vo.result.user.BaseEmployeeResultVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 员工
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
public interface BaseEmployeeMapper extends SuperMapper<BaseEmployee> {
    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param wrapper 查询条件
     * @return 分页用户数据
     */
    IPage<BaseEmployeeResultVO> selectPageResultVO(IPage<BaseEmployee> page, @Param(Constants.WRAPPER) Wrapper<BaseEmployee> wrapper);

    /**
     * 根据用户id查询员工
     *
     * @param userId 用户id
     * @return
     */
    List<BaseEmployeeResultVO> listEmployeeByUserId(@Param("userId") Long userId);
}
