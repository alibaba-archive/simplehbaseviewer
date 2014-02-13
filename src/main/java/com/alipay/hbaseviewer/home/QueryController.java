package com.alipay.hbaseviewer.home;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.hbaseviewer.helper.ViewHelper;
import com.alipay.simplehbase.antlr.auto.StatementsParser.ProgContext;
import com.alipay.simplehbase.antlr.manual.TreeUtil;
import com.alipay.simplehbase.client.SimpleHbaseCellResult;
import com.alipay.simplehbase.client.SimpleHbaseClient;

import com.alipay.simplehbase.core.RawHQLType;
import com.alipay.simplehbase.util.ExceptionUtil;

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
        System.out.println("isColumnMode" + commandForm.isColumnMode());
        try {
            ProgContext progContext = TreeUtil.parseProgContext(command);
            String tableName = TreeUtil.parseTableName(progContext);
            RawHQLType rawHQLType = TreeUtil.parseHQLType(progContext);

            SimpleHbaseClient simpleHbaseClient = hbaseClientManager
                    .getSimpleHbaseClient(tableName);
            switch (rawHQLType) {
                case PUT:
                    simpleHbaseClient.put(command);
                    break;
                case SELECT:
                    List<List<SimpleHbaseCellResult>> cellListList = simpleHbaseClient
                            .select(command);
                    List<String> allFamilyAndQualifierName = CellListWrapper
                            .findAllFamilyAndQualifierName(cellListList);
                    model.addAttribute("allFamilyAndQualifierName",
                            allFamilyAndQualifierName);
                    model.addAttribute("resultList", convert(cellListList));
                    break;
                case DELETE:
                    simpleHbaseClient.delete(command);
                    break;
                default:
                    break;
            }

            model.addAttribute("status", "ok");
        } catch (Exception e) {

            model.addAttribute("status", "error");
            model.addAttribute("exception", ExceptionUtil.getExceptionMsg(e));
        }
        return ViewHelper.getHomeView("query");

    }

    private List<CellListWrapper> convert(
            List<List<SimpleHbaseCellResult>> cellListList) {
        List<CellListWrapper> result = new ArrayList<CellListWrapper>();
        for (List<SimpleHbaseCellResult> cellList : cellListList) {
            result.addAll(CellListWrapper.convertCellListWrapper(cellList));
        }
        return result;
    }

    public HbaseClientManager getHbaseClientManager() {
        return hbaseClientManager;
    }

    public void setHbaseClientManager(HbaseClientManager hbaseClientManager) {
        this.hbaseClientManager = hbaseClientManager;
    }
}