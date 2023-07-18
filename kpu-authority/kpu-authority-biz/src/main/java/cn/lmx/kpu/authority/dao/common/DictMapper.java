package cn.lmx.kpu.authority.dao.common;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.authority.dto.common.DictPageQuery;
import cn.lmx.kpu.authority.entity.common.Dict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 字典类型
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Repository
public interface DictMapper extends SuperMapper<Dict> {
    /**
     * 分页查询字典类型
     *
     * @param page  分页参数
     * @param query 查询条件
     * @return 分页数据
     */
    IPage<Dict> pageType(IPage<Dict> page, @Param("query") DictPageQuery query);
}