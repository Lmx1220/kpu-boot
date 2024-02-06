package cn.lmx.kpu.generator.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import cn.lmx.kpu.generator.enumeration.ProjectTypeEnum;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 代码生成
 * </p>
 *
 * @author lmx
 * @since 2024/02/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "项目生成")
public class ProjectGeneratorVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 作者 */
    @NotEmpty(message = "请填写作者")
    public String author;
    /** cloud boot */
    @NotNull(message = "请填写类型")
    private ProjectTypeEnum type;
    /** 输出路径 */
    @NotEmpty(message = "请填写输出路径")

    private String outputDir;
    /** 项目前缀 */
    @NotEmpty(message = "请填写项目前缀")
    private String projectPrefix;
    /**
     * 服务名
     * <p>
     * 如： kpu-base-server 中的base
     * 如： kpu-system-server system
     */
    @NotEmpty(message = "请填写服务名")
    private String serviceName;

    /**
     * 模块名
     * <p>
     * 如： cn.lmx.kpu.base.dao.common 包中的 base
     * 如： cn.lmx.kpu.file.dao.xxx 包中的 file
     */
    @NotEmpty(message = "请填写模块名")
    private String moduleName;

    /**
     * kpu项目 生成代码位于 src/main/java 下的基础包
     * 如： cn.lmx.kpu.base.dao.common 包中的 cn.lmx.kpu
     * 如： cn.lmx.kpu.file.dao.xxx 包中的 cn.lmx.kpu
     */
    @NotEmpty(message = "请填写基础包")
    private String parent;

    @NotEmpty(message = "请填写服务groupId")
    private String groupId;
    /**
     * kpu-util 基础包
     */
    @NotEmpty(message = "请填写工具类基础包")
    private String utilParent;
    @NotEmpty(message = "请填写工具类groupId")
    private String utilGroupId;
    @NotEmpty(message = "请填写版本号")
    private String version;
    /**
     * 中文服务名
     *
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     * @update [2024/02/07  02:04 PM ] [lmx] [变更描述]
     */
    @NotEmpty(message = "请填写服务中文名")
    private String description;
    @NotNull(message = "请填写端口号")
    private Integer serverPort;
}
