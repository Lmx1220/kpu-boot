package cn.lmx.kpu.generator.service;

import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.generator.entity.GenTableColumn;
import cn.lmx.kpu.generator.vo.query.GenTableColumnPageQuery;
import cn.lmx.kpu.generator.vo.result.GenTableColumnResultVO;
import cn.lmx.kpu.generator.vo.save.GenTableColumnSaveVO;
import cn.lmx.kpu.generator.vo.update.GenTableColumnUpdateVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 业务接口
 * 代码生成字段
 * </p>
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
public interface GenTableColumnService extends SuperService<Long, GenTableColumn, GenTableColumnSaveVO, GenTableColumnUpdateVO, GenTableColumnPageQuery, GenTableColumnResultVO> {

    /**
     * 分页查询指定表的字段
     *
     * @param params params
     * @return com.baomidou.mybatisplus.core.metadata.IPage<cn.lmx.kpu.generator.vo.result.GenTableColumnResultVO>
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27] [lmx] [初始创建]
     */
    IPage<GenTableColumnResultVO> pageColumn(PageParams<GenTableColumnPageQuery> params);

    /**
     * 同步字段结构和注释
     *
     * @param id      id
     * @param tableId
     * @return java.lang.Boolean
     * @author lmx
     * @date 2023/10/13 14:27
     * @create [2023/10/13 14:27 ] [lmx] [初始创建]
     */
    Boolean syncField(Long tableId, Long id);
}
