package cn.lmx.kpu.generator.service;

import cn.lmx.basic.base.request.DownloadVO;
import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.enumeration.TemplateEnum;
import cn.lmx.kpu.generator.vo.query.GenTablePageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableSaveVO;
import cn.lmx.kpu.generator.vo.save.GenVO;
import cn.lmx.kpu.generator.vo.update.GenTableUpdateVO;

import java.util.List;
import java.util.Map;

public interface GenTableService extends SuperService<Long, GenTable, GenTableSaveVO,
        GenTableUpdateVO, GenTablePageQuery, GenTableResultVO> {

    Map<String, String> previewCode(Long id, TemplateEnum templateEnum);

    void generatorCode(GenVO genVO);

    DownloadVO downloadZip(List<Long> ids, TemplateEnum templateEnum);

    List<GenTable> selectTableList();
}