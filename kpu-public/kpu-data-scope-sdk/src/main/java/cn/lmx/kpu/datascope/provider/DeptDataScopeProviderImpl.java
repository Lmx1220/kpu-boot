package cn.lmx.kpu.datascope.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.kpu.datascope.model.DataFieldProperty;
import cn.lmx.kpu.datascope.service.OrgHelperService;

import java.util.Collections;
import java.util.List;

/**
 * 本部门
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Slf4j
@RequiredArgsConstructor
@Component("DATA_SCOPE_05")
public class DeptDataScopeProviderImpl implements DataScopeProvider {
    @Autowired
    private OrgHelperService orgHelperService;

    @Override
    public List<DataFieldProperty> findDataFieldProperty(List<DataFieldProperty> fsp) {
        Long mainDeptId = orgHelperService.getMainDeptIdByEmployeeId(ContextUtil.getEmployeeId());
        if (mainDeptId == null) {
            return Collections.emptyList();
        }
        List<Long> orgIdList = Collections.singletonList(mainDeptId);
        fsp.forEach(item -> {
            item.setField(SuperEntity.CREATED_ORG_ID_FIELD);
            item.setValues(orgIdList);
        });
        return fsp;
    }
}
