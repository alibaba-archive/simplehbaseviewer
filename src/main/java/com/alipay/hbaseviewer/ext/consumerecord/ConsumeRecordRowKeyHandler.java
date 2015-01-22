package com.alipay.hbaseviewer.ext.consumerecord;

import java.util.Date;
import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.hbaseviewer.ext.DisplayOnlyStringRowKey;
import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.handler.RowKeyHandler;
import com.alipay.simplehbase.type.ext.HexBytes;
import com.alipay.simplehbase.util.BytesUtil;
import com.alipay.simplehbase.util.DateUtil;
import com.alipay.simplehbase.util.StringUtil;
import com.alipay.simplehbase.util.Util;

public class ConsumeRecordRowKeyHandler implements RowKeyHandler {

    @Override
    public RowKey convert(byte[] row) {
        StringBuilder sb = new StringBuilder();
        List<byte[]> list = BytesUtil.split(row, BytesUtil.ZERO);
        Util.check(list.size() == 4);

        sb.append(StringUtil.reverse(Bytes.toString(list.get(0))));
        sb.append(" ");

        Date date = new Date(Long.MAX_VALUE - Bytes.toLong(list.get(1)));
        sb.append(DateUtil.format(date, DateUtil.MSFormat));
        sb.append(" ");

        sb.append(Bytes.toString(list.get(2)));
        sb.append(" ");

        sb.append(Bytes.toString(list.get(3)));

        return new DisplayOnlyStringRowKey(sb.toString());

    }

    public static void main(String[] args) {
        HexBytes hb = new HexBytes(
                "39 38 32 36 34 33 34 31 31 32 30 31 38 38 30 32 00 7F FF FE B7 74 2E D4 53 00 54 52 41 44 45 00 32 30 31 34 30 39 31 39 31 31 30 30 31 30 30 30 33 31 33 32 30 30 30 30 30 30 30 31");
        System.out.println(new ConsumeRecordRowKeyHandler().convert(hb
                .getData()));

    }
}
