package cn.lmx.kpu.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.annotation.log.WebLog;
import cn.lmx.basic.annotation.user.LoginUser;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.model.entity.system.SysUser;
import cn.lmx.kpu.test.entity.DefGenTestTree;
import cn.lmx.kpu.test.service.DefGenTestTreeService;
import cn.lmx.kpu.test.vo.query.DefGenTestTreePageQuery;
import cn.lmx.kpu.test.vo.result.DefGenTestTreeResultVO;
import cn.lmx.kpu.test.vo.save.DefGenTestTreeSaveVO;
import cn.lmx.kpu.test.vo.update.DefGenTestTreeUpdateVO;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 测试树结构
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defGenTestTree")
@Tag(name = "测试树结构")
public class DefGenTestTreeController extends SuperController<DefGenTestTreeService, Long, DefGenTestTree, DefGenTestTreeSaveVO,
        DefGenTestTreeUpdateVO, DefGenTestTreePageQuery, DefGenTestTreeResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    /**
     * 按树结构查询
     *
     * @param pageQuery 查询参数
     * @return 查询结果
     */
    @Operation(summary = "按树结构查询", description = "按树结构查询")
    @PostMapping("/tree")
    @WebLog("级联查询")
    public R<List<DefGenTestTree>> tree(@RequestBody DefGenTestTreePageQuery pageQuery) {
        return success(superService.findTree(pageQuery));
    }

    @PostMapping("/anyone/test")
    public R<Object> test(@LoginUser(isFull = true) SysUser user) {
        return success(user);
    }
}


