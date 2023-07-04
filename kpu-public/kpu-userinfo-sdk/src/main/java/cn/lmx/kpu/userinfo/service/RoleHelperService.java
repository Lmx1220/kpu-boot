package cn.lmx.kpu.userinfo.service;

import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.userinfo.dao.RoleHelperMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class RoleHelperService {
    private final RoleHelperMapper roleHelperMapper;

    /**
     * 检查某个员工是否拥有某角色
     *
     * @param employeeId employeeId
     * @param codes      codes
     * @return boolean
     * @author lmx
     * @date 2023/7/4 14:27
     * @create [2023/7/4 14:27 ] [lmx] [初始创建]
     */
    public boolean checkRole(Long employeeId, String... codes) {
        ContextUtil.setDatabaseBase();
        ArgumentAssert.notEmpty(codes, "请传递角色编码");
        long count = roleHelperMapper.countRoleFormRole(Arrays.asList(codes), employeeId);
        return count > 0;
    }

    public List<Long> listResourceId(Long employeeId) {
        ContextUtil.setDatabaseBase();
        return roleHelperMapper.selectResourceIdFromRoleByUserId(employeeId);
    }

    public List<String> findRoleCodeByUserId(Long userId) {
        ContextUtil.setDatabaseBase();
        return roleHelperMapper.selectRoleCodeFromRoleByUserId(userId);
    }
}
