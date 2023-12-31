package cn.lmx.kpu.authority.controller.poi;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.authority.entity.core.Org;
import cn.lmx.kpu.authority.entity.core.Station;
import cn.lmx.kpu.authority.manager.common.DictManager;
import cn.lmx.kpu.authority.manager.core.OrgManager;
import cn.lmx.kpu.authority.manager.core.StationManager;
import cn.lmx.kpu.authority.service.common.DictService;
import cn.lmx.kpu.authority.service.core.OrgService;
import cn.lmx.kpu.authority.service.core.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户导出字典处理器
 *
 * @author lmx
 * @version v1.0.0
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27 ] [lmx] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class UserExcelDictHandlerImpl implements IExcelDictHandler {
    public static final String DICT_STATION = "station";
    public static final String DICT_ORG = "org";
    public static final String DICT_NATION = "NATION";
    public static final String DICT_EDUCATION = "EDUCATION";
    public static final String DICT_POSITION_STATUS = "POSITION_STATUS";
    private final OrgManager orgManager;
    private final StationManager stationManager;
    private final DictManager dictManager;

    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        if (value == null) {
            return null;
        }
        if (DICT_ORG.equals(dict)) {
            Org org = orgManager.getByIdCache(Convert.toLong(value));
            return org != null ? org.getName() : value.toString();
        }
        if (DICT_STATION.equals(dict)) {
            Station station = stationManager.getByIdCache(Convert.toLong(value));
            return station != null ? station.getName() : value.toString();
        }
        if (StrUtil.equalsAny(dict, DICT_NATION, DICT_EDUCATION, DICT_POSITION_STATUS)) {
            Dict dictionary = dictManager.getOne(Wraps.<Dict>lbQ()
                    .eq(Dict::getParentKey, dict)
                    .eq(Dict::getKey, String.valueOf(value)), false);
            return dictionary != null ? dictionary.getName() : String.valueOf(value);
        }
        return String.valueOf(value);
    }

    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        if (value == null) {
            return null;
        }
        if (DICT_STATION.equals(dict)) {
            Station station = stationManager.getOne(Wraps.<Station>lbQ().eq(Station::getName, String.valueOf(value)), false);
            return station != null ? String.valueOf(station.getId()) : "";
        }
        if (DICT_ORG.equals(dict)) {
            Org org = orgManager.getOne(Wraps.<Org>lbQ().eq(Org::getName, String.valueOf(value)), false);
            return org != null ? String.valueOf(org.getId()) : "";
        }
        if (StrUtil.equalsAny(dict, DICT_NATION, DICT_EDUCATION, DICT_POSITION_STATUS)) {
            Dict dictionary = dictManager.getOne(Wraps.<Dict>lbQ()
                    .eq(Dict::getParentKey, dict)
                    .eq(Dict::getName, String.valueOf(value)), false);
            return dictionary != null ? dictionary.getKey() : String.valueOf(value);
        }
        return value.toString();
    }
}
