package com.ahao.violet.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ahao
 * @since 2019/11/18 下午6:40
 */
@Controller
public class RootCtrl {

    @GetMapping("/")
    public String rootMapping() {
        return "index";
    }
}
