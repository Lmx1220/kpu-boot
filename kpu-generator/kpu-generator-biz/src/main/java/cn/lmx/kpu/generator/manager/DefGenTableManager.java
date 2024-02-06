package cn.lmx.kpu.generator.manager;

import com.baomidou.mybatisplus.annotation.DbType;
import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.kpu.generator.entity.DefGenTable;

import javax.sql.DataSource;

/**
 * <p>
 * 通用业务接口
 * 代码生成
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefGenTableManager extends SuperManager<DefGenTable> {
    /**
     * 获取数据源
     *
     * @param dsId dsId
     * @return javax.sql.DataSource
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     * @update [2024/02/07  02:04 AM ] [lmx] [变更描述]
     */
    DataSource getDs(Long dsId);

    /**
     * 获取数据库类型
     *
     * @return com.baomidou.mybatisplus.annotation.DbType
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    DbType getDbType();
}
