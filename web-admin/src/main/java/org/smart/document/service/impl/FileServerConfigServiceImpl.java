package org.smart.document.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smart.document.entity.FileServerConfig;
import org.smart.document.mapper.FileServerConfigMapper;
import org.smart.document.service.FileServerConfigService;
import org.springframework.stereotype.Service;

/**
 * 文件服务器配置 Service 实现类
 */
@Service
public class FileServerConfigServiceImpl extends ServiceImpl<FileServerConfigMapper, FileServerConfig> implements FileServerConfigService {

}
