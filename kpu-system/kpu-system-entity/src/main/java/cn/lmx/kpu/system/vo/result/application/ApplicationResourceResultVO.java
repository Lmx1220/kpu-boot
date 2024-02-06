package cn.lmx.kpu.system.vo.result.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import cn.lmx.kpu.system.entity.application.DefApplication;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author lmx
 * @version 4.0
 * @date 2024/02/07  02:04 下午
 * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "应用资源返回")
public class ApplicationResourceResultVO implements Serializable {
    private DefApplication defApplication;
    private Collection<DefResourceResultVO> resourceList;
}
