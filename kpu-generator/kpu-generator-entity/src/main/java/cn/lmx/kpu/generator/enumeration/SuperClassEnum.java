package cn.lmx.kpu.generator.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.controller.SuperReadController;
import cn.lmx.basic.base.controller.SuperSimpleController;
import cn.lmx.basic.base.controller.SuperWriteController;
import cn.lmx.basic.base.manager.SuperCacheManager;
import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.basic.base.manager.impl.SuperCacheManagerImpl;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.basic.base.mapper.SuperMapper;
import cn.lmx.basic.base.service.SuperCacheService;
import cn.lmx.basic.base.service.SuperService;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.interfaces.BaseEnum;

/**
 * 父类
 *
 * @author lmx
 * @date 2024/02/07  02:04 PM
 * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SuperClassEnum implements BaseEnum {
    /**
     * 增删改查
     */
    SUPER_CLASS("01", SuperController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 增删改查 + 导入导出
     */
    SUPER_POI_CLASS("02", SuperController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 增删改查 + 缓存
     */
    SUPER_CACHE_CLASS("03", SuperCacheController.class.getName(),
            SuperCacheService.class.getName(), SuperCacheServiceImpl.class.getName(),
            SuperCacheManager.class.getName(), SuperCacheManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 没有增删改查
     */
    SUPER_SIMPLE_CLASS("04", SuperSimpleController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 仅查询方法
     */
    SUPER_READ_CLASS("05", SuperReadController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 仅查询方法
     */
    SUPER_WRITE_CLASS("06", SuperWriteController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * controller和service不继承父类， manager和mapper 继承
     */
    NONE_CS("07", "", "", "", SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * controller、service、 manager和mapper不继承父类
     */
    NONE("08", "", "", "", "", "", "");

    private String value;
    private String controller;
    private String service;
    private String serviceImpl;
    private String manager;
    private String managerImpl;
    private String mapper;

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return this.name();
    }
}
