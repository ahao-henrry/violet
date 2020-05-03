package com.ahao.violet.ctrl;

import com.ahao.violet.service.IRootService;
import com.ahao.violet.vo.TextVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahao
 * @since 2019/11/18 下午6:40
 */
@RestController
public class RootCtrl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RootCtrl.class);

    @Autowired
    private IRootService iRootService;

    @GetMapping("/index")
    public String rootMapping() {
        return "index";
    }

    @PostMapping("/upload/text")
    public String uploadText(@RequestBody TextVO textVO) {
        String s = iRootService.uploadText(textVO);

        return "\"name\":\"ahao\"";
    }
}
