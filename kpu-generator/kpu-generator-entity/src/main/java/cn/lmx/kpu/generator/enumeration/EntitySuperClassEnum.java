package cn.lmx.kpu.generator.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import cn.lmx.basic.base.entity.Entity;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.base.entity.TreeEntity;
import cn.lmx.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * 父类实体类型
 * <p>
 * 若在kpu-core中新增了 基类实体，可以在此增加对应的枚举值
 *
 * @author lmx
 * @date 2024/02/07
 */
@Getter
@AllArgsConstructor
public enum EntitySuperClassEnum implements BaseEnum {
    /**
     * 只有id
     * <p>
     * "org_id" 字段会自动忽略
     */
    SUPER_ENTITY("01", SuperEntity.class.getName(), new String[]{"id", "created_time", "created_by"}),

    /**
     * 有创建人创建时间等
     * "org_id" 字段会自动忽略
     */
    ENTITY("02", Entity.class.getName(), new String[]{"id", "created_time", "created_by", "updated_time", "updated_by"}),

    /**
     * 树形实体
     * "org_id" 字段会自动忽略
     */
    TREE_ENTITY("03", TreeEntity.class.getName(), new String[]{"id", "created_time", "created_by", "updated_time", "updated_by", "parent_id", "sort_value"}),
    /**
     * 不继承任何实体
     */
    NONE("04", "", new String[]{""}),
    ;

    private final String value;
    private final String clazzName;
    /**
     * 生成代码时，默认忽略的字段
     */
    private final String[] columns;


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

    public boolean eq(EntitySuperClassEnum val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }


    public boolean matchSuperEntityColumns(String fieldName) {
        // 公共字段判断忽略大小写【 部分数据库大小写不敏感 】
        return Stream.of(this.getColumns()).anyMatch(e -> e.equalsIgnoreCase(fieldName));
    }

}
