package cn.lmx.kpu.file.manager;

import cn.lmx.basic.base.manager.SuperManager;
import cn.lmx.basic.base.service.SuperService;
import cn.lmx.kpu.file.entity.File;
import cn.lmx.kpu.file.vo.param.FileUploadVO;
import cn.lmx.kpu.file.vo.result.FileResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 增量文件上传日志
 * </p>
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface FileManager extends SuperManager<File> {

    /**
     * 上传附件
     *
     * @param file         文件
     * @param attachmentVO 参数
     * @return 附件
     */
    FileResultVO upload(MultipartFile file, FileUploadVO attachmentVO);

    /**
     * 获取文件的临时访问链接
     *
     * @param paths 文件相对路径
     * @return
     */
    Map<String, String> findUrlByPath(List<String> paths);

    /**
     * 获取文件的临时访问链接
     *
     * @param ids 文件id
     * @return
     */
    Map<Long, String> findUrlById(List<Long> ids);

    /**
     * 下载文件
     *
     * @param request  请求头
     * @param response 响应头
     * @param ids      文件id
     * @throws Exception
     */
    void download(HttpServletRequest request, HttpServletResponse response, List<Long> ids) throws Exception;
}
