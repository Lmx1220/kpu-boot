package cn.lmx.kpu.generator.type;

import cn.lmx.basic.base.entity.TreeEntity;

/**
 * @author lmx
 * @date 2024/02/07  02:04
 */
public class GenConstants {
    /**
     * 页面不需要编辑字段
     */
    public static final String[] NOT_EDIT = {TreeEntity.CREATED_BY_FIELD, TreeEntity.CREATED_TIME_FIELD,
            TreeEntity.UPDATED_BY_FIELD, TreeEntity.UPDATED_TIME_FIELD, TreeEntity.CREATED_ORG_ID_FIELD};

    /**
     * 页面不需要显示的列表字段
     */
    public static final String[] NOT_LIST = {
            TreeEntity.CREATED_BY_FIELD, TreeEntity.CREATED_TIME_FIELD,
            TreeEntity.UPDATED_BY_FIELD, TreeEntity.UPDATED_TIME_FIELD, TreeEntity.CREATED_ORG_ID_FIELD
    };

    /**
     * 页面不需要查询字段
     */
    public static final String[] NOT_QUERY = {TreeEntity.ID_FIELD, TreeEntity.CREATED_BY_FIELD, TreeEntity.CREATED_TIME_FIELD,
            TreeEntity.UPDATED_BY_FIELD, TreeEntity.UPDATED_TIME_FIELD, TreeEntity.CREATED_ORG_ID_FIELD};

}
