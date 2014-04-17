package com.alipay.hbaseviewer.home;

import java.util.ArrayList;
import java.util.List;

import com.alipay.simplehbase.client.SimpleHbaseClient;

/**
 * HbaseClientManager
 * 
 * @author xinzhi.zhang
 * */
public class HbaseClientManager {

    private List<SimpleHbaseClient> list;

    public SimpleHbaseClient getSimpleHbaseClient(String tableName) {
        for (SimpleHbaseClient client : list) {
            if (client.getHbaseTableConfig().getHbaseTableSchema()
                    .getTableName().equals(tableName)) {
                return client;
            }
        }
        return null;
    }

    public List<String> getAllTableNames() {
        List<String> tableNames = new ArrayList<String>();
        for (SimpleHbaseClient client : list) {
            tableNames.add(client.getHbaseTableConfig().getHbaseTableSchema()
                    .getTableName());
        }
        return tableNames;
    }

    public List<SimpleHbaseClient> getList() {
        return list;
    }

    public void setList(List<SimpleHbaseClient> list) {
        this.list = list;
    }
}
