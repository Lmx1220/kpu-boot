package cn.lmx.kpu.generator.converts;


import cn.lmx.kpu.generator.rules.ColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

public interface ITypeConvert {

    ColumnType processTypeConvert(DateType dateType, String fieldType, Long size, Integer digit);
}
