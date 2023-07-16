package cn.lmx.kpu.oauth.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.lmx.basic.base.R;
import cn.lmx.basic.interfaces.BaseEnum;
import cn.lmx.basic.utils.ClassUtils;
import cn.lmx.basic.utils.CollHelper;
import cn.lmx.basic.utils.StrPool;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.authority.service.common.DictService;
import cn.lmx.kpu.authority.service.common.ParameterService;
import cn.lmx.kpu.model.vo.query.CodeQueryVO;
import cn.lmx.kpu.model.vo.result.Option;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 通用 控制器
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Slf4j
@RestController
@Api(value = "Common", tags = "通用Controller")
@AllArgsConstructor
public class OauthGeneralController {
    /**
     * 需要让前端查询的枚举类所在的包
     */
    private static final String ENUMS_PACKAGE = "cn.lmx";
    private final ParameterService parameterService;

    private static final Map<String, Map<String, String>> ENUM_MAP = new HashMap<>();
    private static final Map<String, List<Option>> ENUM_LIST_MAP = new HashMap<>();
    /**
     * 过滤那些枚举
     */
    private static final Predicate<Class<?>> CLASS_FILTER = item -> item != null && item.isEnum() && item.isEnum() && MybatisEnumTypeHandler.isMpEnums(item);
    private final DictService dictionaryService;

    static {
        Set<Class<?>> enumClass = ClassUtils.scanPackage(ENUMS_PACKAGE, CLASS_FILTER);

        StringJoiner enumSb = new StringJoiner(StrPool.COMMA);
        enumClass.forEach(item -> {
            Object[] enumConstants = item.getEnumConstants();
            BaseEnum[] baseEnums = Arrays.stream(enumConstants).map(i -> (BaseEnum) i).toArray(BaseEnum[]::new);

            ENUM_LIST_MAP.put(item.getSimpleName(), Option.mapOptions(baseEnums));
            ENUM_MAP.put(item.getSimpleName(), CollHelper.getMap(baseEnums));
            enumSb.add(item.getSimpleName());
        });

        log.info("扫描: {} ,共加载了{}个枚举类, 分别为: {}", ENUMS_PACKAGE, ENUM_MAP.size(), enumSb);
    }

    private static Map<String, List<Option>> mapOptionByDict(Map<String, List<Dict>> map, CodeQueryVO[] types) {
        if (MapUtil.isEmpty(map)) {
            return Collections.emptyMap();
        }
        Map<String, List<Option>> newMap = new HashMap<>();
        map.forEach((key, values) ->
                {
                    //key获取 type
                    CodeQueryVO type = Arrays.stream(types).filter(item -> item.getType().equals(key)).findFirst().orElse(null);
                    List<Option> collect = values.stream().map(item -> Option.builder().label(item.getName())
                            .text(item.getName()).value(item.getKey()).build()).collect(Collectors.toList());
                    if (type.getExtend() != null) {
                        if (type.getExtendFirst()) {
                            collect.add(0, type.getExtend());
                        } else {
                            collect.add(type.getExtend());
                        }
                    }
                    newMap.put(key, collect);
                }

        );
        return newMap;
    }


    @ApiOperation(value = "获取当前系统指定枚举 List", notes = "获取当前系统指定枚举（kpu-web使用）")
    @PostMapping("/enums/findEnumListByType")
    public R<Map<String, List<Option>>> enumLists(@RequestBody CodeQueryVO[] types) {
        if (ArrayUtil.isEmpty(types)) {
            return R.success(ENUM_LIST_MAP);
        }
        Map<String, List<Option>> map = new HashMap<>(CollHelper.initialCapacity(types.length));

        for (CodeQueryVO type : types) {
            if (ENUM_MAP.containsKey(type.getType())) {
                List<Option> options = ENUM_LIST_MAP.get(type.getType());
                //过滤指定项
                if (ArrayUtil.isNotEmpty(type.getExcludes())) {
                    //过滤指定项 保留不在排除项中的选项 不保留排除项
                    options = options.stream().filter(item -> !ArrayUtil.contains(type.getExcludes(), item.getValue())).collect(Collectors.toList());
                }
                // 添加扩展项
                if (type.getExtend() != null) {
                    if (type.getExtendFirst()) {
                        options.add(0, type.getExtend());
                    } else {
                        options.add(type.getExtend());
                    }
                }
                map.put(type.getType(), options);
            }
        }
        return R.success(map);
    }

    @ApiOperation(value = "根据类型编码查询字典项,并排除指定项", notes = "根据类型编码查询字典项,并排除指定项")
    @PostMapping("/dict/findDictMapItemListByKey")
    public R<Map<String, List<Option>>> codeList(@RequestBody CodeQueryVO[] types) {
        return R.success(mapOptionByDict(dictionaryService.listByItem(types), types));
    }


    @PostMapping("/parameter/findParamMapByKey")
    @ApiOperation(value = "根据key批量获取系统参数", notes = "根据key批量获取系统参数")
    public R<Map<String, String>> findParams(@RequestBody List<String> keys) {
        return R.success(parameterService.findParams(keys));
    }
}

