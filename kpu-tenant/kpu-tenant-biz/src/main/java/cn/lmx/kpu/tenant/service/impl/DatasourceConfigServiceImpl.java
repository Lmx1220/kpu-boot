package cn.lmx.kpu.tenant.service.impl;


import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.constant.Constants;
import cn.lmx.basic.exception.ArgumentException;
import cn.lmx.kpu.tenant.dto.DatasourceConfigPageQuery;
import cn.lmx.kpu.tenant.dto.DatasourceConfigResultVO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigSaveVO;
import cn.lmx.kpu.tenant.dto.DatasourceConfigUpdateVo;
import cn.lmx.kpu.tenant.entity.DatasourceConfig;
import cn.lmx.kpu.tenant.manager.DatasourceConfigManager;
import cn.lmx.kpu.tenant.service.DatasourceConfigService;
import cn.lmx.kpu.tenant.utils.DriverEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DatasourceConfigServiceImpl extends SuperServiceImpl<DatasourceConfigManager, Long, DatasourceConfig, DatasourceConfigSaveVO, DatasourceConfigUpdateVo, DatasourceConfigPageQuery, DatasourceConfigResultVO> implements DatasourceConfigService {


    @Override
    public boolean testConnect(Long id) {
        DatasourceConfig datasourceConfig = this.superManager.getById(id);
        if (datasourceConfig == null) {
            return false;
        }

        DriverEnum dbTypeEnum = DriverEnum.findEnumByType(datasourceConfig.getDriverClassName());
        if (dbTypeEnum == null) {
            throw new ArgumentException("不识别的类型");
        } else {
            testConnectionDriver(datasourceConfig, dbTypeEnum.getDriver());
        }
        return false;
    }

    private void testConnectionDriver(DatasourceConfig datasourceConfig, String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new ArgumentException("注册驱动失败");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(datasourceConfig.getUrl(), datasourceConfig.getUsername(), datasourceConfig.getPassword());
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(Constants.TEST_CONNECT_SQL);
            int i = 0;
            while (re.next()) {
                i++;
                //System.out.println(re.getString(1));
            }
            re.close();
            stat.close();
            conn.close();
            if (i == 0) {
                throw new ArgumentException("该连接下没有库");
            }
        } catch (SQLException e) {
            throw new ArgumentException("连接数据库失败");
        }
    }

}
