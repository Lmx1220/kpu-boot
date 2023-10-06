package cn.lmx.kpu.tenant.dto;

import cn.lmx.kpu.model.enumeration.system.TenantConnectTypeEnum;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  18:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class DatasourceConfigBO {

    /**
     * 名称
     */
    private String name;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 链接
     */
    private String url;

    /**
     * 驱动
     */
    private String driverClassName;

    private String dbPrefix;
    private Long tenantId;

    private TenantConnectTypeEnum type;
}
