package cn.lmx.kpu.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserSaveVO", description = "用户")
public class UserSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS,  beanClass = Org.class)
     */
    @ApiModelProperty(value = "组织")
    private Long orgId;
    /**
     * 岗位
     * #c_station
     *
     * @Echo(api = STATION_ID_CLASS)
     */
    @ApiModelProperty(value = "岗位")
    private Long stationId;
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
    private String sex;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Size(max = 255, message = "头像长度不能超过255")
    private String avatar;
    /**
     * 民族
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.NATION)
     */
    @ApiModelProperty(value = "民族")
    @Size(max = 2, message = "民族长度不能超过2")
    private String nation;
    /**
     * 学历
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.EDUCATION)
     */
    @ApiModelProperty(value = "学历")
    @Size(max = 2, message = "学历长度不能超过2")
    private String education;
    /**
     * 职位状态
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.POSITION_STATUS)
     */
    @ApiModelProperty(value = "职位状态")
    @Size(max = 2, message = "职位状态长度不能超过2")
    private String positionStatus;
    /**
     * 工作描述
     */
    @ApiModelProperty(value = "工作描述")
    @Size(max = 255, message = "工作描述长度不能超过255")
    private String workDescribe;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @Size(min = 6, max = 64, message = "密码长度不能小于6或超过64")
    private String password;
    private Long createdOrgId;

}
