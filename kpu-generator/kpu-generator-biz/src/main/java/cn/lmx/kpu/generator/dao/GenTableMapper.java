package cn.lmx.kpu.generator.dao;

import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.generator.entity.GenTable;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  15:26
 */
@Repository
public interface GenTableMapper extends SuperMapper<GenTable> {
    @Select("SELECT TABLE_NAME AS name, TABLE_COMMENT AS comment FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DATABASE();")
    List<GenTable> selectTableList();
}
