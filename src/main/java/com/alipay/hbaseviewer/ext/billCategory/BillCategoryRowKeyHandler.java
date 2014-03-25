package com.alipay.hbaseviewer.ext.billCategory;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.hbaseviewer.ext.DisplayOnlyStringRowKey;
import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.handler.RowKeyHandler;
import com.alipay.simplehbase.util.BytesUtil;
import com.alipay.simplehbase.util.StringUtil;

public class BillCategoryRowKeyHandler implements RowKeyHandler {
    public RowKey convert(byte[] key) {
        StringBuilder sb = new StringBuilder();
        String reverseUserId = Bytes.toString(BytesUtil.subBytes(key, 5, 16));
        String userId = StringUtil.reverse(reverseUserId);
        sb.append(userId);
        sb.append("_");
        String reverseCycle = Bytes.toString(BytesUtil.subBytes(key, 22, 8));
        int cycle = 100000000 - Integer.parseInt(reverseCycle);
        sb.append(cycle);
        return new DisplayOnlyStringRowKey(sb.toString());
    }
}
