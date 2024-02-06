package cn.lmx.kpu.system.service.system.impl;

import cn.hutool.core.util.RandomUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperCacheServiceImpl;

import cn.lmx.kpu.system.entity.system.DefClient;
import cn.lmx.kpu.system.manager.system.DefClientManager;
import cn.lmx.kpu.system.service.system.DefClientService;
import cn.lmx.kpu.system.vo.save.system.DefClientSaveVO;

/**
 * <p>
 * 业务实现类
 * 客户端
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefClientServiceImpl extends SuperCacheServiceImpl<DefClientManager, Long, DefClient>
        implements DefClientService {

    @Override
    protected <SaveVO> DefClient saveBefore(SaveVO saveVO) {
        DefClientSaveVO defClientSaveVO = (DefClientSaveVO) saveVO;
        DefClient defClient = super.saveBefore(defClientSaveVO);
        defClient.setClientId(RandomUtil.randomString(24));
        defClient.setClientSecret(RandomUtil.randomString(32));
        return defClient;
    }

    @Override
    public DefClient getClient(String clientId, String clientSecret) {
        return superManager.getClient(clientId, clientSecret);
    }
}
