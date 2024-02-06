package cn.lmx.kpu.generator.converts;

import com.baomidou.mybatisplus.annotation.DbType;
import cn.lmx.kpu.generator.converts.select.BranchBuilder;
import cn.lmx.kpu.generator.converts.select.Selector;
import cn.lmx.kpu.generator.rules.ColumnType;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
public class TypeConverts {

    /**
     * 查询数据库类型对应的类型转换器
     *
     * @param dbType 数据库类型
     * @return 返回转换器
     */
    public static ITypeConvert getTypeConvert(DbType dbType) {
        return switch (dbType) {
            case ORACLE -> OracleTypeConvert.INSTANCE;
            case SQL_SERVER -> SqlServerTypeConvert.INSTANCE;
            default -> MySqlTypeConvert.INSTANCE;
        };
    }

    /**
     * 使用指定参数构建一个选择器
     *
     * @param param 参数
     * @return 返回选择器
     */
    static Selector<String, ColumnType> use(String param) {
        return new Selector<>(param.toLowerCase());
    }

    /**
     * 这个分支构建器用于构建用于支持 {@link String#contains(CharSequence)} 的分支
     *
     * @param value 分支的值
     * @return 返回分支构建器
     * @see #containsAny(CharSequence...)
     */
    static BranchBuilder<String, ColumnType> contains(CharSequence value) {
        return BranchBuilder.of(s -> s.contains(value));
    }

    /**
     * @see #contains(CharSequence)
     */
    static BranchBuilder<String, ColumnType> containsAny(CharSequence... values) {
        return BranchBuilder.of(s -> {
            for (CharSequence value : values) {
                if (s.contains(value)) {
                    return true;
                }
            }
            return false;
        });
    }
}
