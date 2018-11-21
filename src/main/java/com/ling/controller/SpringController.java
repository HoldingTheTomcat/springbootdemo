package com.ling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by LingZi on 2018/11/21.
 */
@Controller
public class SpringController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("ling")
    @ResponseBody
    public String hellow(){
        logger.debug("LINGZI");
        return "lingzi";
    }
}
