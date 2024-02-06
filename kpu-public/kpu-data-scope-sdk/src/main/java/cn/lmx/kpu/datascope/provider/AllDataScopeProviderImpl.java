package cn.lmx.kpu.datascope.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import cn.lmx.kpu.datascope.model.DataFieldProperty;

import java.util.Collections;
import java.util.List;

/**
 * 全部
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Slf4j
@RequiredArgsConstructor
@Component("DATA_SCOPE_01")
public class AllDataScopeProviderImpl implements DataScopeProvider {

    @Override
    public List<DataFieldProperty> findDataFieldProperty(List<DataFieldProperty> fsp) {
        return Collections.emptyList();
    }
}
