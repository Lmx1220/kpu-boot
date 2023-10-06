package cn.lmx.kpu.tenant.auth;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @author lmx
 * @version 1.0
 * @description: mybatis 数据权限拦截器 联系作者购买企业版后，获取完整功能源码
 * @date 2023/7/4 14:27
 */
@Slf4j
public class DataScopeInnerInterceptor implements InnerInterceptor {
//    private final Function<Long, Map<String, Object>> function;


    /**
     * 查找参数是否包括DataScope对象
     *
     * @param parameterObj 参数列表
     * @return DataScope
     */


    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
//        try {
//            DataScope dataScope = findDataScope(parameter).orElse(null);
//            if (dataScope == null) {
//                return;
//            }
//            // 联系作者购买企业版后，获取完整功能源码
//            String originalSql = boundSql.getSql();
//
//            String scopeName = dataScope.getScopeName();
//            String selfScopeName = dataScope.getSelfScopeName();
//            Long userId = dataScope.getUserId() == null ? ContextUtil.getUserId() : dataScope.getUserId();
//            List<Long> orgIds = dataScope.getOrgIds();
//            DataScopeType dsType = DataScopeType.SELF;
//            if (CollectionUtil.isEmpty(orgIds)) {
//                //查询当前用户的 角色 最小权限
//                //userId
//                //dsType orgIds
//                Map<String, Object> result = SpringUtils.getBean(UserService.class).getDataScopeById(userId);
//                if (result == null) {
//                    return;
//                }
//
//                Integer type = (Integer) result.get("dsType");
//                dsType = DataScopeType.get(type);
//                orgIds = (List<Long>) result.get("orgIds");
//            }
//
//            //查全部
//            if (DataScopeType.ALL.eq(dsType)) {
//                return;
//            }
//            //查个人
//            if (DataScopeType.SELF.eq(dsType)) {
//                originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + selfScopeName + " = " + userId;
//            }
//            //查其他
//            else if (StrUtil.isNotBlank(scopeName) && CollUtil.isNotEmpty(orgIds)) {
//                String join = CollectionUtil.join(orgIds, ",");
//                originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + scopeName + " in (" + join + ")";
//            }
//
//            PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
//            mpBoundSql.sql(originalSql);
//        } finally {
//            DataScopeHelper.clearDataScope();
//        }
    }

}
