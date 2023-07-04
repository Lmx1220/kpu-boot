package cn.lmx.kpu.authority.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Vue路由 Meta
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;

    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "图标")
    private String icon = "";
    @ApiModelProperty(value = "激活图标")
    private String activeIcon = "";
    @ApiModelProperty(value = "默认展开")
    private Boolean defaultOpened = true;
    @ApiModelProperty(value = "常驻标签页")
    private Boolean permanent = false;
    @ApiModelProperty(value = "鉴权标识")
    private String[] auth = {};
    @ApiModelProperty(value = "在导航显示")
    private Boolean sidebar = true;
    @ApiModelProperty(value = "在面包屑显示")
    private Boolean breadcrumb = false;
    @ApiModelProperty(value = "高亮导航")
    private String activeMenu;
    @ApiModelProperty(value = "缓存规则")
    private Object cache = true;
    @ApiModelProperty(value = "不缓存规则")
    private String[] noCache = {};
    @ApiModelProperty(value = "徽标")
    private String badge;
    @ApiModelProperty(value = "访问外链")
    private String link;
    @ApiModelProperty(value = "内嵌网页")
    private String iframe;
    @ApiModelProperty(value = "底部版权")
    private Boolean copyright;
    @ApiModelProperty(value = "底部填充高度")
    private String paddingBottom = "0px";

    public void merge(RouterMeta routerMeta) {
        this.setDefaultOpened(routerMeta.getDefaultOpened());
        this.setPermanent(routerMeta.getPermanent());
        this.setAuth(routerMeta.getAuth());
        this.setSidebar(routerMeta.getSidebar());
        this.setBreadcrumb(routerMeta.getBreadcrumb());
        this.setActiveMenu(routerMeta.getActiveMenu());
        this.setCache(routerMeta.getCache());
        this.setNoCache(routerMeta.getNoCache());
        this.setBadge(routerMeta.getBadge());
        this.setLink(routerMeta.getLink());
        this.setIframe(routerMeta.getIframe());
        this.setCopyright(routerMeta.getCopyright());
        this.setPaddingBottom(routerMeta.getPaddingBottom());
    }
}
