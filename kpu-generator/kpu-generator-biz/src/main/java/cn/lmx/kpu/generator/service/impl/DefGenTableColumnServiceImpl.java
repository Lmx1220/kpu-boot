package cn.lmx.kpu.generator.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.BeanPlusUtil;

import cn.lmx.kpu.generator.config.GeneratorConfig;
import cn.lmx.kpu.generator.entity.DefGenTable;
import cn.lmx.kpu.generator.entity.DefGenTableColumn;
import cn.lmx.kpu.generator.manager.DefGenTableColumnManager;
import cn.lmx.kpu.generator.manager.DefGenTableManager;
import cn.lmx.kpu.generator.service.DefGenTableColumnService;
import cn.lmx.kpu.generator.utils.GenUtils;
import cn.lmx.kpu.generator.vo.query.DefGenTableColumnPageQuery;
import cn.lmx.kpu.generator.vo.result.DefGenTableColumnResultVO;

import javax.sql.DataSource;

/**
 * <p>
 * 业务实现类
 * 代码生成字段
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefGenTableColumnServiceImpl extends SuperServiceImpl<DefGenTableColumnManager, Long, DefGenTableColumn> implements DefGenTableColumnService {
    private final DefGenTableManager defGenTableManager;
    private final GeneratorConfig generatorConfig;


    @Override
    public IPage<DefGenTableColumnResultVO> pageColumn(PageParams<DefGenTableColumnPageQuery> params) {
        IPage<DefGenTableColumn> page = params.buildPage();
        DefGenTableColumnPageQuery model = params.getModel();
        DefGenTableColumn column = BeanUtil.toBean(model, DefGenTableColumn.class);
        superManager.page(page, Wraps.lbQ(column));
        return BeanPlusUtil.toBeanPage(page, DefGenTableColumnResultVO.class);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean syncField(Long tableId, Long id) {
        DefGenTable genTable = defGenTableManager.getById(tableId);
        DefGenTableColumn genTableColumn = getById(id);
        ArgumentAssert.notNull(genTable, "请先选择需要同步的表");
        ArgumentAssert.notNull(genTableColumn, "请先选择需要同步的字段");
        DataSource ds = defGenTableManager.getDs(genTable.getDsId());

        Table tableMeta = MetaUtil.getTableMeta(ds, genTable.getName());
        for (Column column : tableMeta.getColumns()) {
            if (genTableColumn.getName().equals(column.getName())) {
                DefGenTableColumn tableColumn = GenUtils.initColumnField(generatorConfig, defGenTableManager.getDbType(), genTable, column);
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
