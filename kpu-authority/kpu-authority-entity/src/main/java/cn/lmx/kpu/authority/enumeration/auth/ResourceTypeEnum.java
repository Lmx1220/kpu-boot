package cn.lmx.kpu.authority.enumeration.auth;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 资源类型
 *
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ResourceTypeEnum", description = "资源类型-枚举")
public enum ResourceTypeEnum implements BaseEnum {
    /**
     * 菜单
     */
    MENU("10", "菜单"),
    /**
     * 视图
     */
    VIEW("20", "视图"),
    /**
     * 功能
     */
    FUNCTION("30", "功能"),
    /**
     * 字段
     */
    FIELD("40", "字段"),

    /**
     * 数据权限
     */
    DATA("50", "数据");


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
    @ApiModelProperty(value = "编码", allowableValues = "10,20,30,40,50", example = "10")
    public String getCode() {
        return this.code;
    }
}
