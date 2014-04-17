package com.alipay.hbaseviewer.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.hbaseviewer.helper.ViewHelper;
import com.alipay.simplehbase.util.DateUtil;

/**
 * ExampleController
 * 
 * @author xinzhi.zhang
 * */
@Controller
public class ExampleController {

    private static List<String> insertHqls        = new ArrayList<String>();
    private static List<String> selectHqls        = new ArrayList<String>();
    private static List<String> deleteHqls        = new ArrayList<String>();

    private static List<String> consumeRecordHqls = new ArrayList<String>();
    private static List<String> billCategoryHqls  = new ArrayList<String>();

    static {
        insertHqls
                .add("insert into MyRecordV05 ( name,age ) values ( \"allen\", \"30\" ) rowkey is intkey (\"0\")   ");
        insertHqls
                .add("insert into MyRecordV05 ( fatname,age ) values ( \"allenfat\", \"30\" ) rowkey is intkey (\"0\") ");
        insertHqls
                .add("insert into MyRecordV05 ( name ) values ( null ) rowkey is intkey (\"0\")");
        insertHqls
                .add("insert into MyRecordV05 ( name,age ) values ( \"allen\", \"30\" ) rowkey is intkey (\"0\") ts is \"2014-10-01_10:12:12:123\" ");

        selectHqls
                .add("select * from MyRecordV05 startkey is intkey (\"0\") , endkey is intkey (\"100\") maxversion 10 startTS is \"2014-01-01\" , endTS is \"2100-01-01\" limit 10 , 20");
        selectHqls
                .add("select * from MyRecordV05 startkey is intkey (\"0\")  startTS is \"2014-01-01\" limit 10 , 20");
        selectHqls.add("select * from MyRecordV05 startkey is intkey (\"0\")");
        selectHqls.add("select * from MyRecordV05 rowkey is intkey (\"0\")");
        selectHqls
                .add("select ( name ) from MyRecordV05 rowkey is intkey (\"0\")");
        selectHqls
                .add("select ( name ) from MyRecordV05 where name equal \"allen\" rowkey is intkey (\"0\")");

        deleteHqls.add("delete * from MyRecordV05 startkey is intkey (\"0\") ");

        consumeRecordHqls
                .add("insert into T_CONSUME_RECORD ( OWNER_CARD_NO ) values ( \"2088123456781234\" ) rowkey is consumerecordkey ( \"111$2000-01-01_01:01:01$TRADE$222\" )");
        consumeRecordHqls
                .add("select * from  T_CONSUME_RECORD  rowkey is consumerecordkey ( \"111$2000-01-01_01:01:01$TRADE$222\" )");
        consumeRecordHqls
                .add("delete * from  T_CONSUME_RECORD  rowkey is consumerecordkey ( \"111$2000-01-01_01:01:01$TRADE$222\" )");

        billCategoryHqls
                .add("insert into billCategory  ( userId ) values ( \"2088123456781234\" ) rowkey is billCategoryRowKey (\"2088123456781234_20140401\")");
        billCategoryHqls
                .add("select * from  billCategory   rowkey is billCategoryRowKey (\"2088123456781234_20140401\") ");
        billCategoryHqls
                .add("delete * from  billCategory   rowkey is billCategoryRowKey (\"2088123456781234_20140401\") ");

    }
    @Autowired
    private HbaseClientManager  hbaseClientManager;

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String getHandle(ModelMap model) {
        model.addAttribute("insertHqls", insertHqls);
        model.addAttribute("selectHqls", selectHqls);
        model.addAttribute("deleteHqls", deleteHqls);
        model.addAttribute("consumeRecordHqls", consumeRecordHqls);
        model.addAttribute("billCategoryHqls", billCategoryHqls);
        return ViewHelper.getHomeView("example");
    }

    @RequestMapping(value = "/exampletest", method = RequestMethod.GET)
    public String exeExampleHql(ModelMap model) {

        for (String hql : insertHqls) {
            ModelMap tmodel = new ModelMap();
            QueryExecutor.execute(hbaseClientManager, hql, tmodel);
            if (!tmodel.get("status").equals("ok")) {
                model.addAttribute("status", tmodel.get("status"));
                model.addAttribute("exception", tmodel.get("exception"));
                return getHandle(tmodel);
            }
        }

        for (String hql : selectHqls) {
            ModelMap tmodel = new ModelMap();
            QueryExecutor.execute(hbaseClientManager, hql, tmodel);
            if (!tmodel.get("status").equals("ok")) {
                model.addAttribute("status", tmodel.get("status"));
                model.addAttribute("exception", tmodel.get("exception"));
                return getHandle(tmodel);
            }
        }

        for (String hql : deleteHqls) {
            ModelMap tmodel = new ModelMap();
            QueryExecutor.execute(hbaseClientManager, hql, tmodel);
            if (!tmodel.get("status").equals("ok")) {
                model.addAttribute("status", tmodel.get("status"));
                model.addAttribute("exception", tmodel.get("exception"));
                return getHandle(tmodel);
            }
        }

        for (String hql : consumeRecordHqls) {
            ModelMap tmodel = new ModelMap();
            QueryExecutor.execute(hbaseClientManager, hql, tmodel);
            if (!tmodel.get("status").equals("ok")) {
                model.addAttribute("status", tmodel.get("status"));
                model.addAttribute("exception", tmodel.get("exception"));
                return getHandle(tmodel);
            }
        }

        for (String hql : billCategoryHqls) {
            ModelMap tmodel = new ModelMap();
            QueryExecutor.execute(hbaseClientManager, hql, tmodel);
            if (!tmodel.get("status").equals("ok")) {
                model.addAttribute("status", tmodel.get("status"));
                model.addAttribute("exception", tmodel.get("exception"));
                return getHandle(tmodel);
            }
        }

        model.addAttribute("status", "ok");
        model.addAttribute("nowTime",
                DateUtil.format(new Date(), DateUtil.SecondFormat));
        return getHandle(model);
    }

    public HbaseClientManager getHbaseClientManager() {
        return hbaseClientManager;
    }

    public void setHbaseClientManager(HbaseClientManager hbaseClientManager) {
        this.hbaseClientManager = hbaseClientManager;
    }
}
