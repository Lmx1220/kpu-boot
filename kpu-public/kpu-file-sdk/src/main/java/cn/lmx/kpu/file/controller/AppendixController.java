package cn.lmx.kpu.file.controller;

import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperSimpleController;
import cn.lmx.kpu.file.entity.Appendix;
import cn.lmx.kpu.file.service.AppendixService;
import cn.lmx.kpu.model.vo.result.AppendixResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 增量文件上传日志
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 * @create [2023/7/4 14:27] [lmx] [初始创建]
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/anyone/appendix")
@Api(value = "AppendixController", tags = "业务附件")
public class AppendixController extends SuperSimpleController<AppendixService, Appendix> {


    /**
     * 根据业务id 和 业务类型查询附件信息
     *
     * @param bizId   业务id
     * @param bizType 业务类型
     */
    @ApiOperation(value = "根据业务id 和 业务类型查询附件信息", notes = "根据业务id 和 业务类型查询附件信息")
    @PostMapping(value = "/listByBizId")
    @SysLog("根据业务id 和 业务类型查询附件信息")
    public R<List<AppendixResultVO>> listByBizId(@RequestParam Long bizId, @RequestParam(required = false) String bizType) {
        return R.success(baseService.listByBizIdAndBizType(bizId, bizType));
    }
}
