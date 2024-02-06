package cn.lmx.kpu.base.service.user;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.base.entity.user.BasePosition;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 岗位
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface BasePositionService extends SuperService<Long, BasePosition> {
    /**
     * 根据id查询待回显参数
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    Map<Serializable, Object> findByIds(Set<Serializable> ids);

    /**
     * 检测机构名称是否存在
     *
     * @param name  机构名称
     * @param orgId 上级机构id
     * @param id    岗位id
     * @return
     */
    boolean check(String name, Long orgId, Long id);

}
