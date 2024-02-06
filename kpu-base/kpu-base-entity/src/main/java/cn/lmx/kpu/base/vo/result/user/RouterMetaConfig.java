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
    private String iconKey = "icon";
    private String ignoreKeepAliveKey = "ignoreKeepAlive";
    private String affixKey = "affix";
    private String frameSrcKey = "frameSrc";
    private String transitionNameKey = "transitionName";
    private String hideBreadcrumbKey = "hideBreadcrumb";
    private String carryParamKey = "carryParam";
    private String currentActiveMenuKey = "currentActiveMenu";
    private String hideTabKey = "hideTab";
    private String hideMenuKey = "hideMenu";
    private String hideChildrenInMenuKey = "hideChildrenInMenu";
    private String typeKey = "type";
    private String contentKey = "content";
    private String dotKey = "dot";
}
