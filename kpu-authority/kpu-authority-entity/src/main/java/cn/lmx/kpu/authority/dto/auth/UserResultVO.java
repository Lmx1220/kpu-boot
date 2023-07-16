package cn.lmx.kpu.authority.dto.auth;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.authority.entity.core.Org;
import cn.lmx.kpu.model.constant.EchoDictItem;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

import static cn.lmx.kpu.model.constant.Condition.LIKE;
import static cn.lmx.kpu.model.constant.EchoApi.*;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/07/08  14:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "UserResultVO", description = "用户")
public class UserResultVO extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String nickName;

    /**
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS,  beanClass = Org.class)
     */
    @ApiModelProperty(value = "组织")
    @Echo(api = ORG_ID_CLASS, beanClass = Org.class)
    private Long orgId;

    /**
     * 岗位
     * #c_station
     *
     * @Echo(api = STATION_ID_CLASS)
     */
    @ApiModelProperty(value = "岗位")
    @Echo(api = STATION_ID_CLASS)
    private Long stationId;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    private Boolean readonly;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 民族
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.NATION)
     */
    @ApiModelProperty(value = "民族")
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.NATION)
    private String nation;

    /**
     * 学历
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.EDUCATION)
     */
    @ApiModelProperty(value = "学历")
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.EDUCATION)
    private String education;

    /**
     * 职位状态
     *
     * @Echo(api = DICT_ITEM_CLASS,  dictType = EchoDictItem.POSITION_STATUS)
     */
    @ApiModelProperty(value = "职位状态")
    @Echo(api = DICT_ITEM_CLASS, dictType = EchoDictItem.POSITION_STATUS)
    private String positionStatus;

    /**
     * 工作描述
     */
    @ApiModelProperty(value = "工作描述")
    @TableField(value = "work_describe", condition = LIKE)
    private String workDescribe;

    /**
     * 最后一次输错密码时间
     */
    @ApiModelProperty(value = "最后一次输错密码时间")
    @TableField("password_error_last_time")
    private LocalDateTime passwordErrorLastTime;

    /**
     * 密码错误次数
     */
    @ApiModelProperty(value = "密码错误次数")
    @TableField("password_error_num")
    private Integer passwordErrorNum;

    /**
     * 密码过期时间
     */
    @ApiModelProperty(value = "密码过期时间")
    @TableField("password_expire_time")
    private LocalDateTime passwordExpireTime;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @JsonIgnore
    private String password;

    /**
     * 密码盐
     */
    @ApiModelProperty(value = "密码盐")
    private String salt;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "创建者所属机构")
    private Long createdOrgId;


}
