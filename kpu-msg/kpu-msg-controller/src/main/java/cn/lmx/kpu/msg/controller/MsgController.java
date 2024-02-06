package cn.lmx.kpu.msg.controller;

import cn.lmx.basic.annotation.log.SysLog;
import cn.lmx.basic.annotation.user.LoginUser;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.entity.SuperEntity;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.kpu.model.entity.base.SysUser;
import cn.lmx.kpu.msg.biz.MsgBiz;
import cn.lmx.kpu.msg.vo.update.MsgPublishVO;
import cn.lmx.kpu.msg.vo.update.MsgSendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.kpu.msg.service.MsgService;
import cn.lmx.kpu.msg.entity.Msg;
import cn.lmx.kpu.msg.vo.save.MsgSaveVO;
import cn.lmx.kpu.msg.vo.update.MsgUpdateVO;
import cn.lmx.kpu.msg.vo.result.MsgResultVO;
import cn.lmx.kpu.msg.vo.query.MsgPageQuery;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 前端控制器
 * 消息表
 * </p>
 *
 * @author lmx
 * @date 2023-12-10 18:14:10
 * @create [2023-12-10 18:14:10] [lmx] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/msg")
@Api(value = "Msg", tags = "消息表")
public class MsgController extends SuperController<MsgService, Long, Msg, MsgSaveVO,
    MsgUpdateVO, MsgPageQuery, MsgResultVO> {

    private final EchoService echoService;
    private final MsgBiz msgBiz;
    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @ApiOperation(value = "根据模板发送消息", notes = "根据模板发送消息")
    @PostMapping("/sendByTemplate")
    @SysLog("发送消息")
    public R<Boolean> sendByTemplate(@RequestBody @Validated(SuperEntity.Update.class) MsgSendVO data
            , @ApiIgnore @LoginUser SysUser sysUser) {
        return R.success(msgBiz.sendByTemplate(data, sysUser));
    }
    @ApiOperation(value = "发布站内消息", notes = "发布站内消息")
    @PostMapping("/publish")
    @SysLog("发布站内消息")
    public R<Boolean> p(@RequestBody @Validated(SuperEntity.Update.class) MsgPublishVO data
            , @ApiIgnore @LoginUser SysUser sysUser) {
        return R.success(msgBiz.publish(data, sysUser));
    }
}


