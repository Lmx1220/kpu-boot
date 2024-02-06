package cn.lmx.kpu.system.mapper.tenant;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.kpu.system.entity.tenant.DefUser;
import cn.lmx.kpu.system.vo.query.tenant.DefUserPageQuery;
import cn.lmx.kpu.system.vo.result.tenant.DefUserResultVO;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * 用户
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefUserMapper extends SuperMapper<DefUser> {

    /**
     * 查找同一企业下的用户
     *
     * @param pageQuery 参数
     * @param page      分页参数
     * @return
     */
    IPage<DefUserResultVO> pageUser(@Param("param") DefUserPageQuery pageQuery, IPage<DefUser> page);

    /**
     * 递增 密码错误次数
     *
     * @param id  用户id
     * @param now 当前时间
     * @return 被修改了几行数据
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    int incrPasswordErrorNumById(@Param("id") Long id, @Param("now") LocalDateTime now);

    /**
     * 重置 密码错误次数
     *
     * @param id  用户id
     * @param now 当前时间
     * @return 被修改了几行数据
     */
    int resetPassErrorNum(@Param("id") Long id, @Param("now") LocalDateTime now);

}
