package com.alipay.hbaseviewer.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.hbaseviewer.helper.ViewHelper;

/**
 * HomeController.
 * */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return index();
    }

    @RequestMapping(value = "/index.htm")
    public String index() {
        return ViewHelper.getHomeView("index");
    }
}