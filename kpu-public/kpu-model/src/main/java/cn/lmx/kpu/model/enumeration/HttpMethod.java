package cn.lmx.kpu.model.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import cn.lmx.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * HTTP方法枚举
 *
 * @author lmx
 */
@Getter
@Schema(description = "HTTP方法-枚举")
@AllArgsConstructor
@NoArgsConstructor
public enum HttpMethod implements BaseEnum {
    /**
     * ALL
     */
    ALL("ALL"),
    /**
     * GET:GET
     */
    GET("GET"),
    /**
     * POST:POST
     */
    POST("POST"),
    /**
     * PUT:PUT
     */
    PUT("PUT"),
    /**
     * DELETE:DELETE
     */
    DELETE("DELETE"),
    /**
     * PATCH:PATCH
     */
    PATCH("PATCH"),
    /**
     * TRACE:TRACE
     */
    TRACE("TRACE"),
    /**
     * HEAD:HEAD
     */
    HEAD("HEAD"),
    /**
     * OPTIONS:OPTIONS
     */
    OPTIONS("OPTIONS"),

    ;
    @Schema(description = "描述")
    private String desc;

    public static HttpMethod match(String val, HttpMethod def) {
        return Stream.of(values()).parallel().filter((item) -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static HttpMethod get(String val) {
        return match(val, null);
    }

    public boolean eq(HttpMethod val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "GET,POST,PUT,DELETE,PATCH,TRACE,HEAD,OPTIONS", example = "GET")
    public String getCode() {
        return this.name();
    }
}
