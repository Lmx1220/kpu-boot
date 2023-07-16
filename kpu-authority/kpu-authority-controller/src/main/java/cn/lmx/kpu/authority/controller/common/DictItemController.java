package cn.lmx.kpu.authority.controller.common;


import cn.hutool.core.util.ArrayUtil;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.controller.SuperNoPoiController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.QueryWrap;
import cn.lmx.kpu.authority.dto.common.DictItemPageQuery;
import cn.lmx.kpu.authority.dto.common.DictItemResultVO;
import cn.lmx.kpu.authority.dto.common.DictItemSaveVO;
import cn.lmx.kpu.authority.dto.common.DictItemUpdateVO;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.authority.service.common.DictService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 字典类型
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictItem")
@Api(value = "dictItem", tags = "字典项")
@PreAuth(replace = "authority:dict:")
@RequiredArgsConstructor
public class DictItemController
        extends SuperNoPoiController<DictService, Long, Dict, DictItemSaveVO, DictItemUpdateVO, DictItemPageQuery, DictItemResultVO> {
    @Override
    public QueryWrap<Dict> handlerWrapper(Dict model, PageParams<DictItemPageQuery> params) {
        QueryWrap<Dict> qw = Wraps.q(model, params.getExtra(), getEntityClass());
        qw.lambda().in(ArrayUtil.isNotEmpty(params.getModel().getClassify()), Dict::getClassify, params.getModel().getClassify());
        qw.lambda().in(ArrayUtil.isNotEmpty(params.getModel().getState()), Dict::getState, params.getModel().getState());
        qw.lambda()
                // 忽略 Wraps.q(model); 时， sortValue 字段的默认查询规则，
                .ignore(Dict::setSortValue);
        return qw;
    }

}
