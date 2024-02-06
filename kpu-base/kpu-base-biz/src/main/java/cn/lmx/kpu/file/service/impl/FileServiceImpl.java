package cn.lmx.kpu.file.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.basic.utils.BeanPlusUtil;
import cn.lmx.kpu.file.entity.File;
import cn.lmx.kpu.file.manager.FileManager;
import cn.lmx.kpu.file.service.FileService;
import cn.lmx.kpu.file.strategy.FileContext;
import cn.lmx.kpu.file.vo.param.FileUploadVO;
import cn.lmx.kpu.file.vo.result.FileResultVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务实现类
 * 增量文件上传日志
 * </p>
 *
 * @author lmx
 * @date 2024/02/07
 * @create [2024/02/07] [lmx] [初始创建]
 */
@Slf4j
@Service
@Transactional(readOnly = true)

public class FileServiceImpl extends SuperServiceImpl<FileManager, Long, File> implements FileService {
    @Resource
    private FileContext fileContext;
    @Resource
    private FileManager fileManager;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileResultVO upload(MultipartFile file, FileUploadVO fileUploadVO) {
        File fileFile = fileContext.upload(file, fileUploadVO);
        fileManager.save(fileFile);
        return BeanPlusUtil.toBean(fileFile, FileResultVO.class);
    }

    @Override
    public Map<String, String> findUrlByPath(List<String> paths) {
        return fileContext.findUrlByPath(paths);
    }

    @Override
    public Map<Long, String> findUrlById(List<Long> paths) {
        return fileContext.findUrlById(paths);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return false;
        }
        List<File> list = list(Wrappers.<File>lambdaQuery().in(File::getId, ids));
        if (list.isEmpty()) {
            return false;
        }
        fileManager.removeByIds(ids);
        return fileContext.delete(list);
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, List<Long> ids) throws Exception {
        List<File> list = fileManager.listByIds(ids);
        ArgumentAssert.notEmpty(list, "请配置正确的文件存储类型");

        fileContext.download(request, response, list);
    }
}
