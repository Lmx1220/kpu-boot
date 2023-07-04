package cn.lmx.kpu.common.constant;

import cn.lmx.basic.utils.StrPool;

/**
 * 默认值
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface DefValConstants {

    /**
     * 默认的根节点path
     */
    String ROOT_PATH = StrPool.COMMA;
    /**
     * 默认树层级
     */
    Integer TREE_GRADE = 0;
    /**
     * 默认的父id
     */
    Long PARENT_ID = StrPool.DEF_PARENT_ID;
    /**
     * 默认的排序
     */
    Integer SORT_VALUE = 0;
    /**
     * 字典占位符
     */
    String DICT_PLACEHOLDER = "###";
}
