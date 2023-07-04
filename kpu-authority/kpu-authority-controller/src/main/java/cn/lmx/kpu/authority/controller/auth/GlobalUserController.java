package cn.lmx.kpu.authority.controller.auth;


import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.basic.utils.StrHelper;
import cn.lmx.kpu.authority.dto.auth.GlobalUserPageDTO;
import cn.lmx.kpu.authority.dto.auth.GlobalUserSaveDTO;
import cn.lmx.kpu.authority.dto.auth.GlobalUserUpdateDTO;
import cn.lmx.kpu.authority.dto.auth.UserUpdatePasswordDTO;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.service.auth.UserService;
import cn.lmx.kpu.common.constant.BizConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.lmx.kpu.common.constant.SwaggerConstants.DATA_TYPE_STRING;
import static cn.lmx.kpu.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;

/**
 * <p>
 * 前端控制器
 * 全局账号
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/globalUser")
@Api(value = "GlobalUser", tags = "全局账号")
@SysLog(enabled = false)
@RequiredArgsConstructor
public class GlobalUserController extends SuperController<UserService, Long, User, GlobalUserPageDTO, GlobalUserSaveDTO, GlobalUserUpdateDTO> {

    @Override
    public R<User> handlerSave(GlobalUserSaveDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        User user = BeanPlusUtil.toBean(model, User.class);
        user.setName(StrHelper.getOrDef(model.getName(), model.getAccount()));
        if (StrUtil.isEmpty(user.getPassword())) {
            user.setPassword(BizConstant.DEF_PASSWORD);
        }
        user.setState(true);
        baseService.initUser(user);
        return success(user);
    }

    @Override
    public R<User> handlerUpdate(GlobalUserUpdateDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        User user = BeanPlusUtil.toBean(model, User.class);
        baseService.updateUser(user);
        return success(user);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "account", value = "账号", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "检测账号是否可用", notes = "检测账号是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String tenantCode, @RequestParam String account) {
        ContextUtil.setTenant(tenantCode);
        return success(baseService.check(null, account));
    }

    @Override
    public IPage<User> query(PageParams<GlobalUserPageDTO> params) {
        IPage<User> page = params.buildPage(User.class);
        GlobalUserPageDTO model = params.getModel();
        ContextUtil.setTenant(model.getTenantCode());

        baseService.pageByRole(page, params);

        page.getRecords().forEach(item -> {
            item.setPassword(null);
            item.setSalt(null);
        });
        return page;
    }


    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "ids[]", value = "主键id", dataType = DATA_TYPE_STRING, allowMultiple = true, paramType = PARAM_TYPE_QUERY),
    })
    public R<Boolean> delete(@RequestParam String tenantCode, @RequestParam("ids[]") List<Long> ids) {
        ContextUtil.setTenant(tenantCode);
        return success(baseService.remove(ids));
    }


    /**
     * 修改密码
     *
     * @param model 修改实体
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("/reset")
    public R<Boolean> updatePassword(@RequestBody @Validated(SuperEntity.Update.class) UserUpdatePasswordDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        return success(baseService.reset(model));
    }
}
