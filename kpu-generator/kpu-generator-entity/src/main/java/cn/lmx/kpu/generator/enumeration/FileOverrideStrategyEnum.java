package cn.lmx.kpu.generator.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import cn.lmx.basic.interfaces.BaseEnum;

/**
 * 文件覆盖策略
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Getter
@AllArgsConstructor
public enum FileOverrideStrategyEnum implements BaseEnum {
    /**
     * 覆盖 原始文件
     */
    OVERRIDE("1", "覆盖"),
    /**
     * 在原文件同一级新增一个 .add 后缀的文件
     */
    ADD("2", "新增"),
    /**
     * 直接忽略生成
     */
    IGNORE("3", "忽略"),
    /**
     * 存在时忽略生成，不存在则生成文件
     */
    EXIST_IGNORE("4", "存在时忽略"),
    ;

    final String code;
    final String desc;

    @Override
    public boolean eq(String val) {
        return this.name().equals(val);
    }

    public boolean eq(FileOverrideStrategyEnum val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
