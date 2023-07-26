package cn.lmx.kpu.authority.dto.auth;

import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.kpu.model.vo.save.AppendixSaveVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "UserUpdateAvatarDTO", description = "用户")
public class UserUpdateAvatarDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 上传的头像
     */
    @ApiModelProperty(value = "上传的头像")
    @Valid
    private AppendixSaveVO appendixAvatar;

    /**
     * 选择的头像
     */
    @ApiModelProperty(value = "选择的头像")
    private String avatar;

}
