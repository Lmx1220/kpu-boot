package cn.lmx.kpu.file.strategy;

import org.springframework.web.multipart.MultipartFile;
import cn.lmx.kpu.file.domain.FileDeleteBO;
import cn.lmx.kpu.file.domain.FileGetUrlBO;
import cn.lmx.kpu.file.entity.File;

import java.util.List;
import java.util.Map;

/**
 * 文件策略接口
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
public interface FileStrategy {
    /**
     * 文件上传
     *
     * @param file    文件
     * @param bucket  桶
     * @param bizType 业务类型
     * @return 文件对象
     */
    File upload(MultipartFile file, String bucket, String bizType);

    /**
     * 删除源文件
     *
     * @param fileDeleteBO 待删除文件
     * @return 是否成功
     */
    boolean delete(FileDeleteBO fileDeleteBO);

    /**
     * 根据路径获取访问地址
     *
     * @param fileGets 文件查询对象
     * @return
     */
    Map<String, String> findUrl(List<FileGetUrlBO> fileGets);

    /**
     * 根据路径获取访问地址
     *
     * @param fileGet 文件查询对象
     * @return
     */
    String getUrl(FileGetUrlBO fileGet);
}
