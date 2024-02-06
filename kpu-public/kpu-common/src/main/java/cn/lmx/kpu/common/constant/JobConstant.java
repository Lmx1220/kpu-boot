package cn.lmx.kpu.common.constant;

/**
 * 定时任务 常量
 *
 * @author lmx
 * @date 2024/02/07  02:04 上午
 */
public interface JobConstant {

    /**
     * 默认的定时任务组
     */
    String DEF_BASE_JOB_GROUP_NAME = "kpu-base-executor";
    String DEF_EXTEND_JOB_GROUP_NAME = "kpu-extend-executor";
    /**
     * 短信发送处理器
     */
    String SMS_SEND_JOB_HANDLER = "smsSendJobHandler";
}
