package cn.lmx.kpu.authority.dto.core;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.annotation.echo.Echo;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.interfaces.echo.EchoVO;
import cn.lmx.kpu.authority.entity.core.Org;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

import static cn.lmx.kpu.model.constant.EchoApi.ORG_ID_CLASS;

/**
 * @author lmx
 * @since 2023/7/4 14:27
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "StationResultVO", description = "岗位")
@AllArgsConstructor
public class StationResultVO extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String remarks;

    @ApiModelProperty(value = "创建者所属机构")
    private Long createdOrgId;

    @Builder
    public StationResultVO(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                           String name, Long orgId, Boolean state, String remarks, Long createdOrgId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.name = name;
        this.orgId = orgId;
        this.state = state;
        this.remarks = remarks;
        this.createdOrgId = createdOrgId;
    }

}
