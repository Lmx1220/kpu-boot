package cn.lmx.kpu.model.vo.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源 查询DTO
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "MenuQueryDTO", description = "资源查询")
public class MenuQueryDTO {

    /**
     * 父资源id， 用于查询按钮
     */
    @ApiModelProperty(value = "菜单id", notes = "指定菜单id")
    private Long menuId;
    /**
     * 登录人用户id
     */
    @ApiModelProperty(value = "指定用户id", notes = "指定用户id，前端不传则自动获取")
    private Long userId;

}
