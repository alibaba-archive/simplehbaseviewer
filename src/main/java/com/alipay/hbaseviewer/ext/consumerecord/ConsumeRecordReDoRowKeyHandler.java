package com.alipay.hbaseviewer.ext.consumerecord;

import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.hbaseviewer.ext.DisplayOnlyStringRowKey;
import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.handler.RowKeyHandler;
import com.alipay.simplehbase.util.BytesUtil;

import com.alipay.simplehbase.util.StringUtil;
import com.alipay.simplehbase.util.Util;

public class ConsumeRecordReDoRowKeyHandler implements RowKeyHandler {

    @Override
    public RowKey convert(byte[] row) {
        byte[] sep = Bytes.toBytes("_");

        StringBuilder sb = new StringBuilder();
        List<byte[]> list = BytesUtil.split(row, sep);
        Util.check(list.size() == 4);

        sb.append(StringUtil.reverse(Bytes.toString(list.get(1))));
        sb.append(" ");

        sb.append(Bytes.toString(list.get(2)));
        sb.append(" ");

        sb.append(Bytes.toString(list.get(3)));

        return new DisplayOnlyStringRowKey(sb.toString());

    }
}
