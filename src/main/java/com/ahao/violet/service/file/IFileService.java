package com.ahao.violet.service.file;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ahao
 * @since 2019/11/1 下午7:32
 */
public interface IFileService {

    /**
     * 将图片存到服务器以及 mysql
     *
     * @param file
     * @return
     */
    String saveImage(MultipartFile file);

}
