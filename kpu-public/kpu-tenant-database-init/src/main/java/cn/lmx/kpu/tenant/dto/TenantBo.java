package cn.lmx.kpu.tenant.dto;

import cn.lmx.kpu.model.enumeration.system.TenantConnectTypeEnum;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/05  19:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class TenantBo {
    private TenantConnectTypeEnum connectType;
}
