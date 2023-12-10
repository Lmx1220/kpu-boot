package cn.lmx.kpu.msg.service;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.msg.entity.Interface;
import cn.lmx.kpu.msg.vo.save.InterfaceSaveVO;
import cn.lmx.kpu.msg.vo.update.InterfaceUpdateVO;
import cn.lmx.kpu.msg.vo.result.InterfaceResultVO;
import cn.lmx.kpu.msg.vo.query.InterfacePageQuery;


/**
 * <p>
 * 业务接口
 * 接口
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
public interface InterfaceService extends SuperService<Long, Interface, InterfaceSaveVO,
    InterfaceUpdateVO, InterfacePageQuery, InterfaceResultVO> {

}


