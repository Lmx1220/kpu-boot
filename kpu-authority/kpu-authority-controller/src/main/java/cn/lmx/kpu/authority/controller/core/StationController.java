package cn.lmx.kpu.authority.controller.core;

import cn.hutool.core.convert.Convert;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.authority.dto.core.StationPageQuery;
import cn.lmx.kpu.authority.dto.core.StationResultVO;
import cn.lmx.kpu.authority.dto.core.StationSaveVO;
import cn.lmx.kpu.authority.dto.core.StationUpdateVo;
import cn.lmx.kpu.authority.entity.core.Station;
import cn.lmx.kpu.authority.service.core.StationService;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.userinfo.service.UserHelperService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.lmx.kpu.common.constant.SwaggerConstants.*;

/**
 * <p>
 * 前端控制器
 * 岗位
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RestController
@RequestMapping("/station")
@Api(value = "Station", tags = "岗位")
@PreAuth(replace = "authority:station:")
@RequiredArgsConstructor
public class StationController extends SuperCacheController<StationService, Long, Station, StationSaveVO, StationUpdateVo, StationPageQuery, StationResultVO> {
    private final UserHelperService userHelperService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "name", value = "名称", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "检测名称是否可用", notes = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam String name) {
        return success(baseService.check(id, name));
    }

    @Override
    public R<Station> handlerSave(StationSaveVO model) {
        SysUser sysUser = userHelperService.getUserByIdCache(ContextUtil.getUserId());
        if (sysUser != null) {
            model.setCreatedOrgId(sysUser.getOrgId());
        }
        return super.handlerSave(model);
    }

    @Override
    public IPage<Station> query(PageParams<StationPageQuery> params) {
        IPage<Station> page = params.buildPage(Station.class);
        baseService.findStationPage(page, params);
        return page;
    }

    @Override
    public R<Boolean> handlerImport(List<Map<String, String>> list) {
        // 组织id 和 状态需要自行转义，可以参考UserController里面的用户导入如何转义
        List<Station> stationList = list.stream().map((map) -> {
            Station item = new Station();
            item.setDescribe(map.getOrDefault("描述", ""));
            item.setName(map.getOrDefault("名称", ""));
            item.setOrgId(Convert.toLong(map.getOrDefault("组织", "0")));
            item.setState(Convert.toBool(map.getOrDefault("状态", "false")));
            ArgumentAssert.notEmpty(item.getName(), "请填入岗位名称");
            return item;
        }).collect(Collectors.toList());

        return R.success(baseService.saveBatch(stationList));
    }

}
