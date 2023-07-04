package cn.lmx.kpu.userinfo.dao;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.model.entity.base.SysMenu;
import cn.lmx.kpu.model.vo.query.MenuQueryDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源操作类
 *
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Repository
public interface MenuHelperMapper extends SuperMapper<SysMenu> {

    /**
     * 查询 拥有的资源
     *
     * @param resource 查询参数
     * @return 可用资源
     */
    List<SysMenu> findVisibleResource(MenuQueryDTO resource);
}
