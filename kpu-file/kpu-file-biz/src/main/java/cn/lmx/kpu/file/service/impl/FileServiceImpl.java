package cn.lmx.kpu.file.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.lmx.basic.base.service.impl.SuperServiceImpl;
import cn.lmx.kpu.file.manager.FileManager;
import cn.lmx.kpu.file.vo.param.FileParamVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import cn.lmx.basic.utils.ArgumentAssert;
import cn.lmx.kpu.file.entity.File;
import cn.lmx.kpu.file.service.FileService;
import cn.lmx.kpu.file.vo.param.FileUploadVO;
import cn.lmx.kpu.file.vo.result.FileResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * @date 2023/7/4 14:27
 */
@Slf4j
@Service
@Transactional(readOnly = true)

public class FileServiceImpl extends SuperServiceImpl<FileManager, Long, File, File, File, FileParamVO, FileResultVO> implements FileService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileResultVO upload(MultipartFile file, FileUploadVO fileUploadVO) {
        return superManager.upload(file, fileUploadVO);
    }

    @Override
    public Map<String, String> findUrlByPath(List<String> paths) {
        return superManager.findUrlByPath(paths);
    }

    @Override
    public Map<Long, String> findUrlById(List<Long> paths) {
        return superManager.findUrlById(paths);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<?> ids) {
        return superManager.removeByIds(ids);
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, List<Long> ids) throws Exception {
        superManager.download(request, response, ids);
    }
}
