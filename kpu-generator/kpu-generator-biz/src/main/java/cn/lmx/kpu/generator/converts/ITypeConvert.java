package cn.lmx.kpu.generator.converts;

import cn.lmx.kpu.generator.config.DateType;
import cn.lmx.kpu.generator.rules.ColumnType;

/**
 * 数据库字段类型转换
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 AM
 * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
 */
public interface ITypeConvert {


    /**
     * 执行类型转换
     *
     * @param datetype  日期类型
     * @param fieldType 字段类型
     * @param digit     小数位
     * @param size      size
     * @return ignore
     */
    ColumnType processTypeConvert(DateType datetype, String fieldType, Long size, Integer digit);

}
