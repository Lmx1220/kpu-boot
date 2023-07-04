package cn.lmx.kpu.authority.dao.core;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.authority.entity.core.Org;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 组织
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
public interface OrgMapper extends SuperMapper<Org> {
    /**
     * 根据 用户ID 查找 部门ID
     *
     * @param userId 用户ID
     * @return
     */
    Org getDeptByUserId(@Param("userId") Long userId);
}
