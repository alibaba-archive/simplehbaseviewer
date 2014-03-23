package com.alipay.hbaseviewer.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;

import com.alipay.simplehbase.antlr.auto.StatementsParser.ProgContext;
import com.alipay.simplehbase.antlr.manual.TreeUtil;
import com.alipay.simplehbase.client.SimpleHbaseCellResult;
import com.alipay.simplehbase.client.SimpleHbaseClient;
import com.alipay.simplehbase.core.RawHQLType;
import com.alipay.simplehbase.util.ExceptionUtil;

public class QueryExecutor {

    public static void execute(HbaseClientManager hbaseClientManager,
            String hql, ModelMap model) {
        try {
            ProgContext progContext = TreeUtil.parseProgContext(hql);
            String tableName = TreeUtil.parseTableName(progContext);
            RawHQLType rawHQLType = TreeUtil.parseHQLType(progContext);

            SimpleHbaseClient simpleHbaseClient = hbaseClientManager
                    .getSimpleHbaseClient(tableName);
            switch (rawHQLType) {
                case PUT:
                    simpleHbaseClient.put(hql);
                    break;
                case SELECT:
                    List<List<SimpleHbaseCellResult>> cellListList = simpleHbaseClient
                            .select(hql);
                    List<String> allFamilyAndQualifierName = CellListWrapper
                            .findAllFamilyAndQualifierName(cellListList);
                    model.addAttribute("allFamilyAndQualifierName",
                            allFamilyAndQualifierName);
                    model.addAttribute("resultList", convert(cellListList));
                    break;
                case DELETE:
                    simpleHbaseClient.delete(hql);
                    break;
                default:
                    break;
            }

            model.addAttribute("status", "ok");
        } catch (Exception e) {

            model.addAttribute("status", "error");
            model.addAttribute("exception", ExceptionUtil.getExceptionMsg(e));
        }
    }

    private static List<CellListWrapper> convert(
            List<List<SimpleHbaseCellResult>> cellListList) {
        List<CellListWrapper> result = new ArrayList<CellListWrapper>();
        for (List<SimpleHbaseCellResult> cellList : cellListList) {
            result.addAll(CellListWrapper.convertCellListWrapper(cellList));
        }
        return result;
    }
}
