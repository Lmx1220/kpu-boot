package cn.lmx.kpu.msg.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import cn.lmx.kpu.msg.vo.result.ExtendNoticeResultVO;

import java.io.Serializable;

/**
 * 我的消息
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Data
@EqualsAndHashCode
@Schema(description = "我的消息")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyMsgResult implements Serializable {
    /** 待办 */
    private IPage<ExtendNoticeResultVO> todoList;
    /** 提醒 */
    private IPage<ExtendNoticeResultVO> noticeList;
    /** 预警 */
    private IPage<ExtendNoticeResultVO> earlyWarningList;
}
