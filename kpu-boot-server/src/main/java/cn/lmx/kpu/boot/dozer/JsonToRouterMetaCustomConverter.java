package cn.lmx.kpu.boot.dozer;

import cn.lmx.basic.jackson.JsonUtil;
import cn.lmx.kpu.authority.dto.auth.RouterMeta;
import com.github.dozermapper.core.CustomConverter;

/**
 * 自定义转换器
 * <p>
 * 用于将 json 字符串转换为 RouterMeta 对象
 * <p>
 *
 * @author lmx
 */
public class JsonToRouterMetaCustomConverter implements CustomConverter {
    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        if (sourceFieldValue == null) {
            return null;
        }

        try {
            // 将 metaJson 字符串转换为 RouterMeta 对象
            String jsonString = (String) sourceFieldValue;
            RouterMeta routerMeta = JsonUtil.parse(jsonString, RouterMeta.class);
            return routerMeta;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
