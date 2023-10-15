package cn.lmx.kpu.area2;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.log.StaticLog;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.kpu.authority.entity.common.Area;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmx
 * @version v1.0.0
 * @date 2023/10/14  20:33
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class CityParserTest {
    @Resource
    ICityParser cityParser;
    @Resource
    SqlCityParserDecorator sqlCityParserDecorator;

    @BeforeEach
    public void setTenant() {
        ContextUtil.setUserId(2L);
    }

    //    @Test
    public void test() {
    }

    /**
     * 实时爬取最新的地区数据，请执行该方法
     */
    @Test
    public void init() {
        TimeInterval timer = DateUtil.timer();
        // -------这是执行过程--------------
        cityParserDecorator();
        // ---------------------------------
        long interval = timer.interval();// 花费毫秒数
        long intervalMinute = timer.intervalMinute();// 花费分钟数
        StaticLog.info("本次程序执行 花费毫秒数: {} ,   花费分钟数:{} . ", interval, intervalMinute);
    }

    private List<Area> cityParserDecorator() {
        /*
        获取统计局数据, 注意：areaLevel 抓取的层级 从0开始，0-省 1-市 2-区 3-镇 4-乡
        CityParser 122行： countyArea.setChildren(parseTowntr(fullName + countyName, COMMON_URL + href.subSequence(2, 5).toString() + "/" + href));
        */
        List<Area> list = cityParser.parseProvinces(3);

        // 持久化
        sqlCityParserDecorator.parseProvinces(list);
        return list;
    }

}