package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.interfaces.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateEnum implements BaseEnum {
    BACKEND("BACKEND", "后端"),
    WEB_PLUS("WEB_PLUS", "前端");

    private String value;
    private String desc;

    @Override
    public String getDesc() {
        return desc;
    }

    public boolean eq(TemplateEnum templateEnum) {
        return this.value.equals(templateEnum.value);
    }
}
