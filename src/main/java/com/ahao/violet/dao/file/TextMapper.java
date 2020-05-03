package com.ahao.violet.dao.file;

import com.ahao.violet.vo.TextVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ahao
 * @since 2019/11/2 上午10:44
 */
@Mapper
public interface TextMapper {

    TextVO searchOne(String key);

    int add(TextVO textVO);

}
