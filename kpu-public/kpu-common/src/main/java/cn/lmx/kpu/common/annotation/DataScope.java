package cn.lmx.kpu.common.annotation;

import java.lang.annotation.*;

/**
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataScope {
    DataField[] value() default {};

    boolean ignore() default false;
}
