package cn.lmx.kpu.datascope.service;

import cn.hutool.core.collection.CollUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lmx.kpu.datascope.entity.DefResourceDataScope;
import cn.lmx.kpu.datascope.mapper.DataScopeMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 AM
 * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataScopeService {
    private final DataScopeMapper dataScopeMapper;

    /**
     * 根据员工拥有的数据权限ID，查询指定路径下的数据权限
     *
     * @param applicationId   应用ID
     * @param path            当前访问的页面路径
     * @param dataScopeIdList 员工拥有的数据权限ID
     * @return cn.lmx.kpu.datascope.entity.DefResourceDataScope
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     */
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public DefResourceDataScope getDataScopeByPath(Long applicationId, String path, List<Long> dataScopeIdList) {
        List<DefResourceDataScope> dataScopeByPaths = null;
        // 先根据 员工拥有的数据权限(dataScopeIdList) 进行查询
        if (CollUtil.isNotEmpty(dataScopeIdList)) {
            dataScopeByPaths = dataScopeMapper.findDataScopeByPath(applicationId, path, dataScopeIdList);
        }
        // 若没有未员工分配任何数据权限，则查询默认的数据权限。若没有配置默认的数据权限，则取 sortValue 值最小的数据权限
        if (CollUtil.isEmpty(dataScopeByPaths)) {
            dataScopeByPaths = dataScopeMapper.findDefDataScopeByPath(applicationId, path);
        }
        return CollUtil.isNotEmpty(dataScopeByPaths) ? dataScopeByPaths.get(0) : null;
    }

    /**
     * 查询员工拥有的数据权限
     *
     * @param employeeId 员工ID
     * @param category   角色类型
     * @return java.util.List<java.lang.Long>
     * @author lmx
     * @date 2024/02/07  02:04 AM
     * @create [2024/02/07  02:04 AM ] [lmx] [初始创建]
     */
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Long> selectDataScopeIdByEmployeeId(Long employeeId, String category) {
        List<Long> list = new ArrayList<>();
        // 员工 -> 角色 -> 资源
        List<Long> ids1 = dataScopeMapper.selectDataScopeIdFromRoleByEmployeeId(employeeId, category);
        // 员工 -> 机构 -> 角色 -> 资源
        List<Long> ids2 = dataScopeMapper.selectDataScopeIdFromOrgByEmployeeId(employeeId, category);
        list.addAll(ids1);
        list.addAll(ids2);
        return list;
    }
}
