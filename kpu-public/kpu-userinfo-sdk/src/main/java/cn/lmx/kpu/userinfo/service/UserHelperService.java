package cn.lmx.kpu.userinfo.service;

import cn.lmx.basic.context.ContextUtil;
import cn.lmx.kpu.model.entity.base.SysOrg;
import cn.lmx.kpu.model.entity.base.SysStation;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.userinfo.dao.OrgHelperMapper;
import cn.lmx.kpu.userinfo.dao.StationHelperMapper;
import cn.lmx.kpu.userinfo.dao.UserHelperMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户相关 帮助类
 *
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class UserHelperService {

    private final UserHelperMapper userHelperMapper;
    private final OrgHelperMapper orgHelperMapper;
    private final StationHelperMapper stationHelperMapper;

    public SysUser getUserByIdCache(Long userId) {
        ContextUtil.setDatabaseBase();
        return userHelperMapper.selectById(userId);
    }

    public SysOrg getOrgByIdCache(Long orgId) {
        ContextUtil.setDatabaseBase();
        return orgHelperMapper.selectById(orgId);
    }

    public SysStation getStationByIdCache(Long positionId) {
        ContextUtil.setDatabaseBase();
        return stationHelperMapper.selectById(positionId);
    }


}
