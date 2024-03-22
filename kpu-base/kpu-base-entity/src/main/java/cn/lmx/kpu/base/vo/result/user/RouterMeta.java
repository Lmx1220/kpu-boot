package cn.lmx.kpu.base.vo.result.user;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Vue路由 Meta
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta extends LinkedHashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;


    @JsonIgnore
    private final RouterMetaConfig routerMetaConfig;

    public RouterMeta() {
        this(null);
    }

    /**
     * 构造
     *
     * @param routerMetaConfig 配置
     */
    public RouterMeta(RouterMetaConfig routerMetaConfig) {
        this.routerMetaConfig = ObjectUtil.defaultIfNull(routerMetaConfig, RouterMetaConfig.DEFAULT_CONFIG);
    }

    @Schema(description = "标题")
    public String getTitle() {
        return (String) this.get(routerMetaConfig.getTitleKey());
    }

    public RouterMeta setTitle(String title) {
        this.put(routerMetaConfig.getTitleKey(), title);
        return this;
    }
    @Schema(description = "i18n")
    public String getI18n() {
        return (String) this.get(routerMetaConfig.getTitleKey());
    }

    public RouterMeta setI18n(String i18n) {
        this.put(routerMetaConfig.getI18nKey(), i18n);
        return this;
    }

    @Schema(description = "图标")
    public String getIcon() {
        return (String) this.get(routerMetaConfig.getIconKey());
    }

    public RouterMeta setIcon(String icon) {
        this.put(routerMetaConfig.getIconKey(), icon);
        return this;
    }

    @Schema(description = "激活图标")
    public String getActiveIcon() {
        return (String) this.get(routerMetaConfig.getActiveIconKey());
    }

    public RouterMeta setActiveIcon(String activeIcon) {
        this.put(routerMetaConfig.getActiveIconKey(), activeIcon);
        return this;
    }
    @Schema(description = "次导航是否默认展开")
    public String getDefaultOpened() {
        return (String) this.get(routerMetaConfig.getDefaultOpenedKey());
    }

    public RouterMeta setDefaultOpened(String defaultOpened) {
        this.put(routerMetaConfig.getDefaultOpenedKey(), defaultOpened);
        return this;
    }

    @Schema(description = "是否常驻标签页")
    public Boolean getPermanent() {
        return (Boolean) this.get(routerMetaConfig.getPermanentKey());
    }

    public RouterMeta setPermanent(Boolean permanent) {
        this.put(routerMetaConfig.getPermanentKey(), permanent);
        return this;
    }

    @Schema(description = "该路由是否在菜单导航中展示")
    public Boolean getSidebar() {
        return (Boolean) this.get(routerMetaConfig.getSidebarKey());
    }

    public RouterMeta setSidebar(Boolean sidebar) {
        this.put(routerMetaConfig.getSidebarKey(), sidebar);
        return this;
    }
    @Schema(description = "指定高亮的菜单导航，需要设置完整路由地址")
    public String getActiveMenu() {
        return (String) this.get(routerMetaConfig.getActiveMenuKey());
    }

    public RouterMeta setActiveMenu(String activeMenu) {
        this.put(routerMetaConfig.getActiveMenuKey(), activeMenu);
        return this;
    }
    @Schema(description = "是否为单个一级导航菜单")
    public Boolean getSignleMenu() {
        return (Boolean) this.get(routerMetaConfig.getSignleMenuKey());
    }

    public RouterMeta setSignleMenu(Boolean signleMenu) {
        this.put(routerMetaConfig.getSignleMenuKey(), signleMenu);
        return this;
    }
    @Schema(description = "该路由是否在面包屑导航中展示")
    public Boolean getBreadcrumb() {
        return (Boolean) this.get(routerMetaConfig.getBreadcrumbKey());
    }

    public RouterMeta setBreadcrumb(Boolean breadcrumb) {
        this.put(routerMetaConfig.getBreadcrumbKey(), breadcrumb);
        return this;
    }
    // Boolean / String / String[]
    @Schema(description = "是否对该页面进行缓存")
    public Object getCache() {
        return  this.get(routerMetaConfig.getCacheKey());
    }

    public RouterMeta setCache(Object cache) {
        this.put(routerMetaConfig.getCacheKey(), cache);
        return this;
    }
    // String / String[]
    @Schema(description = "是否对该页面清除缓存，须设置 cache 才会生效")
    public Object getNoCache() {
        return  this.get(routerMetaConfig.getNoCacheKey());
    }

    public RouterMeta setNoCache(Object noCache) {
        this.put(routerMetaConfig.getNoCacheKey(), noCache);
        return this;
    }
   @Schema(description = "导航标记")
    public String getBadge() {
        return (String) this.get(routerMetaConfig.getBadgeKey());
    }

    public RouterMeta setBadge(String badge) {
        this.put(routerMetaConfig.getBadgeKey(), badge);
        return this;
    }

    @Schema(description = "是否在新窗口打开")
    public Boolean getNewWindow() {
        return (Boolean) this.get(routerMetaConfig.getNewWindowKey());
    }

    public RouterMeta setNewWindow(Boolean newWindow) {
        this.put(routerMetaConfig.getNewWindowKey(), newWindow);
        return this;
    }
    @Schema(description = "外部网页链接")
    public String getLink() {
        return (String) this.get(routerMetaConfig.getLinkKey());
    }

    public RouterMeta setLink(Boolean link) {
        this.put(routerMetaConfig.getLinkKey(), link);
        return this;
    }

    @Schema(description = "内嵌iframe的地址")
    public String getIframe() {
        return (String) this.get(routerMetaConfig.getIframeKey());
    }

    public RouterMeta setIframe(String iframe) {
        this.put(routerMetaConfig.getIframeKey(), iframe);
        return this;
    }
    @Schema(description = "该路由是否显示底部版权信息")
    public Boolean getCopyright() {
        return (Boolean) this.get(routerMetaConfig.getCopyrightKey());
    }

    public RouterMeta setCopyright(Boolean copyright) {
        this.put(routerMetaConfig.getCopyrightKey(), copyright);
        return this;
    }
    @Schema(description = "该路由是否需要空出距离底部距离")
    public String getPaddingBottom() {
        return (String) this.get(routerMetaConfig.getPaddingBottomKey());
    }

    public RouterMeta setPaddingBottom(String paddingBottom) {
        this.put(routerMetaConfig.getPaddingBottomKey(), paddingBottom);
        return this;
    }
    @Schema(description = "是否开启白名单，开启后无需登录即可访问")
    public Boolean getWhiteList() {
        return (Boolean) this.get(routerMetaConfig.getWhiteListKey());
    }

    public RouterMeta setWhiteList(String whiteList) {
        this.put(routerMetaConfig.getWhiteListKey(), whiteList);
        return this;
    }

    @Schema(description = "用于隐藏子菜单")
    public Boolean getHideChildrenInMenu() {
        return (Boolean) this.get(routerMetaConfig.getHideChildrenInMenuKey());
    }

    public RouterMeta setHideChildrenInMenu(Boolean hideChildrenInMenu) {
        this.put(routerMetaConfig.getHideChildrenInMenuKey(), hideChildrenInMenu);
        return this;
    }
}
