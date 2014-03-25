package com.alipay.hbaseviewer.ext;

import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.exception.SimpleHBaseException;

public class DisplayOnlyStringRowKey implements RowKey {

    private String rowkey;

    public DisplayOnlyStringRowKey(String rowkey) {
        this.rowkey = rowkey;
    }

    @Override
    public byte[] toBytes() {
        throw new SimpleHBaseException("Not support.");
    }

    @Override
    public String toString() {
        return rowkey;
    }
}
