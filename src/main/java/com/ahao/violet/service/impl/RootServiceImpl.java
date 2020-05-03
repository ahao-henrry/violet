package com.ahao.violet.service.impl;

import com.ahao.violet.dao.file.FileMapper;
import com.ahao.violet.dao.file.TextMapper;
import com.ahao.violet.service.IRootService;
import com.ahao.violet.vo.TextVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ahao
 * @since 2020/5/3 下午2:53
 */
@Service
public class RootServiceImpl implements IRootService {

    @Autowired
    private TextMapper textMapper;

    @Override
    public String uploadText(TextVO textVO) {
        int update = textMapper.add(textVO);
        if (update > 0) {
            return "suc";
        }
        return null;
    }
}
