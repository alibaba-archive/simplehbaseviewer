package com.alipay.hbaseviewer.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.hbaseviewer.helper.ViewHelper;

@Controller
public class ExampleController {

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String getHandle() {
        return ViewHelper.getHomeView("example");
    }
}
