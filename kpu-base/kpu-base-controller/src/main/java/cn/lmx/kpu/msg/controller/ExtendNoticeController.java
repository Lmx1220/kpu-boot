package cn.lmx.kpu.msg.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lmx.basic.annotation.log.WebLog;
import cn.lmx.basic.base.R;
import cn.lmx.basic.base.controller.SuperController;
import cn.lmx.basic.base.request.PageParams;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.basic.database.mybatis.conditions.Wraps;
import cn.lmx.basic.interfaces.echo.EchoService;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.msg.entity.ExtendNotice;
import cn.lmx.kpu.msg.enumeration.NoticeRemindModeEnum;
import cn.lmx.kpu.msg.service.ExtendNoticeService;
import cn.lmx.kpu.msg.vo.MyMsgResult;
import cn.lmx.kpu.msg.vo.query.ExtendNoticePageQuery;
import cn.lmx.kpu.msg.vo.result.ExtendNoticeResultVO;
import cn.lmx.kpu.msg.vo.save.ExtendNoticeSaveVO;
import cn.lmx.kpu.msg.vo.update.ExtendNoticeUpdateVO;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 通知表
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
@RequestMapping("/extendNotice/anyone")
@Tag(name = "通知表")
public class ExtendNoticeController extends SuperController<ExtendNoticeService, Long, ExtendNotice, ExtendNoticeSaveVO,
        ExtendNoticeUpdateVO, ExtendNoticePageQuery, ExtendNoticeResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public void handlerQueryParams(PageParams<ExtendNoticePageQuery> params) {
        params.getModel().setRecipientId(ContextUtil.getEmployeeId());
    }


    @Operation(summary = "全量查询我的未读消息", description = "全量查询我的消息")
    @PostMapping("/myNotice")
    @WebLog(value = "'全量查询我的消息:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<MyMsgResult> myNotice(@RequestBody @Validated PageParams<ExtendNoticePageQuery> params) {

        IPage<ExtendNotice> todoList = params.buildPage(ExtendNotice.class);
        IPage<ExtendNotice> noticeList = params.buildPage(ExtendNotice.class);
        IPage<ExtendNotice> earlyWarningList = params.buildPage(ExtendNotice.class);
        superService.page(todoList, Wraps.<ExtendNotice>lbQ()
                .eq(ExtendNotice::getRemindMode, NoticeRemindModeEnum.TO_DO.getValue()).
                eq(ExtendNotice::getIsRead, false).eq(ExtendNotice::getRecipientId, ContextUtil.getEmployeeId()));
        superService.page(noticeList, Wraps.<ExtendNotice>lbQ()
                .eq(ExtendNotice::getRemindMode, NoticeRemindModeEnum.NOTICE.getValue()).
                eq(ExtendNotice::getIsRead, false).eq(ExtendNotice::getRecipientId, ContextUtil.getEmployeeId()));
        superService.page(earlyWarningList, Wraps.<ExtendNotice>lbQ()
                .eq(ExtendNotice::getRemindMode, NoticeRemindModeEnum.EARLY_WARNING.getValue()).
                eq(ExtendNotice::getIsRead, false).eq(ExtendNotice::getRecipientId, ContextUtil.getEmployeeId()));

        MyMsgResult result = MyMsgResult.builder()
                .todoList(BeanPlusUtil.toBeanPage(todoList, ExtendNoticeResultVO.class))
                .noticeList(BeanPlusUtil.toBeanPage(noticeList, ExtendNoticeResultVO.class))
                .earlyWarningList(BeanPlusUtil.toBeanPage(earlyWarningList, ExtendNoticeResultVO.class))
                .build();
        return R.success(result);
    }


    /**
     * 标记消息为已读
     *
     * @param noticeIds 主表id
     * @return 是否成功
     */
    @Operation(summary = "标记消息为已读", description = "标记消息为已读")
    @PostMapping(value = "/mark")
    public R<Boolean> mark(@RequestBody List<Long> noticeIds) {
        return R.success(superService.mark(noticeIds, ContextUtil.getEmployeeId()));
    }

    /**
     * 删除消息中心
     *
     * @param receiveIds 接收id
     * @return 删除结果
     */
    @Operation(summary = "删除我的消息", description = "根据id物理删除我的消息")
    @DeleteMapping("/deleteMyNotice")
    @WebLog("删除我的消息")
    public R<Boolean> deleteMyMsg(@RequestBody List<Long> receiveIds) {
        return R.success(superService.deleteMyNotice(receiveIds));
    }

}


