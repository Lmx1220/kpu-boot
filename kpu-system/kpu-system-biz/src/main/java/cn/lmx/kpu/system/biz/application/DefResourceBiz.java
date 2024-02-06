package cn.lmx.kpu.system.biz.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.kpu.system.service.application.DefResourceService;

import java.util.List;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DefResourceBiz {
    private final DefResourceService defResourceService;

    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        boolean result = defResourceService.removeByIdWithCache(ids);

        // 删除 角色资源关系表 员工资源关系表
        defResourceService.deleteRoleResourceRelByResourceId(ids);
        return result;
    }


}
