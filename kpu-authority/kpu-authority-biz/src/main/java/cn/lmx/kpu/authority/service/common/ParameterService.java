package cn.lmx.kpu.authority.service.common;

import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.authority.entity.common.Parameter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 参数配置
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface ParameterService extends SuperService<Parameter> {
    /**
     * 根据参数键查询参数值
     *
     * @param key    参数键
     * @param defVal 参数值
     * @return 参数值
     */
    String getValue(String key, String defVal);

    /**
     * 根据参数键查询参数值
     *
     * @param keys 参数键
     * @return 参数值
     */
    Map<String, String> findParams(List<String> keys);
}
