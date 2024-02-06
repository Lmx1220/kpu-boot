package cn.lmx.kpu.system.manager.tenant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.basic.interfaces.echo.LoadService;
import cn.lmx.kpu.system.entity.tenant.DefUser;
import cn.lmx.kpu.system.vo.query.tenant.DefUserPageQuery;
import cn.lmx.kpu.system.vo.result.tenant.DefUserResultVO;

/**
 * <p>
 * 通用业务层
 * 用户
 * </p>
 *
 * @author lmx
 * @version v1.0
 * @date 2024/02/07  02:04 下午
 * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
 */
public interface DefUserManager extends SuperCacheManager<DefUser>, LoadService {

    /**
     * 重置密码错误次数
     *
     * @param id 用户id
     * @return 重置了多少行
     */
    int resetPassErrorNum(Long id);

    /**
     * 修改输错密码的次数
     *
     * @param id 用户Id
     */
    void incrPasswordErrorNumById(Long id);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    DefUser getUserByUsername(String username);

    /**
     * 查找同一企业下的用户
     *
     * @param pageQuery 参数
     * @param page      分页参数
     * @return
     */
    IPage<DefUserResultVO> pageUser(DefUserPageQuery pageQuery, IPage<DefUser> page);


    /**
     * 检测用户名是否可用
     *
     * @param username 用户名
     * @param id       用户id
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    boolean checkUsername(String username, Long id);

    /**
     * 检测 邮箱 是否可用
     *
     * @param email 邮箱
     * @param id    用户id
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    boolean checkEmail(String email, Long id);

    /**
     * 检测 手机号 是否可用
     *
     * @param mobile 手机号
     * @param id     用户id
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    boolean checkMobile(String mobile, Long id);

    /**
     * 检测 身份证 是否可用
     *
     * @param idCard 身份证
     * @param id     用户id
     * @return boolean
     * @author lmx
     * @date 2024/02/07  02:04 下午
     * @create [2024/02/07  02:04 下午 ] [lmx] [初始创建]
     */
    boolean checkIdCard(String idCard, Long id);

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return
     */
    DefUser getUserByEmail(String email);

    /**
     * 根据身份证查询用户
     *
     * @param idCard 身份证
     * @return
     */
    DefUser getUserByIdCard(String idCard);


    /**
     * 根据手机号查询用户
     *
     * @param mobile 手机号
     * @return
     */
    DefUser getUserByMobile(String mobile);
}
