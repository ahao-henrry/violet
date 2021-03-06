package com.ahao.violet.dao.file;

import com.ahao.violet.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ahao
 * @since 2019/11/2 上午10:44
 */
@Mapper
public interface FileMapper {

    int addFile(FileVO fileVO);

    List<FileVO> search(String key);
}
