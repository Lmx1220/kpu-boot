package cn.lmx.kpu.authority.entity.common;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.authority.enumeration.common.LogType;
import cn.lmx.kpu.model.enumeration.HttpMethod;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

import static cn.lmx.basic.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;
import static cn.lmx.kpu.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 系统日志
 * </p>
 *
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_opt_log")
@ApiModel(value = "OptLog", description = "系统日志")
@AllArgsConstructor
public class OptLog extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();
    /**
     * 操作IP
     */
    @ApiModelProperty(value = "操作IP")
    @Size(max = 50, message = "操作IP长度不能超过50")
    @TableField(value = "request_ip", condition = LIKE)
    @Excel(name = "操作IP")
    private String requestIp;

    /**
     * 日志类型
     * #LogType{OPT:操作类型;EX:异常类型}
     */
    @ApiModelProperty(value = "日志类型")
    @TableField("type")
    @Echo(api = Echo.ENUM_API)
    @Excel(name = "日志类型", replace = {"操作类型_OPT", "异常类型_EX", "_null"})
    private LogType type;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    @Size(max = 50, message = "操作人长度不能超过50")
    @TableField(value = "nick_name", condition = LIKE)
    @Excel(name = "操作人")
    private String nickName;

    /**
     * 操作描述
     */
    @ApiModelProperty(value = "操作描述")
    @Size(max = 255, message = "操作描述长度不能超过255")
    @TableField(value = "description", condition = LIKE)
    @Excel(name = "操作描述")
    private String description;

    /**
     * 类路径
     */
    @ApiModelProperty(value = "类路径")
    @Size(max = 255, message = "类路径长度不能超过255")
    @TableField(value = "class_path", condition = LIKE)
    @Excel(name = "类路径")
    private String classPath;

    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    @Size(max = 50, message = "请求方法长度不能超过50")
    @TableField(value = "action_method", condition = LIKE)
    @Excel(name = "请求方法")
    private String actionMethod;

    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址")
    @Size(max = 50, message = "请求地址长度不能超过50")
    @TableField(value = "request_uri", condition = LIKE)
    @Excel(name = "请求地址")
    private String requestUri;

    /**
     * 请求类型
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    @ApiModelProperty(value = "请求类型")
    @TableField("http_method")
    @Excel(name = "请求类型", replace = {"GET请求_GET", "POST请求_POST", "PUT请求_PUT", "DELETE请求_DELETE", "PATCH请求_PATCH", "TRACE请求_TRACE", "HEAD请求_HEAD", "OPTIONS请求_OPTIONS", "_null"})
    @Echo(api = Echo.ENUM_API)
    private HttpMethod httpMethod;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @TableField("start_time")
    @Excel(name = "开始时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime startTime;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    @TableField("finish_time")
    @Excel(name = "完成时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime finishTime;

    /**
     * 消耗时间
     */
    @ApiModelProperty(value = "消耗时间")
    @TableField("consuming_time")
    @Excel(name = "消耗时间")
    private Long consumingTime;

    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    @Size(max = 500, message = "浏览器长度不能超过500")
    @TableField(value = "ua", condition = LIKE)
    @Excel(name = "浏览器")
    private String ua;


    @Builder
    public OptLog(Long id, LocalDateTime createdTime, Long createdBy,
                  String requestIp, LogType type, String userName, String description, String classPath,
                  String actionMethod, String requestUri, HttpMethod httpMethod, LocalDateTime startTime, LocalDateTime finishTime, Long consumingTime, String ua) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.requestIp = requestIp;
        this.type = type;
        this.nickName = nickName;
        this.description = description;
        this.classPath = classPath;
        this.actionMethod = actionMethod;
        this.requestUri = requestUri;
        this.httpMethod = httpMethod;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.consumingTime = consumingTime;
        this.ua = ua;
    }

}
