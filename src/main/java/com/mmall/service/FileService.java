package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-29 18:28
 **/
public interface FileService {

    String upload(MultipartFile file, String path);
}
