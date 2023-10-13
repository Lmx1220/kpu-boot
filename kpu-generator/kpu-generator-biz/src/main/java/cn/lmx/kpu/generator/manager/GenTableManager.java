package cn.lmx.kpu.generator.manager;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.generator.entity.GenTable;
import com.baomidou.mybatisplus.annotation.DbType;

import javax.sql.DataSource;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  15:27
 */
public interface GenTableManager extends SuperManager<GenTable> {


    DataSource getDs(Long dsId);

    DbType getDbType();
}