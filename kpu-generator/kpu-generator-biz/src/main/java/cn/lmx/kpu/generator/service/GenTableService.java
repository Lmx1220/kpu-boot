package cn.lmx.kpu.generator.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.vo.query.GenTablePageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableSaveVO;
import cn.lmx.kpu.generator.vo.update.GenTableUpdateVO;

public interface GenTableService extends SuperService<Long, GenTable, GenTableSaveVO,
        GenTableUpdateVO, GenTablePageQuery, GenTableResultVO> {

}