package cn.lmx.kpu.generator.utils.inner;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/27  13:03
 */
@Data
public class EchoType {
    private String echoStr;
    private String api;
    private String ref;
    private String dictType;
    private String beanClass;

    public void valid() {
        if (StrUtil.isEmpty(echoStr)) {
            throw new IllegalArgumentException("echoStr is null");
        }
        if (StrUtil.isEmpty(api) && StrUtil.isEmpty(ref) && StrUtil.isEmpty(beanClass)) {
            throw new IllegalArgumentException("api or ref or beanClass is null");
        }
        if (StrUtil.isNotEmpty(api) && StrUtil.isNotEmpty(ref)) {
            throw new IllegalArgumentException("api and ref is not null");
        }
        if (StrUtil.isNotEmpty(api) && StrUtil.isNotEmpty(beanClass)) {
            throw new IllegalArgumentException("api and beanClass is not null");
        }
        if (StrUtil.isNotEmpty(ref) && StrUtil.isNotEmpty(beanClass)) {
            throw new IllegalArgumentException("ref and beanClass is not null");
        }

    }
}
