package com.ahao.violet.service;

import com.ahao.violet.vo.FileVO;

import java.util.List;

/**
 * @author ahao
 * @since 2020/3/15 下午2:36
 */
public interface ISearchService {

    List<FileVO> search(String key);
}
