package com.ahao.violet.ctrl;

import com.ahao.violet.service.ISearchService;
import com.ahao.violet.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @author ahao
 * @since 2020/3/12 下午9:49
 */
@RestController
public class SearchCtrl {
    @Autowired
    private ISearchService iSearchService;

    @GetMapping("/search")
    public List<FileVO> search(String key) {
        return iSearchService.search(key);
    }
}
