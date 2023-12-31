package cn.lmx.kpu.common.api;

import cn.lmx.basic.base.R;
import cn.lmx.kpu.common.dto.XxlJobInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lmx
 * @date 2023/7/4 14:27
 */
@FeignClient(name = "JobApi", url = "${kpu.feign.executor-server:http://127.0.0.1:8767}", path = "/xxl-job-admin")
public interface JobApi {
    /**
     * 定时发送接口
     *
     * @param xxlJobInfo
     * @return
     */
    @RequestMapping(value = "/jobinfo/save", method = RequestMethod.POST)
    R<String> addTimingTask(@RequestBody XxlJobInfoVO xxlJobInfo);

}
