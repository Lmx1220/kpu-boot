package cn.lmx.kpu.generator.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.generator.config.GeneratorConfig;
import cn.lmx.kpu.generator.entity.GenTable;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.manager.GenTableColumnManager;
import cn.lmx.kpu.generator.manager.GenTableManager;
import cn.lmx.kpu.generator.service.GenTableColumnService;
import cn.lmx.kpu.generator.utils.GenUtils;
import cn.lmx.kpu.generator.vo.query.GenTableColumnPageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableColumnResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableColumnSaveVO;
import cn.lmx.kpu.generator.vo.update.GenTableColumnUpdateVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * <p>
 * 业务实现类
 * 代码生成字段
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenTableColumnServiceImpl extends SuperServiceImpl<GenTableColumnManager, Long, GenTableColumn, GenTableColumnSaveVO, GenTableColumnUpdateVO, GenTableColumnPageQuery, GenTableColumnResultVO> implements GenTableColumnService {
    private final GenTableManager genTableManager;
    private final GeneratorConfig generatorConfig;


    @Override
    public IPage<GenTableColumnResultVO> pageColumn(PageParams<GenTableColumnPageQuery> params) {
        IPage<GenTableColumn> page = params.buildPage();
        GenTableColumnPageQuery model = params.getModel();
        GenTableColumn column = BeanUtil.toBean(model, GenTableColumn.class);
        superManager.page(page, Wraps.lbQ(column));
        return BeanPlusUtil.toBeanPage(page, GenTableColumnResultVO.class);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean syncField(Long tableId, Long id) {
        GenTable genTable = genTableManager.getById(tableId);
        GenTableColumn genTableColumn = getById(id);
        ArgumentAssert.notNull(genTable, "请先选择需要同步的表");
        ArgumentAssert.notNull(genTableColumn, "请先选择需要同步的字段");
        DataSource ds = genTableManager.getDs(genTable.getDsId());

        Table tableMeta = MetaUtil.getTableMeta(ds, genTable.getName());
        for (Column column : tableMeta.getColumns()) {
            if (genTableColumn.getName().equals(column.getName())) {
                GenTableColumn tableColumn = GenUtils.initColumnField(generatorConfig, genTableManager.getDbType(), genTable, column);
                if (tableColumn != null) {
                    tableColumn.setId(id);
                    tableColumn.setTableId(tableId);
                    superManager.updateById(tableColumn);
                    break;
                }
            }
        }
        return true;
    }
}
