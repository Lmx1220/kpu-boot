package cn.lmx.kpu.msg.service.impl;

import cn.hutool.core.bean.BeanUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.exception.BizException;

import cn.lmx.kpu.msg.entity.DefInterfaceProperty;
import cn.lmx.kpu.msg.manager.DefInterfacePropertyManager;
import cn.lmx.kpu.msg.service.DefInterfacePropertyService;
import cn.lmx.kpu.msg.vo.save.DefInterfacePropertyBatchSaveVO;
import cn.lmx.kpu.msg.vo.save.DefInterfacePropertySaveVO;
import cn.lmx.kpu.msg.vo.update.DefInterfacePropertyUpdateVO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 接口属性
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DefInterfacePropertyServiceImpl extends SuperServiceImpl<DefInterfacePropertyManager, Long, DefInterfaceProperty> implements DefInterfacePropertyService {
    @Override
    public Map<String, Object> listByInterfaceId(Long id) {
        return superManager.listByInterfaceId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean batchSave(DefInterfacePropertyBatchSaveVO saveVO) {
        List<DefInterfacePropertySaveVO> insertRecords = saveVO.getInsertRecords();
        List<DefInterfacePropertyUpdateVO> updateRecords = saveVO.getUpdateRecords();
        List<DefInterfacePropertyUpdateVO> removeRecords = saveVO.getRemoveRecords();
        List<DefInterfacePropertyUpdateVO> pendingRecords = saveVO.getPendingRecords();
        Set<String> insertKeys = insertRecords.stream().map(DefInterfacePropertySaveVO::getKey).collect(Collectors.toSet());
        Set<String> updateKeys = updateRecords.stream().map(DefInterfacePropertyUpdateVO::getKey).collect(Collectors.toSet());
        if (updateKeys.size() + insertKeys.size() != insertRecords.size() + updateRecords.size()) {
            throw BizException.wrap("参数健重复");
        }

        List<Long> removeIdList = removeRecords.stream().map(DefInterfacePropertyUpdateVO::getId).toList();
        superManager.removeByIds(removeIdList);
        List<Long> pendingList = pendingRecords.stream().map(DefInterfacePropertyUpdateVO::getId).toList();
        superManager.removeByIds(pendingList);

        List<DefInterfaceProperty> saveList = BeanUtil.copyToList(insertRecords, DefInterfaceProperty.class);
        superManager.saveBatch(saveList);

        List<DefInterfaceProperty> updateList = BeanUtil.copyToList(updateRecords, DefInterfaceProperty.class);
        superManager.updateBatchById(updateList);

        return true;
    }
}


