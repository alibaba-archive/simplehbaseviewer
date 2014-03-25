package com.alipay.hbaseviewer.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.SimpleHbaseCellResult;
import com.alipay.simplehbase.config.SimpleHbaseConstants;
import com.alipay.simplehbase.util.DateUtil;

public class CellListWrapper {

    private static Log log = LogFactory.getLog(CellListWrapper.class);

    private static String familyAndQualifierName(SimpleHbaseCellResult cell) {
        return cell.getFamilyStr()
                + SimpleHbaseConstants.Family_Qualifier_Separator
                + cell.getQualifierStr();
    }

    public static List<String> findAllFamilyAndQualifierName(
            List<List<SimpleHbaseCellResult>> cellListList) {
        Set<String> set = new TreeSet<String>();
        for (List<SimpleHbaseCellResult> cellList : cellListList) {
            for (SimpleHbaseCellResult cell : cellList) {
                set.add(familyAndQualifierName(cell));
            }
        }
        return new ArrayList<String>(set);
    }

    public static List<CellListWrapper> convertCellListWrapper(
            List<SimpleHbaseCellResult> cellList) {
        Set<Date> set = new TreeSet<Date>(Collections.reverseOrder());
        for (SimpleHbaseCellResult cell : cellList) {
            set.add(cell.getTsDate());
        }

        List<CellListWrapper> result = new ArrayList<CellListWrapper>();

        for (Date ts : set) {
            CellListWrapper cellListWrapper = new CellListWrapper();
            cellListWrapper.setTs(ts);
            result.add(cellListWrapper);
            for (SimpleHbaseCellResult cell : cellList) {
                if (cell.getTsDate().equals(ts)) {
                    cellListWrapper.addCell(cell);
                    cellListWrapper.setRowkey(cell.getRowKey());
                }
            }
        }
        return result;
    }

    private List<SimpleHbaseCellResult> cellList = new ArrayList<SimpleHbaseCellResult>();
    private Date                        ts;
    private RowKey                      rowkey;

    public CellListWrapper() {
    }

    private void addCell(SimpleHbaseCellResult cell) {
        cellList.add(cell);
    }

    public Object findValue(String familyAndQualifierName) {
        for (SimpleHbaseCellResult cell : cellList) {
            if (familyAndQualifierName(cell).equals(familyAndQualifierName)) {

                log.info(cell);

                Object result = cell.getValueObject();
                if (result == null) {
                    return "NULL";
                } else {
                    return result;
                }
            }
        }
        return "";
    }

    public Date getTs() {
        return ts;
    }

    public String getTsString() {
        return DateUtil.format(ts, DateUtil.MSFormat);
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public RowKey getRowkey() {
        return rowkey;
    }

    public void setRowkey(RowKey rowkey) {
        this.rowkey = rowkey;
    }
}
