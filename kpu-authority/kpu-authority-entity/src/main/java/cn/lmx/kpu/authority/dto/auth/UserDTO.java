package cn.lmx.kpu.authority.dto.auth;

import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.kpu.model.constant.EchoDictItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.lmx.kpu.model.constant.EchoApi.DICT_ITEM_CLASS;

/**
 * 用户
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserDTO", description = "用户")
public class UserDTO implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 30, message = "账号长度不能超过30")
    private String username;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50")
    private String nickName;

    /**
     * 组织ID
     * #c_org
     */
    @ApiModelProperty(value = "组织ID")
    private Long org;

    /**
     * 岗位ID
     * #c_station
     */
    @ApiModelProperty(value = "岗位ID")
    private Long station;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Size(max = 20, message = "手机长度不能超过20")
    private String mobile;

    /**
     * 性别
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.SEX)
     */
    @ApiModelProperty(value = "性别")
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.SEX)
    private String sex;

    /**
     * 启用状态 1启用 0禁用
     */
    @ApiModelProperty(value = "启用状态 1启用 0禁用")
    private Boolean state;

    /**
     * 照片
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @ApiModelProperty(value = "工作描述")
    private String workDescribe;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

}
