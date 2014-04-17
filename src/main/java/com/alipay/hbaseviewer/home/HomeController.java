package com.alipay.hbaseviewer.home;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.hbaseviewer.helper.ViewHelper;

/**
 * HomeController.
 * 
 * @author xinzhi.zhang
 * */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return getHandle();
    }

    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String getHandle() {
        return ViewHelper.getHomeView("index");
    }

}