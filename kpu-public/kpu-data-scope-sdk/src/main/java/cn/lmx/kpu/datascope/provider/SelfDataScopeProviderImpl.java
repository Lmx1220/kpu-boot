package cn.lmx.kpu.datascope.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.kpu.datascope.model.DataFieldProperty;

import java.util.Collections;
import java.util.List;

/**
 * 个人的数据
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Slf4j
@RequiredArgsConstructor
@Component("DATA_SCOPE_06")
public class SelfDataScopeProviderImpl implements DataScopeProvider {

    @Override
    public List<DataFieldProperty> findDataFieldProperty(List<DataFieldProperty> fsp) {
        List<Long> employeeIdList = Collections.singletonList(ContextUtil.getUserId());
        fsp.forEach(item -> {
            item.setField(SuperEntity.CREATED_BY_FIELD);
            item.setValues(employeeIdList);
        });
        return fsp;
    }
}
