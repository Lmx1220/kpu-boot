package cn.lmx.kpu.generator.service;


import cn.lmx.basic.base.request.DownloadVO;
import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import cn.lmx.kpu.generator.vo.query.GenTablePageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableImportVO;
import cn.lmx.kpu.generator.vo.save.GenTableSaveVO;
import cn.lmx.kpu.generator.vo.save.GenVO;
import cn.lmx.kpu.generator.vo.update.GenTableUpdateVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 代码生成
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
public interface GenTableService extends SuperService<Long, GenTable, GenTableSaveVO, GenTableUpdateVO, GenTablePageQuery, GenTableResultVO> {
    /**
     * 查询指定数据源的表结构元数据
     *
     * @param dsId 数据源
     * @return
     */
    List<GenTable> selectTableList(Long dsId);


    /**
     * 导入检测
     * 检查指定的表是否已经导入 GenTable 表
     *
     * @param tableNames
     * @return
     */
    Boolean importCheck(List<String> tableNames);

    /**
     * 导入表结构
     * <p>
     * 将物理表中能解析出来的元数据，存入 GenTable 和 GenTableColumn表
     *
     * @param importVO
     * @return
     */
    Boolean importTable(GenTableImportVO importVO);

    /**
     * 同步表结构中的字段
     * <p>
     * 对于物理表的字段变更:
     * 物理表中新增的字段将会插入 GenTableColumn，物理表中删除的字段将会删除 GenTableColumn，物理表中原来存在的字段不会修改。
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
     * @param genVO 生成信息
     */
    void generatorCode(GenVO genVO);

    /**
     * 打包下载指定模板的代码
     *
     * @param ids      表id
     * @param template 模板
     * @return cn.lmx.basic.base.request.DownloadVO
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27 ] [lmx] [初始创建]
     */
    DownloadVO downloadZip(List<Long> ids, TemplateEnum template);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    GenTableResultVO getDetail(Long id);

    /**
     * 获取生成项目的默认配置
     *
     * @return cn.lmx.kpu.generator.vo.save.ProjectGeneratorVO
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27 ] [lmx] [初始创建]
     */
//    ProjectGeneratorVO get();

    /**
     * 生成项目
     *
     * @param projectGenerator projectGenerator
     * @return java.lang.Boolean
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27 ] [lmx] [初始创建]
     */
//    Boolean generator(ProjectGeneratorVO projectGenerator);

    /***
     * 获取字段模板映射
     * @author lmx
     * @date 2023/10/13 14:27
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @create [2023/10/13 14:27 ] [lmx] [初始创建]
     */
    Map<String, String> getFieldTemplate();

    /**
     * 获取默认的文件覆盖策略
     *
     * @return java.util.Map<java.lang.String, cn.lmx.kpu.generator.enumeration.FileOverrideStrategyEnum>
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27 ] [lmx] [初始创建]
     */
    Map<String, FileOverrideStrategyEnum> getFileOverrideStrategy();

    /**
     * 批量查询
     *
     * @param idList idList
     * @return java.util.List<cn.lmx.kpu.generator.vo.result.GenTableResultVO>
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27 ] [lmx] [初始创建]
     */
    List<GenTableResultVO> findTableList(List<Long> idList);

    /**
     * 下载
     *
     * @param projectGenerator projectGenerator
     * @return cn.lmx.basic.base.request.DownloadVO
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27 ] [lmx] [初始创建]
     * @update [2023/10/13 14:27 ] [lmx] [变更描述]
     */
//    DownloadVO download(ProjectGeneratorVO projectGenerator);
}
