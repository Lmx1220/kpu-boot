package cn.lmx.kpu.file.manager.impl;

import org.springframework.stereotype.Service;
import cn.lmx.basic.base.manager.impl.SuperManagerImpl;
import cn.lmx.kpu.file.entity.File;
import cn.lmx.kpu.file.manager.FileManager;
import cn.lmx.kpu.file.mapper.FileMapper;

/**
 * 文件
 *
 * @author lmx
 * @date 2024/02/07  02:04
 */
@Service
public class FileManagerImpl extends SuperManagerImpl<FileMapper, File> implements FileManager {

}
