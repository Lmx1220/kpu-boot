package cn.lmx.kpu.generator.enumeration;

import cn.lmx.basic.base.controller.SuperCacheController;
import cn.lmx.basic.base.controller.SuperController;
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

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SuperClassEnum implements BaseEnum {

    SUPER_CLASS("01", SuperController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    SUPER_CACHE_CLASS("02", SuperCacheController.class.getName(),
            SuperCacheService.class.getName(), SuperCacheServiceImpl.class.getName(),
            SuperCacheManager.class.getName(), SuperCacheManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    NONE_CS("03", "", "", "", "", "", ""),
    NONE("04", "", "", "", SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName());

    private String value;
    private String controller;
    private String service;
    private String serviceImpl;
    private String manager;
    private String managerImpl;
    private String mapper;

    public SuperClassEnum setController(String controller) {
        this.controller = controller;
        return this;
    }

    public SuperClassEnum setService(String service) {
        this.service = service;
        return this;
    }

    public SuperClassEnum setManager(String manager) {
        this.manager = manager;
        return this;
    }

    public SuperClassEnum setManagerImpl(String managerImpl) {
        this.managerImpl = managerImpl;
        return this;
    }

    public SuperClassEnum setMapper(String mapper) {
        this.mapper = mapper;
        return this;
    }

    public SuperClassEnum setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
        return this;
    }

    @Override
    public String getDesc() {
        return this.name();
    }
}
