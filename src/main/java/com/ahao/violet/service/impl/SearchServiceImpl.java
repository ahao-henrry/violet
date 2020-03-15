package com.ahao.violet.service.impl;

import com.ahao.violet.dao.file.FileMapper;
import com.ahao.violet.service.ISearchService;
import com.ahao.violet.vo.FileVO;
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

    @Override
    public List<FileVO> search(String key) {
        return fileMapper.search(key);
    }
}
