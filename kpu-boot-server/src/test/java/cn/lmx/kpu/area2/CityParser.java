package cn.lmx.kpu.area2;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import cn.lmx.kpu.authority.entity.common.Area;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * 将国家统计局的数据封装成list
 *
 * @author lmx
 * @date 2023/10/14  20:22
 */
@Component
public class CityParser implements ICityParser {

    private static final String COMMON_URL = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/";

    private static final Charset CHARSET = CharsetUtil.CHARSET_UTF_8;

    /**
     * @param areaLevel 抓取的层级 从0开始，0-省 1-市 2-区 3-镇 4-乡
     * 	传递3、4时，数据量很大，抓取数据非常慢！
     * @return java.util.List<cn.lmx.kpu.authority.entity.common.Area>
     * @author lmx
     * @date 2023/10/14 23:13
     */
    public List<Area> parseProvinces(int areaLevel) {
        return parseProvince(COMMON_URL + "index.html",areaLevel);
    }

    private List<Area> parseProvince(String url,int areaLevel) {
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);

        // 获取 class='provincetr' 的元素
        Elements elements = document.getElementsByClass("provincetr");
        List<Area> provinces = new LinkedList<Area>();
        int sort = 1;
        for (Element element : elements) {
            // 获取 elements 下属性是 href 的元素
            Elements links = element.getElementsByAttribute("href");
            for (Element link : links) {
                String provinceName = link.text();
                String href = link.attr("href");
                String provinceCode = href.substring(0, 2);

                Area provinceArea = Area.builder()
                        .divisionCode(provinceCode + "0000000000")
                        .code(provinceCode)
                        .name(provinceName).source("10")
                        .sortValue(sort++)
                        .level("20")
                        .fullName(provinceName)
                        .build();
                if (areaLevel > 0) {
                    provinceArea.setChildren(parseCity(provinceName, COMMON_URL + href,areaLevel));
                }
//                StaticLog.info("省级数据:  {}  ", provinceArea);

                provinces.add(provinceArea);
            }
        }
        return provinces;
    }

    private List<Area> parseCity(String provinceName, String url,int areaLevel) {
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("citytr");

        List<Area> cities = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            String href = links.get(0).attr("href");
            String cityDivisionCode = links.get(0).text();
            String cityCode = links.get(0).text().substring(0, 4);
            String cityName = links.get(1).text();

            Area cityArea = Area.builder()
                    .name(cityName).divisionCode(cityDivisionCode).code(cityCode).source("10").sortValue(sort++)
                    .level("30")
                    .fullName(provinceName + cityName)
                    .build();

//            StaticLog.info("	市级数据:  {}  ", cityArea);
            if (areaLevel > 1) {
                cityArea.setChildren(parseCounty(provinceName + cityName, COMMON_URL + href,areaLevel));
            }
            cities.add(cityArea);
        }
        return cities;
    }

    private List<Area> parseCounty(String fullName, String url,int areaLevel) {
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("countytr");

        if (trs.isEmpty()) {
            return parseTowntr(fullName, url,areaLevel);
        }
        List<Area> counties = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            if (links == null || links.size() != 2) {
                continue;
            }
            String href = links.get(0).attr("href");
            String divisionCode = links.get(0).text();
            String cityCode = links.get(0).text().substring(0, 6);
            String countyName = links.get(1).text();

            Area countyArea = Area.builder().divisionCode(divisionCode).code(cityCode)
                    .name(countyName)
                    .source("10")
                    .fullName(fullName + countyName)
                    .sortValue(sort++)
                    .level("40")
                    .build();

//            StaticLog.info("		县级数据:  {}  ", countyArea);
            if (areaLevel > 2) {
                countyArea.setChildren(parseTowntr(fullName + countyName, COMMON_URL + href.subSequence(2, 5).toString() + "/" + href,areaLevel));
            }
            counties.add(countyArea);
        }
        return counties;
    }

    /**
     * 乡镇级数据
     *
     * @param url
     * @return
     */
    public List<Area> parseTowntr(String fullName, String url,int areaLevel) {
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("towntr");

        List<Area> counties = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            if (links == null || links.size() != 2) {
                continue;
            }
            String href = links.get(0).attr("href");
            String towntrDivisionCode = links.get(0).text();
            String towntrCode = links.get(0).text().substring(0, 9);
            ;
            String towntrName = links.get(1).text();

            Area towntrArea = Area.builder()
                    .name(towntrName).divisionCode(towntrDivisionCode).code(towntrCode).source("10")
                    .fullName(fullName + towntrName)
                    .level("50")
                    .sortValue(sort++)
                    .build();

//            StaticLog.info("			乡镇级数据:  {}  ", towntrArea);
            if(areaLevel > 3) {
                towntrArea.setChildren(parseVillagetr(fullName + towntrName, COMMON_URL + href.subSequence(2, 5).toString() + "/" + href.substring(5, 7) + "/" + href));
            }
            counties.add(towntrArea);
        }
        return counties;
    }

    /**
     * 村庄数据
     *
     * @param url
     * @return
     */
    public List<Area> parseVillagetr(String fullName, String url) {
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("villagetr");

        List<Area> counties = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            if (tds == null || tds.size() != 3) {
                continue;
            }
            String villagetrDivisionCode = tds.get(0).text();
            String villagetrCode = tds.get(0).text().substring(0, 12);
            String villagetrName = tds.get(2).text();

            Area villagetrArea = Area.builder().divisionCode(villagetrDivisionCode).code(villagetrCode)
                    .name(villagetrName)
                    .fullName(fullName + villagetrName)
                    .sortValue(sort++)
                    .level("50")
                    .source("10").build();
//            StaticLog.info("				村级数据:  {}  ", villagetrArea);

            counties.add(villagetrArea);
        }
        return counties;
    }
}