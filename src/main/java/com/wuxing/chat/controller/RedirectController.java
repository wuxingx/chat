package com.wuxing.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zx on 2017/6/14.
 */
@RestController
@RequestMapping("/chat")
public class RedirectController {

    // log4日志记录
    public Logger logger = LoggerFactory.getLogger(RedirectController.class);

    @RequestMapping("/pages/{pagePath}")
    public String redirect(
            @PathVariable String modulPath,
            @PathVariable String pagePath
            ) {
        logger.info("跳转>>>>>>" + "/chat/jsp/" + pagePath);
        return pagePath;
    }
}
