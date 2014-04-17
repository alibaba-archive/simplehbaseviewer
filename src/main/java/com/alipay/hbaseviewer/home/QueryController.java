package com.alipay.hbaseviewer.home;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.hbaseviewer.helper.ViewHelper;
import com.alipay.simplehbase.util.DateUtil;
import com.alipay.simplehbase.util.StringUtil;

/**
 * HomeController.
 * 
 * @author xinzhi.zhang
 * */
@Controller
public class QueryController {

    private static Log         log = LogFactory.getLog(QueryController.class);

    @Autowired
    private HbaseClientManager hbaseClientManager;

    @RequestMapping(value = "/query.htm", method = RequestMethod.GET)
    public String getHandle() {
        return ViewHelper.getHomeView("query");
    }

    @RequestMapping(value = "/query.htm", method = RequestMethod.POST)
    public String postHandle(CommandForm commandForm, ModelMap model) {
        log.info(commandForm);

        String fullCommand = commandForm.getFullCommand();
        String command = commandForm.getCommand();
        if (StringUtil.isEmptyString(command)) {
            command = fullCommand;
            commandForm.setCommand(command);
        }

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