package cn.lmx.kpu.authority.service.common;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.basic.interfaces.echo.LoadService;
import cn.lmx.kpu.authority.dto.common.*;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.model.vo.query.CodeQueryVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 字典类型
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface DictService extends SuperService<Long, Dict, DictSaveVO, DictUpdateVo, DictPageQuery, DictResultVO>, LoadService {


    /**
     * 根据类型查询字典
     *
     * @param types 类型
     * @return 集合
     */
    Map<String, List<Dict>> listByItem(CodeQueryVO[] types);



//    /**
//     * 根据类型编码查询字典项
//     *
//     * @param typeCodes 字典类型#编码
//     * @return 名称
//     */
//    Map<Serializable, Object> findDictItem(Set<Serializable> typeCodes);

}
