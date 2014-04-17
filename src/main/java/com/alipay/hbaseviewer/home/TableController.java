package com.alipay.hbaseviewer.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.hbaseviewer.helper.ViewHelper;
import com.alipay.simplehbase.client.SimpleHbaseClient;
import com.alipay.simplehbase.config.SimpleHbaseRuntimeSetting;

/**
 * TableController
 * 
 * @author xinzhi.zhang
 * */
@Controller
public class TableController {

    @Autowired
    private HbaseClientManager hbaseClientManager;

    @RequestMapping(value = "/tables.htm", method = RequestMethod.GET)
    public String getHandle(ModelMap model, String tableName) {
        List<String> tables = hbaseClientManager.getAllTableNames();
        model.addAttribute("tables", tables);

        SimpleHbaseClient simpleHbaseClient = hbaseClientManager
                .getSimpleHbaseClient(tableName);
        if (simpleHbaseClient != null) {
            model.addAttribute("columns", simpleHbaseClient
                    .getHbaseTableConfig().getHbaseTableSchema()
                    .findAllColumnSchemas());

            SimpleHbaseRuntimeSetting runtimeSetting = simpleHbaseClient
                    .getSimpleHbaseRuntimeSetting();
            model.addAttribute("rowKeyTextFuncs",
                    runtimeSetting.findAllRowKeyTextFunc());
            model.addAttribute("literalInterpreters",
                    runtimeSetting.findAllLiteralInterpreter());
        }

        return ViewHelper.getHomeView("tables");
    }
}
