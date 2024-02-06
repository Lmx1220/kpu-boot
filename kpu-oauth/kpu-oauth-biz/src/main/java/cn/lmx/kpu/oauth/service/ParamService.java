package cn.lmx.kpu.oauth.service;

import cn.lmx.basic.interfaces.echo.LoadService;

import java.util.List;
import java.util.Map;

/**
 * 字典查询服务
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
public interface ParamService extends LoadService {
    /**
     * 根据参数key查参数值
     * <p>
     * 1. 先查询租户自己的参数。
     * 2. 若不存在，则查询系统默认的参数。
     *
     * @param paramsKeys 参数key
     * @return key： 参数key  value: 参数值
     */
    Map<String, String> findParamMapByKey(List<String> paramsKeys);
}
