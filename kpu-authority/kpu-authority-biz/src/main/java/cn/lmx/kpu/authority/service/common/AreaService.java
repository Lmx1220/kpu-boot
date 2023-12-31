package cn.lmx.kpu.authority.service.common;

import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.kpu.authority.dto.common.AreaResultVO;
import cn.lmx.kpu.authority.dto.common.AreaPageQuery;
import cn.lmx.kpu.authority.dto.common.AreaSaveVO;
import cn.lmx.kpu.authority.dto.common.AreaUpdateVO;
import cn.lmx.kpu.authority.entity.common.Area;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 地区表
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface AreaService extends SuperCacheService<Long, Area, AreaSaveVO, AreaUpdateVO, AreaPageQuery, AreaResultVO> {

    /**
     * 递归删除
     *
     * @param ids 地区id
     * @return 是否成功
     */
    boolean recursively(List<Long> ids);
    /**
     * 查询树结构
     *
     * @param query 参数
     * @return 树
     */
    List<Area> findTree(AreaPageQuery query);
}
