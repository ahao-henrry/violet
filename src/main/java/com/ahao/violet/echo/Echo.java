package com.ahao.violet.echo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ahao
 * @since 2019/11/1 下午6:47
 */
@RestController
@RequestMapping("/echo")
public class Echo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("")
    public String echo() {
        return DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }
}
