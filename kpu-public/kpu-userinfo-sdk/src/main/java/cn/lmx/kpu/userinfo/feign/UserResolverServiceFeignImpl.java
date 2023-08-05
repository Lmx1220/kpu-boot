package cn.lmx.kpu.userinfo.feign;

import cn.lmx.basic.base.R;
import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.model.entity.base.SysResource;
import cn.lmx.kpu.model.entity.base.SysStation;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.model.vo.query.ResourceQueryDTO;
import cn.lmx.kpu.userinfo.service.ResourceHelperService;
import cn.lmx.kpu.userinfo.service.RoleHelperService;
import cn.lmx.kpu.userinfo.service.UserHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@RequiredArgsConstructor
@Service
public class UserResolverServiceFeignImpl implements UserResolverService {
    private final UserHelperService userHelperService;
    private final ResourceHelperService resourceHelperService;
    private final RoleHelperService roleHelperService;

    @Override
    public R<SysUser> getById(UserQuery query) {
        Long userId = query.getUserId();
        SysUser sysUser = userHelperService.getUserByIdCache(userId);
        if (sysUser == null) {
            throw BizException.wrap("无法获取用户信息");
        }
        sysUser.setOrgId(sysUser.getOrgId());
        sysUser.setStationId(sysUser.getStationId());

        if (query.getFull() || query.getOrg()) {
            sysUser.setOrg(userHelperService.getOrgByIdCache(sysUser.getOrgId()));
        }

        if (query.getFull() || query.getStation()) {
            SysStation station = userHelperService.getStationByIdCache(sysUser.getStationId());
            if (station != null) {
                sysUser.setStation(station);
            }
        }

        if (query.getFull() || query.getRoles()) {
            List<String> list = roleHelperService.findRoleCodeByUserId(userId);
            sysUser.setRoles(list);
        }
        if (query.getFull() || query.getResource()) {
            List<SysResource> resourceList = resourceHelperService.findVisibleAuth(ResourceQueryDTO.builder().userId(userId).build());
            sysUser.setResources(CollHelper.split(resourceList, SysResource::getCode, StrPool.SEMICOLON));
        }

        return R.success(sysUser);
    }
}
