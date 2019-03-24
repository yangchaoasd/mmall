package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.FileService;
import com.mmall.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-29 18:29
 **/
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    public String upload(MultipartFile file, String path) {

        String fileName = file.getOriginalFilename();
        String uploadFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        log.info("开始上传文件，上传的文件名是:{}，文件路径:{}，新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true); //设置可写
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            // 文件上传成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            // 文件已经上传到ftp服务器上
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常:{}", e);
            return null;
        }
        return targetFile.getName();
    }
}
