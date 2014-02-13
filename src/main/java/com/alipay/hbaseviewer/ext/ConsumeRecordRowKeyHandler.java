package com.alipay.hbaseviewer.ext;

import java.util.Date;
import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.client.rowkey.handler.RowKeyHandler;
import com.alipay.simplehbase.util.BytesUtil;
import com.alipay.simplehbase.util.DateUtil;
import com.alipay.simplehbase.util.StringUtil;
import com.alipay.simplehbase.util.Util;

public class ConsumeRecordRowKeyHandler implements RowKeyHandler {

    @Override
    public Object convert(byte[] row) {
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
        return sb;

    }
}
