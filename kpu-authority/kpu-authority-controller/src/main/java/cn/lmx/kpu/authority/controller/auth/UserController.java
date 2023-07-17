package cn.lmx.kpu.authority.controller.auth;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.LbqWrapper;
import cn.lmx.basic.database.mybatis.conditions.query.QueryWrap;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.authority.controller.poi.ExcelUserVerifyHandlerImpl;
import cn.lmx.kpu.authority.controller.poi.UserExcelDictHandlerImpl;
import cn.lmx.kpu.authority.dto.auth.*;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.entity.auth.UserRole;
import cn.lmx.kpu.authority.entity.core.Org;
import cn.lmx.kpu.authority.service.auth.RoleAuthorityService;
import cn.lmx.kpu.authority.service.auth.UserRoleService;
import cn.lmx.kpu.authority.service.auth.UserService;
import cn.lmx.kpu.authority.service.core.OrgService;
import cn.lmx.kpu.common.constant.BizConstant;
import cn.lmx.kpu.file.service.AppendixService;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.userinfo.service.UserHelperService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.lmx.kpu.common.constant.SwaggerConstants.*;


/**
 * <p>
 * 前端控制器
 * 用户
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@Api(value = "User", tags = "用户")
@PreAuth(replace = "authority:user:")
@RequiredArgsConstructor
public class UserController extends SuperCacheController<UserService, Long, User, UserSaveVO, UserUpdateVo, UserPageQuery, UserResultVO> {
    private final OrgService orgService;
    private final EchoService echoService;
    private final AppendixService appendixService;
    private final ExcelUserVerifyHandlerImpl excelUserVerifyHandler;
    private final UserExcelDictHandlerImpl userExcelDictHandlerIImpl;
    private final UserHelperService userHelperService;
    private final RoleAuthorityService roleAuthorityService;
    private final UserRoleService userRoleService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "name", value = "名称", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "检测名称是否可用", notes = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam String name) {
        return success(baseService.check(id, name));
    }

    /**
     * 重写保存逻辑
     *
     * @param data 用户DTO
     * @return 数据
     */
    @Override
    public R<User> handlerSave(UserSaveVO data) {
        User user = BeanUtil.toBean(data, User.class);
        user.setReadonly(false);
        SysUser sysUser = userHelperService.getUserByIdCache(ContextUtil.getUserId());
        if (sysUser != null) {
            data.setCreatedOrgId(sysUser.getOrgId());
        }
        baseService.saveUser(user);
        return success(user);
    }

    /**
     * 重写删除逻辑
     *
     * @param ids 用户id
     * @return 是否成功
     */
    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        baseService.remove(ids);
        return success(true);
    }

    /**
     * 重写修改逻辑
     *
     * @param data 用户
     * @return 用户
     */
    @Override
    public R<User> handlerUpdate(UserUpdateVo data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.updateUser(user);
        return success(user);
    }

    /**
     * 修改
     *
     * @param data 用户基础信息
     * @return 用户
     */
    @ApiOperation(value = "修改基础信息")
    @PutMapping("/base")
    @SysLog(value = "'修改基础信息:' + #data?.id", request = false)
    @PreAuth("hasAnyPermission('{}edit')")
    public R<User> updateBase(@RequestBody @Validated({SuperEntity.Update.class}) UserUpdateBaseInfoDTO data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.updateById(user);
        return success(user);
    }

    /**
     * 修改头像
     *
     * @param data 用户头像信息
     * @return 用户
     */
    @ApiOperation(value = "修改头像", notes = "修改头像")
    @PutMapping("/avatar")
    @SysLog("'修改头像:' + #p0.id")
    public R<Boolean> avatar(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateAvatarDTO data) {
        return success(baseService.updateAvatar(data));
    }

    /**
     * 修改密码
     *
     * @param data 修改实体
     * @return 是否成功
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("/password")
    @SysLog("'修改密码:' + #p0.id")
    public R<Boolean> updatePassword(@RequestBody @Validated(SuperEntity.Update.class) UserUpdatePasswordDTO data) {
        return success(baseService.updatePassword(data));
    }

    /**
     * 重置密码
     *
     * @param data 用户重置密码信息
     * @return 是否成功
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping("/reset")
    @SysLog("'重置密码:' + #data.id")
    public R<Boolean> reset(@RequestBody @Validated(SuperEntity.Update.class) UserUpdatePasswordDTO data) {
        baseService.reset(data);
        return success();
    }

    /**
     * 查询角色的已关联用户
     *
     * @param userId 用户id
     * @return 角色已关联用户
     */
    @ApiOperation(value = "查询角色的已关联用户", notes = "查询角色的已关联用户")
    @GetMapping(value = "/roleList")
    public R<List<Long>> findRoleIdByUserId(@RequestParam("userId") Long userId) {
        List<UserRole> list = userRoleService.list(Wraps.<UserRole>lbQ().eq(UserRole::getUserId, userId));
        return success(list.stream().mapToLong(UserRole::getRoleId).boxed().collect(Collectors.toList()));
    }

    /**
     * 给用户分配角色
     *
     * @param userRole 用户角色授权对象
     * @return 新增结果
     */
    @ApiOperation(value = "给用户分配角色", notes = "给用户分配角色")
    @PostMapping("/saveUserRole")
    @SysLog("给用户分配角色")
    public R<List<Long>> saveUserRole(@RequestBody UserRoleSaveVO userRole) {
        return success(roleAuthorityService.saveUserRole(userRole));
    }


    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/find")
    @SysLog("查询所有用户")
    public R<List<Long>> findAllUserId() {
        return success(baseService.findAllUserId());
    }

    @ApiOperation(value = "查询所有用户实体", notes = "查询所有用户实体")
    @GetMapping("/findAll")
    @SysLog("查询所有用户")
    public R<List<User>> findAll() {
        List<User> res = baseService.list();
        res.forEach(obj -> obj.setPassword(null));
        return success(res);
    }

    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    public R<List<User>> findUserById(@RequestParam(value = "ids") List<Long> ids) {
        return this.success(baseService.findUserById(ids));
    }

    @Override
    public Class<?> getExcelClass() {
        return UserExcelVO.class;
    }

    @Override
    public List<?> findExportList(PageParams<UserPageQuery> params) {
        return super.findExportList(params);
    }

    @Override
    public void enhanceExportParams(ExportParams ep) {
        ep.setDictHandler(userExcelDictHandlerIImpl);
    }

    @Override
    public R<Boolean> importExcel(@RequestParam("file") MultipartFile simpleFile, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);
        params.setVerifyGroup(new Class[]{Default.class});
        // 下面的2个handler只能支持少量数据导入，因为每一条数据都会执行N次查询。有谁能提供优雅解决方式，欢迎PR
        params.setVerifyHandler(excelUserVerifyHandler);
        params.setDictHandler(userExcelDictHandlerIImpl);

        ExcelImportResult<UserExcelVO> result = ExcelImportUtil.importExcelMore(simpleFile.getInputStream(), UserExcelVO.class, params);

        if (result.isVerifyFail()) {
            // 更加优雅的方式，应该是让用户下载错误的数据， 有谁能提供优雅解决方式，欢迎PR
            return R.validFail(result.getFailList().stream()
                    .map(item -> StrUtil.format("第{}行检验错误: {}", item.getRowNum(), item.getErrorMsg()))
                    .collect(Collectors.joining("<br/>")));
        }

        List<UserExcelVO> list = result.getList();
        if (list.isEmpty()) {
            return this.validFail("导入数据不能为空");
        }

        Set<String> usernames = new HashSet<>();
        List<User> userList = list.stream().map(item -> {
            ArgumentAssert.notContain(usernames, item.getUsername(), "Excel中存在重复的账号: {}", item.getUsername());

            usernames.add(item.getUsername());
            User user = new User();
            BeanUtil.copyProperties(item, user);
            user.setSalt(RandomUtil.randomString(20));
            user.setPassword(SecureUtil.sha256(BizConstant.DEF_PASSWORD + user.getSalt()));
            return user;
        }).collect(Collectors.toList());

        baseService.saveBatch(userList);
        return this.success(true);
    }

    /**
     * 分页、导出、导出预览 方法的共用查询条件
     *
     * @param params 分页参数
     */
    @Override
    public IPage<User> query(PageParams<UserPageQuery> params) {
        IPage<User> page = params.buildPage(User.class);
        UserPageQuery userPage = params.getModel();

        QueryWrap<User> wrap = handlerWrapper(null, params);

        LbqWrapper<User> wrapper = wrap.lambda();
        if (userPage.getOrgId() != null && userPage.getOrgId() > 0) {
            List<Org> children = orgService.findChildren(Arrays.asList(userPage.getOrgId()));
            wrapper.in(User::getOrgId, children.stream().map(Org::getId).collect(Collectors.toList()));
        }
        wrapper.like(User::getNickName, userPage.getNickName())
                .like(User::getUsername, userPage.getUsername())
                .like(User::getEmail, userPage.getEmail())
                .like(User::getMobile, userPage.getMobile())
                .eq(User::getStationId, userPage.getStationId())
                .in(User::getPositionStatus, userPage.getPositionStatus())
                .in(User::getEducation, userPage.getEducation())
                .in(User::getNation, userPage.getNation())
                .in(User::getSex, userPage.getSex())
                .eq(User::getState, userPage.getState());

        if (StrUtil.equalsAny(userPage.getScope(), BizConstant.SCOPE_BIND, BizConstant.SCOPE_UN_BIND) && userPage.getRoleId() != null) {
            String sql = " select ura.user_id from c_user_role ura where ura.user_id = s.id \n" +
                    "  and ura.role_id =   " + userPage.getRoleId();
            if (BizConstant.SCOPE_BIND.equals(userPage.getScope())) {
                wrapper.inSql(User::getId, sql);
            } else {
                wrapper.notInSql(User::getId, sql);
            }
        }

        baseService.findPage(page, wrapper);

        page.getRecords().forEach(item -> {
            item.setPassword(null);
            item.setSalt(null);
        });

        appendixService.echoAppendix(page);

        return page;
    }

    @ApiOperation(value = "分页查询所有用户", notes = "分页查询所有用户")
    @PostMapping("/pageAll")
    @SysLog(value = "'分页列表查询:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<IPage<User>> pageAll(@RequestBody @Validated PageParams<UserPageQuery> params) {
        IPage<User> page = params.buildPage(User.class);
        UserPageQuery userPage = params.getModel();

        QueryWrap<User> wrap = handlerWrapper(null, params);

        LbqWrapper<User> wrapper = wrap.lambda();
        if (userPage.getOrgId() != null && userPage.getOrgId() > 0) {
            List<Org> children = orgService.findChildren(Arrays.asList(userPage.getOrgId()));
            wrapper.in(User::getOrgId, children.stream().map(Org::getId).collect(Collectors.toList()));
        }
        wrapper.like(User::getNickName, userPage.getNickName())
                .like(User::getUsername, userPage.getUsername())
                .like(User::getEmail, userPage.getEmail())
                .like(User::getMobile, userPage.getMobile())
                .eq(User::getStationId, userPage.getStationId())
                .in(User::getPositionStatus, userPage.getPositionStatus())
                .in(User::getEducation, userPage.getEducation())
                .in(User::getNation, userPage.getNation())
                .in(User::getSex, userPage.getSex())
                .eq(userPage.getState() != null, User::getState, userPage.getState())
                .between(params.getExtra().get("createTime_st") != null && params.getExtra().get("createTime_en") != null,
                        User::getCreateTime, params.getExtra().get("createTime_st"), params.getExtra().get("createTime_en"));
        ;

        if (StrUtil.equalsAny(userPage.getScope(), BizConstant.SCOPE_BIND, BizConstant.SCOPE_UN_BIND) && userPage.getRoleId() != null) {
            String sql = " select ura.user_id from c_user_role ura where ura.user_id = s.id \n" +
                    "  and ura.role_id =   " + userPage.getRoleId();
            if (BizConstant.SCOPE_BIND.equals(userPage.getScope())) {
                wrapper.inSql(User::getId, sql);
            } else {
                wrapper.notInSql(User::getId, sql);
            }
        }

        baseService.findPage(page, wrapper);
        // 手动注入
        echoService.action(page);

        page.getRecords().forEach(item -> {
            item.setPassword(null);
            item.setSalt(null);
        });
        return R.success(page);
    }

}
