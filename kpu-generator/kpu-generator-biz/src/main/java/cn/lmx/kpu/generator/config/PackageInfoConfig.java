package cn.lmx.kpu.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/08/22  12:58
 */
@Data
@NoArgsConstructor
public class PackageInfoConfig {
    private String parent = "cn.lmx.kpu";
    private String utilParent = "cn.lmx.basic";
    private String saveVo = "vo.save";
    private String updateVo = "vo.update";
    private String pageQuery = "vo.query";
    private String resultVo = "vo.result";
    private String entity = "entity";
    private String manager = "manager";
    private String managerImpl = "manager.{}impl";
    private String service = "service";
    private String serviceImpl = "service.{}impl";
    private String controller = "controller";
    private String mapper = "mapper";
    private String xml = "mapper_{}/base";
}
