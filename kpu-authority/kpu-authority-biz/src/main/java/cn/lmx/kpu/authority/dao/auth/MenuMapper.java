package cn.lmx.kpu.authority.dao.auth;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.authority.entity.auth.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 菜单
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
public interface MenuMapper extends SuperMapper<Menu> {

    /**
     * 查询用户可用菜单
     *
     * @param userId 用户id
     * @return 菜单
     */
    List<Menu> findVisibleMenu(@Param("userId") Long userId);

}
