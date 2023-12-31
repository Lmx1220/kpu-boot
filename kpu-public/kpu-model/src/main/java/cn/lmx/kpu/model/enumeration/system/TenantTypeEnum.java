package cn.lmx.kpu.model.enumeration.system;

import cn.lmx.basic.interfaces.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 企业
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TenantTypeEnum", description = "类型-枚举")
public enum TenantTypeEnum implements BaseEnum {

    /**
     * CREATE="创建"
     */
    CREATE("创建"),
    /**
     * REGISTER="注册"
     */
    REGISTER("注册"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static TenantTypeEnum match(String val, TenantTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TenantTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(TenantTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "CREATE,REGISTER", example = "CREATE")
    public String getCode() {
        return this.name();
    }

}
