package cn.lmx.kpu.model.enumeration.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import cn.lmx.basic.interfaces.BaseEnum;

/**
 * 资源类型
 *
 * @author lmx
 * @since 2024/02/07  02:04
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "资源类型-枚举")
public enum ResourceTypeEnum implements BaseEnum {
    /**
     * 菜单
     */
    MENU("20", "菜单"),
    /**
     * 视图
     */
    VIEW("30", "视图"),
    /**
     * 按钮
     */
    BUTTON("40", "按钮"),
    /**
     * 字段
     */
    FIELD("50", "字段"),

    /**
     * 数据权限
     */
    DATA("60", "数据");

    /**
     * 资源类型
     */
    private String code;

    /**
     * 资源描述
     */
    private String desc;

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    @Schema(description = "编码", allowableValues = "20,30,40,50,60", example = "20")
    public String getCode() {
        return this.code;
    }
}
