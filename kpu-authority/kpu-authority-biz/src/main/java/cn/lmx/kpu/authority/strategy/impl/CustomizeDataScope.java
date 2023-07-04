package cn.lmx.kpu.authority.strategy.impl;

import cn.lmx.basic.exception.BizException;
import cn.lmx.basic.exception.code.ExceptionCode;
import cn.lmx.kpu.authority.strategy.AbstractDataScopeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义模式
 *
 * @author lmx
 * @version 1.0
 * @date 2023/7/4 14:27
 */
@Component("CUSTOMIZE")
@RequiredArgsConstructor
public class CustomizeDataScope implements AbstractDataScopeHandler {

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        if (orgList == null || orgList.isEmpty()) {
            throw new BizException(ExceptionCode.BASE_VALID_PARAM.getCode(), "自定义数据权限类型时，组织不能为空");
        }
        return orgList;
    }
}
