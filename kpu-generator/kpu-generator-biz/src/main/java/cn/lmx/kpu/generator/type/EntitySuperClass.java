package cn.lmx.kpu.generator.type;

import cn.lmx.basic.interfaces.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 父类实体类型
 *
 * @author zuihou
 * @date 2019/05/14
 */
@Getter
@AllArgsConstructor
public enum EntitySuperClass implements BaseEnum {
    /**
     * 只有id
     * <p>
     * "tenant_code" 字段会自动忽略
     */
    SUPER_ENTITY("01", "cn.lmx.basic.base.entity.SuperEntity", new String[]{"id", "tenant_code", "create_time", "created_by"}),
    /**
     * 有创建人创建时间等
     * "tenant_code" 字段会自动忽略
     */
    ENTITY("02", "cn.lmx.basic.base.entity.Entity", new String[]{"id", "tenant_code", "create_time", "created_by", "update_time", "updated_by"}),

    /**
     * 树形实体
     * "tenant_code" 字段会自动忽略
     */
    TREE_ENTITY("03", "cn.lmx.basic.base.entity.TreeEntity", new String[]{"id", "tenant_code", "create_time", "created_by", "update_time", "updated_by", "label", "parent_id", "sort_value"}),

    /**
     * 不继承任何实体
     */
    NONE("04", "", new String[]{""}),
    ;

    private String value;
    private String clazzName;
    private String[] columns;


    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return this.name();
    }

    @Override
    public boolean eq(String val) {
        return this.name().equals(val);
    }

    public boolean eq(EntitySuperClass val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

}
