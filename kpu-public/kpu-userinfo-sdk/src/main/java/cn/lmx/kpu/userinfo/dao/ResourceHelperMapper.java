package cn.lmx.kpu.userinfo.dao;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.model.entity.base.SysResource;
import cn.lmx.kpu.model.vo.query.ResourceQueryDTO;
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
public interface ResourceHelperMapper extends SuperMapper<SysResource> {

    /**
     * 查询 拥有的资源
     *
     * @param resource 查询参数
     * @return 可用资源
     */
    List<SysResource> findVisibleAuth(ResourceQueryDTO resource);
}
