package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.base.controller.*;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 父类
 *
 * @author lmx
 * @date 2023/10/13 14:27
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SuperClassEnum implements BaseEnum {
    SUPER_CLASS("01", SuperController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    SUPER_POI_CLASS("02", SuperPoiController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    SUPER_CACHE_CLASS("03", SuperCacheController.class.getName(),
            SuperCacheService.class.getName(), SuperCacheServiceImpl.class.getName(),
            SuperCacheManager.class.getName(), SuperCacheManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    SUPER_SIMPLE_CLASS("04", SuperSimpleController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    SUPER_READ_CLASS("05", SuperReadController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    NONE_CS("06", "", "", "", SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    NONE("07", "", "", "", "", "", "");

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
