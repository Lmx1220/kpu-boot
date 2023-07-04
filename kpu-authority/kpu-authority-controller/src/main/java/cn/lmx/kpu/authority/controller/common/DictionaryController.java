package cn.lmx.kpu.authority.controller.common;


import cn.lmx.basic.annotation.security.PreAuth;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.database.mybatis.conditions.query.QueryWrap;
import cn.lmx.kpu.authority.dto.common.DictionaryPageQuery;
import cn.lmx.kpu.authority.dto.common.DictionarySaveDTO;
import cn.lmx.kpu.authority.dto.common.DictionaryTypeSaveDTO;
import cn.lmx.kpu.authority.dto.common.DictionaryUpdateDTO;
import cn.lmx.kpu.authority.entity.common.Dictionary;
import cn.lmx.kpu.authority.service.common.DictionaryService;
import cn.lmx.kpu.common.constant.DefValConstants;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/dictionary")
@Api(value = "Dictionary", tags = "字典类型")
@PreAuth(replace = "authority:dictionary:")
@RequiredArgsConstructor
public class DictionaryController
        extends SuperController<DictionaryService, Long, Dictionary, DictionaryPageQuery, DictionarySaveDTO, DictionaryUpdateDTO> {

    @Override
    public QueryWrap<Dictionary> handlerWrapper(Dictionary model, PageParams<DictionaryPageQuery> params) {
        QueryWrap<Dictionary> qw = Wraps.q(null, params.getExtra(), getEntityClass());
        qw.lambda().eq(Dictionary::getType, model.getType())
                .like(Dictionary::getCode, model.getCode())
                .like(Dictionary::getName, model.getName())
                .ne(Dictionary::getCode, DefValConstants.DICT_PLACEHOLDER);
        return qw;
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        this.baseService.removeByIds(ids);
        return this.success(true);
    }

    @ApiOperation(value = "分页列表查询-字典类型")
    @PostMapping(value = "/pageType")
    @PreAuth("hasAnyPermission('{}view')")
    public R<IPage<Dictionary>> pageType(@RequestBody @Validated PageParams<DictionaryPageQuery> params) {
        IPage<Dictionary> page = params.buildPage(Dictionary.class);
        baseService.pageType(page, params.getModel());
        return R.success(page);
    }

    @ApiOperation(value = "保存-字典类型")
    @PostMapping(value = "/type")
    @PreAuth("hasAnyPermission('{}add')")
    public R<Dictionary> saveType(@RequestBody @Validated DictionaryTypeSaveDTO dictType) {
        return R.success(baseService.saveType(dictType));
    }

    @ApiOperation(value = "修改-字典类型")
    @PutMapping(value = "/type")
    @PreAuth("hasAnyPermission('{}edit')")
    public R<Boolean> updateType(@RequestBody @Validated DictionaryTypeSaveDTO dictType) {
        return R.success(baseService.updateType(dictType));
    }

    @ApiOperation(value = "删除-字典类型")
    @DeleteMapping(value = "/type")
    @PreAuth("hasAnyPermission('{}delete')")
    public R<Boolean> deleteType(@RequestBody List<String> types) {
        return R.success(baseService.deleteType(types));
    }
}
