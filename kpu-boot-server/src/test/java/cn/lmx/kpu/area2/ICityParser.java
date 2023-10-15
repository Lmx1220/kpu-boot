package cn.lmx.kpu.area2;

import cn.lmx.kpu.authority.entity.common.Area;

import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/14  20:33
 */
public interface ICityParser {

    /**
     * 解析得到省市区数据
     *
     * @param areaLevel 抓取的层级 从0开始，0-省 1-市 2-区 3-镇 4-乡
     * @return 城市
     */
    List<Area> parseProvinces(int areaLevel);
}