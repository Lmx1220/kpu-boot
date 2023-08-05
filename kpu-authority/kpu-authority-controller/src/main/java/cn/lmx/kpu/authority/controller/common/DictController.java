package cn.lmx.kpu.authority.controller.common;


import cn.hutool.core.util.ArrayUtil;
import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperPoiController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.QueryWrap;
import cn.lmx.kpu.authority.dto.common.DictPageQuery;
import cn.lmx.kpu.authority.dto.common.DictResultVO;
import cn.lmx.kpu.authority.dto.common.DictSaveVO;
import cn.lmx.kpu.authority.dto.common.DictUpdateVo;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.authority.service.common.DictService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/dict")
@Api(value = "Dict", tags = "字典")
@PreAuth(replace = "authority:dictionary:")
@RequiredArgsConstructor
public class DictController
        extends SuperPoiController<DictService, Long, Dict, DictSaveVO, DictUpdateVo, DictPageQuery, DictResultVO> {

    @Override
    public QueryWrap<Dict> handlerWrapper(Dict model, PageParams<DictPageQuery> params) {
        QueryWrap<Dict> qw = Wraps.q(null, params.getExtra(), getEntityClass());
        qw.lambda().likeRight(Dict::getName, params.getModel().getName());
        qw.lambda().likeRight(Dict::getKey, params.getModel().getKey());
        qw.lambda().in(ArrayUtil.isNotEmpty(params.getModel().getClassify()), Dict::getClassify, params.getModel().getClassify());
        qw.lambda().in(ArrayUtil.isNotEmpty(params.getModel().getState()), Dict::getState, params.getModel().getState());
        qw.lambda().eq(Dict::getParentId, 0);
        return qw;
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        this.superService.removeByIds(ids);
        return this.success(true);
    }
}
