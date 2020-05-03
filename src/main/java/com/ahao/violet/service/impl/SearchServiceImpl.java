package com.ahao.violet.service.impl;

import com.ahao.violet.dao.file.FileMapper;
import com.ahao.violet.dao.file.TextMapper;
import com.ahao.violet.service.ISearchService;
import com.ahao.violet.vo.FileVO;
import com.ahao.violet.vo.TextVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ahao
 * @since 2020/3/15 下午2:37
 */
@Service
public class SearchServiceImpl implements ISearchService {
    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private TextMapper textMapper;

    @Override
    public List<FileVO> search(String key) {
        return fileMapper.search(key);
    }

    @Override
    public TextVO searchOne(String key) {
        TextVO textVO = textMapper.searchOne(key);
        String fileId = textVO.getFileId();
        if (null == fileId) {
            return textVO;
        }

        FileVO fileVO = fileMapper.searchByFileId(fileId);
        textVO.setFileVO(fileVO);
        return textVO;
    }
}
