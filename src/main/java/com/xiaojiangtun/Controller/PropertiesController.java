package com.xiaojiangtun.Controller;

import com.xiaojiangtun.config.DefaultProperties;
import com.xiaojiangtun.config.MyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置文件调用PropertiesController
 *
 * @author fudayu
 */
@RequestMapping("/properties")
@RestController
public class PropertiesController {

    private static final Logger log = LoggerFactory.getLogger(PropertiesController.class);

    @Autowired
    private DefaultProperties defaultProperties;

    @Autowired
    private MyProperties myProperties;

    @GetMapping("/defaultProperties")
    public DefaultProperties defaultProperties() {
        log.info("=================================================================================================");
        log.info(defaultProperties.toString());
        log.info("=================================================================================================");
        return defaultProperties;
    }

    @GetMapping("/myProperties")
    public MyProperties myProperties() {
        log.info("=================================================================================================");
        log.info(myProperties.toString());
        log.info("=================================================================================================");
        return myProperties;
    }
}