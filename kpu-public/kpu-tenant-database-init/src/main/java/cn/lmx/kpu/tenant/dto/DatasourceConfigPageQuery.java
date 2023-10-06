package cn.lmx.kpu.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 数据源
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
@ApiModel(value = "DatasourceConfigPageQuery", description = "数据源")
public class DatasourceConfigPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    private String url;
    /**
     * 驱动
     */
    @ApiModelProperty(value = "驱动")
    private String driverClassName;

}
