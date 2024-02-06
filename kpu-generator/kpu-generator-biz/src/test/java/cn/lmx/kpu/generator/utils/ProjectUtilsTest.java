package cn.lmx.kpu.generator.utils;

import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.kpu.generator.enumeration.ProjectTypeEnum;
import cn.lmx.kpu.generator.vo.save.ProjectGeneratorVO;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 PM
 * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
 */
public class ProjectUtilsTest {
    public static void main(String[] args) {
        ProjectGeneratorVO vo = new ProjectGeneratorVO();
        vo.setProjectPrefix("kpu");
        vo.setOutputDir("/Users/lmx/gitlab/kpu-cloud-pro-datasource-column");
        vo.setType(ProjectTypeEnum.CLOUD);
        vo.setAuthor("阿汤哥");
        vo.setServiceName("test");
        vo.setModuleName("test");
        vo.setParent("cn.lmx.kpu");
        vo.setGroupId("cn.lmx.kpu");
        vo.setUtilParent("cn.lmx.basic");
        vo.setVersion("4.16.0-java17");
        vo.setDescription("测试服务");
        vo.setServerPort(8080);
        ProjectUtils.generator(vo, new DatabaseProperties());
    }
}
