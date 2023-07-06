package cn.lmx.kpu.authority.dto.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.lmx.basic.base.entity.TreeEntity;
import cn.lmx.kpu.authority.enumeration.auth.AuthorizeType;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;

import static cn.lmx.kpu.model.constant.Condition.LIKE;

/**
 * menuList
 * 菜单资源树
 *
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Data
@ToString(callSuper = true)
public class MenuResourceTreeVO extends TreeEntity<MenuResourceTreeVO, Long> {
    private AuthorizeType type;
    private String label;
    private String code;
    private String icon;
    private Boolean isDef;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 200, message = "描述长度不能超过200")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述")
    private String describe;

}
