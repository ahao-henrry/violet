package com.ahao.violet.service.file.impl;

import com.ahao.violet.dao.file.FileMapper;
import com.ahao.violet.service.file.IFileService;
import com.ahao.violet.vo.FileVO;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifDirectoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    @Value("${violet.img.url}")
    private String imgUrl;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public String saveImage(MultipartFile multipartFile) {
        String fileRealName = multipartFile.getOriginalFilename();
        File fileFullPath = new File(imgPath);
        if (!fileFullPath.exists()) {
            fileFullPath.mkdir();
        }

        try {
            String fileId = saveImage(multipartFile, imgPath, fileRealName);
            return fileId;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param multipartFile
     * @param fileFullPath
     * @param fileRealName
     * @throws IOException
     */
    private String saveImage(MultipartFile multipartFile, String fileFullPath, String fileRealName) throws IOException {
        String fileType = fileRealName.substring(fileRealName.lastIndexOf("."));
        String fileId = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = fileId + fileType;

        File file = new File(fileFullPath + "/" + fileName);
        File tmp = new File(fileFullPath + "/tmp" + "/" + fileName);
        multipartFile.transferTo(tmp);
        this.rotateImg(tmp, file);

        FileVO fileVO = new FileVO();
        fileVO.setFileId(fileId);
        fileVO.setFileName(fileName);
        fileVO.setFileFullPath(fileFullPath);
        fileVO.setFileRealName(fileRealName);
        fileVO.setFileUrl(imgUrl + fileName);

        fileMapper.addFile(fileVO);
        return fileId;
    }

    private boolean rotateImg(File file, File dest) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            Directory directory = metadata.getFirstDirectoryOfType(ExifDirectoryBase.class);
            int orientation = 0;
            if (directory != null && directory.containsTag(ExifDirectoryBase.TAG_ORIENTATION)) {
                orientation = directory.getInt(ExifDirectoryBase.TAG_ORIENTATION);
                System.out.println(orientation);
            }
            int angle = 0;
            if (6 == orientation || 1 == orientation) {
                //6旋转90
                angle = 90;
            } else if (3 == orientation) {
                //3旋转180
                angle = 180;
            } else if (8 == orientation) {
                //8旋转90
                angle = 270;
            }
            BufferedImage src = ImageIO.read(file);
            BufferedImage des = this.rotate(src, angle);
            String filename = file.getName();
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            ImageIO.write(des, ext, dest);
            return true;
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MetadataException e) {
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }


    private BufferedImage rotate(Image src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // calculate the new image size
        Rectangle rect_des = this.calcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), angel);

        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        // transform
        g2.translate((rect_des.width - src_width) / 2,
                (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

        g2.drawImage(src, null, null);
        return res;
    }

    private Rectangle calcRotatedSize(Rectangle src, int angel) {
        // if angel is greater than 90 degree, we need to do some conversion
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }
}
