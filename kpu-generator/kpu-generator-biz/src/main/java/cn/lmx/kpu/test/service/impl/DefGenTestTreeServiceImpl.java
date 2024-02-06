package cn.lmx.kpu.test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.utils.TreeUtil;
import cn.lmx.kpu.test.entity.DefGenTestTree;
import cn.lmx.kpu.test.manager.DefGenTestTreeManager;
import cn.lmx.kpu.test.service.DefGenTestTreeService;
import cn.lmx.kpu.test.vo.query.DefGenTestTreePageQuery;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 测试树结构
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefGenTestTreeServiceImpl extends SuperServiceImpl<DefGenTestTreeManager, Long, DefGenTestTree> implements DefGenTestTreeService {

    @Override
    public List<DefGenTestTree> findTree(DefGenTestTreePageQuery query) {
        List<DefGenTestTree> list = superManager.list(Wraps.<DefGenTestTree>lbQ().orderByAsc(DefGenTestTree::getSortValue));
        return TreeUtil.buildTree(list);
    }

}


