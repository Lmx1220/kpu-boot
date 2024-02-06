package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.DefInterfaceProperty;
import cn.lmx.kpu.msg.vo.save.DefInterfacePropertyBatchSaveVO;

import java.util.Map;


/**
 * <p>
 * 业务接口
 * 接口属性
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
public interface DefInterfacePropertyService extends SuperService<Long, DefInterfaceProperty> {
    /**
     * 根据接口ID查询接口属性参数
     *
     * @param id
     * @return
     */
    Map<String, Object> listByInterfaceId(Long id);

    /**
     * 批量保存
     *
     * @param saveVO
     * @return
     */
    Boolean batchSave(DefInterfacePropertyBatchSaveVO saveVO);
}


