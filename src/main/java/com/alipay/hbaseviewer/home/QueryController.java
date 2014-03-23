package com.alipay.hbaseviewer.home;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.hbaseviewer.helper.ViewHelper;
import com.alipay.simplehbase.util.DateUtil;

/**
 * HomeController.
 * */
@Controller
public class QueryController {

    @Autowired
    private HbaseClientManager hbaseClientManager;

    @RequestMapping(value = "/query.htm", method = RequestMethod.GET)
    public String getHandle() {
        return ViewHelper.getHomeView("query");
    }

    @RequestMapping(value = "/query.htm", method = RequestMethod.POST)
    public String postHandle(CommandForm commandForm, ModelMap model) {

        String command = commandForm.getCommand();
        model.addAttribute("commandForm", commandForm);

        QueryExecutor.execute(hbaseClientManager, command, model);

        model.addAttribute("nowTime",
                DateUtil.format(new Date(), DateUtil.SecondFormat));

        return ViewHelper.getHomeView("query");

    }

    public HbaseClientManager getHbaseClientManager() {
        return hbaseClientManager;
    }

    public void setHbaseClientManager(HbaseClientManager hbaseClientManager) {
        this.hbaseClientManager = hbaseClientManager;
    }
}