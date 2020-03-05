package com.ahao.violet.service.file.impl;

import com.ahao.violet.dao.file.FileMapper;
import com.ahao.violet.service.file.IFileService;
import com.ahao.violet.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ahao
 * @since 2019/11/1 下午7:30
 */
@Service
public class FileServiceImpl implements IFileService {
    @Value("${violet.img.path}")
    private String imgPath;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public boolean saveImage(MultipartFile multipartFile) {
        String fileRealName = multipartFile.getOriginalFilename();
        File fileFullPath = new File(imgPath);
        if (!fileFullPath.exists()) {
            fileFullPath.mkdir();
        }

        try {
            saveImage(multipartFile, imgPath, fileRealName);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     *
     * @param multipartFile
     * @param fileFullPath
     * @param fileRealName
     * @throws IOException
     */
    private void saveImage(MultipartFile multipartFile, String fileFullPath, String fileRealName) throws IOException {
        String fileType = fileRealName.substring(fileRealName.lastIndexOf("."));
        String fileId = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = fileId + fileType;

        File file = new File(fileFullPath + "/" + fileName);
        multipartFile.transferTo(file);

        FileVO fileVO = new FileVO();
        fileVO.setFileId(fileId);
        fileVO.setFileName(fileName);
        fileVO.setFileFullPath(fileFullPath);
        fileVO.setFileRealName(fileRealName);

        fileMapper.addFile(fileVO);
    }
}
