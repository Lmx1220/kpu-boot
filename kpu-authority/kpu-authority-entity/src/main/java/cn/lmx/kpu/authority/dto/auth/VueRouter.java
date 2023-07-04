package cn.lmx.kpu.authority.dto.auth;

import cn.lmx.basic.base.entity.TreeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 构建 Vue路由
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter extends TreeEntity<VueRouter, Long> {

    private static final long serialVersionUID = -3327478146308500708L;
    @ApiModelProperty(value = "路径")
    private String path;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "组件")
    private String component;
    @ApiModelProperty(value = "重定向")
    private String redirect;
    @ApiModelProperty(value = "元数据")
    private RouterMeta meta;

    @Override
    @JsonIgnore
    public Long getId() {
        return this.id;
    }

    @Override
    @JsonIgnore
    public Long getParentId() {
        return this.parentId;
    }

//    public String getComponent() {
//        if (getChildren() != null && !getChildren().isEmpty()) {
//            return "Layout";
//        }
//        return this.component;
//    }
}
