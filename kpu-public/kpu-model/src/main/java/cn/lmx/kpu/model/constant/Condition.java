package cn.lmx.kpu.model.constant;

import com.baomidou.mybatisplus.annotation.SqlCondition;

/**
 * @author lmx
 * @version 3.8.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
public class Condition {

    /**  MySQL、Oracle 数据库的 模糊查询 */
//    public static final String LIKE = SqlCondition.LIKE;
    /**  ORACLE 数据库的 模糊查询 */
    public static final String LIKE = SqlCondition.ORACLE_LIKE;
}
