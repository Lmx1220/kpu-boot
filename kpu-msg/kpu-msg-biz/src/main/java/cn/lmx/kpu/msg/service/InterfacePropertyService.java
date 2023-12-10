package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.InterfaceProperty;
import cn.lmx.kpu.msg.vo.save.InterfacePropertySaveVO;
import cn.lmx.kpu.msg.vo.update.InterfacePropertyUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfacePropertyResultVO;
import cn.lmx.kpu.msg.vo.query.InterfacePropertyPageQuery;


/**
 * <p>
 * 业务接口
 * 接口属性
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface InterfacePropertyService extends SuperService<Long, InterfaceProperty, InterfacePropertySaveVO,
    InterfacePropertyUpdateVO, InterfacePropertyPageQuery, InterfacePropertyResultVO> {

}


