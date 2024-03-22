package cn.lmx.kpu.base.vo.result.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 树配置属性相关
 * <p>
 * 代码参考hutool
 *
 * @author lmx
 */
@Getter
@Setter
@Accessors(chain = true)
public class RouterMetaConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 默认属性配置对象
     */
    public static RouterMetaConfig DEFAULT_CONFIG = new RouterMetaConfig();

    /** 属性名配置字段 */
    private String titleKey = "title";
    private String i18nKey = "i18n";
    private String iconKey = "icon";
    private String activeIconKey = "activeIcon";
    private String defaultOpenedKey = "defaultOpened";
    private String permanentKey = "permanent";
    private String sidebarKey = "sidebar";
    private String activeMenuKey = "activeMenu";
    private String signleMenuKey = "signleMenu";
    private String breadcrumbKey = "breadcrumb";
    private String cacheKey = "cache";
    private String noCacheKey = "noCache";
    private String badgeKey = "badge";
    private String newWindowKey = "newWindow";
    private String linkKey = "link";
    private String iframeKey = "iframe";
    private String copyrightKey = "copyright";
    private String paddingBottomKey = "paddingBottom";
    private String whiteListKey = "whiteList";

    private String hideChildrenInMenuKey = "hideChildrenInMenu";

}
