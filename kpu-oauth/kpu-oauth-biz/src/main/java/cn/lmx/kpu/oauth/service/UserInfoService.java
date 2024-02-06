package cn.lmx.kpu.oauth.service;

import cn.lmx.kpu.base.entity.user.BaseOrg;
import cn.lmx.kpu.oauth.vo.param.RegisterByEmailVO;
import cn.lmx.kpu.oauth.vo.param.RegisterByMobileVO;
import cn.lmx.kpu.oauth.vo.result.OrgResultVO;

import java.util.List;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 PM
 * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
 */
public interface UserInfoService {
    /**
     * 根据单位ID查找部门
     *
     * @param companyId 单位ID
     * @return java.util.List<cn.lmx.kpu.model.entity.base.SysOrg>
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    List<BaseOrg> findDeptByCompany(Long companyId);

    /**
     * 查询单位和部门信息
     *
     * @return cn.lmx.kpu.oauth.vo.result.OrgResultVO
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    OrgResultVO findCompanyAndDept();


    /**
     * 注册
     *
     * @param register 注册
     * @return
     */
    String registerByMobile(RegisterByMobileVO register);

    /**
     * 注册
     *
     * @param register 注册
     * @return
     */
    String registerByEmail(RegisterByEmailVO register);
}
