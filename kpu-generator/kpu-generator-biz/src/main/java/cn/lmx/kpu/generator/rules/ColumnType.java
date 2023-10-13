package cn.lmx.kpu.generator.rules;

/**
 * 获取实体类字段属性类信息接口
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
public interface ColumnType {

    /**
     * 获取字段类型
     *
     * @return 字段类型
     */
    String getType();

    /**
     * 获取字段类型完整名
     *
     * @return 字段类型完整名
     */
    String getPkg();

    /**
     * 前端TypeScript类型
     *
     * @return
     */
    String getTsType();

    /**
     * web plus 的组件
     *
     * @return web plus 的组件
     */
    String getComponent();

    /**
     * vxe 组件
     *
     * @return vxe 组件
     */
    String getVxeComponent();
}
