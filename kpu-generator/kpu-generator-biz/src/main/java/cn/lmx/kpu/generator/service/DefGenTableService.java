package cn.lmx.kpu.generator.service;

import cn.lmx.basic.base.request.DownloadVO;
import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.generator.entity.DefGenTable;
import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import cn.lmx.kpu.generator.vo.result.DefGenTableResultVO;
import cn.lmx.kpu.generator.vo.save.DefGenTableImportVO;
import cn.lmx.kpu.generator.vo.save.DefGenVO;
import cn.lmx.kpu.generator.vo.save.ProjectGeneratorVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 代码生成
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
public interface DefGenTableService extends SuperService<Long, DefGenTable> {
    /**
     * 查询指定数据源的表结构元数据
     *
     * @param dsId 数据源
     * @return
     */
    List<DefGenTable> selectTableList(Long dsId);


    /**
     * 导入检测
     * 检查指定的表是否已经导入 DefGenTable 表
     *
     * @param tableNames
     * @return
     */
    Boolean importCheck(List<String> tableNames);

    /**
     * 导入表结构
     * <p>
     * 将物理表中能解析出来的元数据，存入 DefGenTable 和 DefGenTableColumn表
     *
     * @param importVO
     * @return
     */
    Boolean importTable(DefGenTableImportVO importVO);

    /**
     * 同步表结构中的字段
     * <p>
     * 对于物理表的字段变更:
     * 物理表中新增的字段将会插入 DefGenTableColumn，物理表中删除的字段将会删除 DefGenTableColumn，物理表中原来存在的字段不会修改。
     *
     * @param id
     */
    void syncField(Long id);

    /**
     * 预览代码
     * <p>
     * 根据表ID，预览指定模板的代码
     *
     * @param id       表id
     * @param template 模板
     * @return
     */
    Map<String, String> previewCode(Long id, TemplateEnum template);


    /**
     * 生成指定模板的代码到本机的绝对路径
     *
     * @param defGenVO 生成信息
     */
    void generatorCode(DefGenVO defGenVO);

    /**
     * 打包下载指定模板的代码
     *
     * @param ids      表id
     * @param template 模板
     * @return cn.lmx.basic.base.request.DownloadVO
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    DownloadVO downloadZip(List<Long> ids, TemplateEnum template);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    DefGenTableResultVO getDetail(Long id);

    /**
     * 获取生成项目的默认配置
     *
     * @return cn.lmx.kpu.generator.vo.save.ProjectGeneratorVO
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    ProjectGeneratorVO getDef();

    /**
     * 生成项目
     *
     * @param projectGenerator projectGenerator
     * @return java.lang.Boolean
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    Boolean generator(ProjectGeneratorVO projectGenerator);

    /***
     * 获取字段模板映射
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     */
    Map<String, String> getFieldTemplate();

    /**
     * 获取默认的文件覆盖策略
     *
     * @return java.util.Map<java.lang.String, cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum>
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     * @update [2024/02/07  02:04 AM ] [lmx] [变更描述]
     */
    Map<String, FileOverrideStrategyEnum> getDefFileOverrideStrategy();

    /**
     * 批量查询
     *
     * @param idList idList
     * @return java.util.List<cn.lmx.kpu.generator.vo.result.DefGenTableResultVO>
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     * @update [2024/02/07  02:04 AM ] [lmx] [变更描述]
     */
    List<DefGenTableResultVO> findTableList(List<Long> idList);

    /**
     * 下载
     *
     * @param projectGenerator projectGenerator
     * @return cn.lmx.basic.base.request.DownloadVO
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     * @update [2024/02/07  02:04 PM ] [lmx] [变更描述]
     */
    DownloadVO download(ProjectGeneratorVO projectGenerator);
}
