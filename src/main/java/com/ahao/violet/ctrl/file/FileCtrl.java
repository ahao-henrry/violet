package com.ahao.violet.ctrl.file;

import com.ahao.violet.service.file.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ahao
 * @since 2019/11/1 下午6:45
 */
@RestController
@RequestMapping("/file")
public class FileCtrl {
    @Autowired
    private IFileService iFileService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "something is wrong";
        }

        String fileName = file.getOriginalFilename();
        if (!fileName.contains(".jpg") && !fileName.contains(".png")) {
            return "something is wrong";
        }

        String fileId = iFileService.saveImage(file);
        if (null == fileId) {
            return "something is wrong";
        }

        return fileId;
    }

    @GetMapping("/download")
    public String download(String fileId) {
        return "ll";
    }

    @GetMapping("/show")
    public String show(String fileId) {
        return "ll";
    }
}
