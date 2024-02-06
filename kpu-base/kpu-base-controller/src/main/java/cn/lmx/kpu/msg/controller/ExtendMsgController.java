package cn.lmx.kpu.msg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.annotation.log.WebLog;
import cn.lmx.basic.annotation.user.LoginUser;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.database.mybatis.conditions.query.QueryWrap;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.datascope.DataScopeHelper;
import cn.lmx.kpu.model.entity.system.SysUser;
import cn.lmx.kpu.msg.biz.MsgBiz;
import cn.lmx.kpu.msg.entity.ExtendMsg;
import cn.lmx.kpu.msg.enumeration.SourceType;
import cn.lmx.kpu.msg.service.ExtendMsgService;
import cn.lmx.kpu.msg.vo.query.ExtendMsgPageQuery;
import cn.lmx.kpu.msg.vo.result.ExtendMsgResultVO;
import cn.lmx.kpu.msg.vo.save.ExtendMsgSaveVO;
import cn.lmx.kpu.msg.vo.update.ExtendMsgPublishVO;
import cn.lmx.kpu.msg.vo.update.ExtendMsgSendVO;
import cn.lmx.kpu.msg.vo.update.ExtendMsgUpdateVO;

/**
 * <p>
 * 前端控制器
 * 消息
 * </p>
 *
 * @author lmx
 * @date 2024/02/07  02:04
 * @create [2024/02/07  02:04] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/extendMsg")
@Tag(name = "消息")
public class ExtendMsgController extends SuperController<ExtendMsgService, Long, ExtendMsg, ExtendMsgSaveVO,
        ExtendMsgUpdateVO, ExtendMsgPageQuery, ExtendMsgResultVO> {
    private final EchoService echoService;
    private final MsgBiz msgBiz;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Operation(summary = "根据模板发送消息", description = "根据模板发送消息")
    @PostMapping("/sendByTemplate")
    @WebLog("发送消息")
    public R<Boolean> sendByTemplate(@RequestBody @Validated(SuperEntity.Update.class) ExtendMsgSendVO data
            , @Parameter(hidden = true) @LoginUser(isEmployee = true) SysUser sysUser) {
        return R.success(msgBiz.sendByTemplate(data, sysUser));
    }

    @Operation(summary = "发布站内信", description = "发布站内信")
    @PostMapping("/publish")
    @WebLog("发布站内信")
    public R<Boolean> publish(@RequestBody @Validated(SuperEntity.Update.class) ExtendMsgPublishVO data
            , @Parameter(hidden = true) @LoginUser(isEmployee = true) SysUser sysUser) {

        return R.success(msgBiz.publish(data, sysUser));
    }

    @Override
    public QueryWrap<ExtendMsg> handlerWrapper(ExtendMsg model, PageParams<ExtendMsgPageQuery> params) {
        QueryWrap<ExtendMsg> queryWrap = super.handlerWrapper(model, params);
        queryWrap.lambda().eq(ExtendMsg::getChannel, SourceType.APP);
        DataScopeHelper.startDataScope("extend_msg");
        return queryWrap;
    }

    /**
     * 查询消息中心
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Operation(summary = "查询消息中心", description = "查询消息中心")
    @GetMapping("/{id}")
    @WebLog("查询消息中心")
    @Override
    public R<ExtendMsgResultVO> get(@PathVariable Long id) {
        return R.success(superService.getResultById(id));
    }


}


