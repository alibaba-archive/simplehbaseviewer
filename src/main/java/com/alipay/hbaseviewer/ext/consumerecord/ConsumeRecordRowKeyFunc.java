package com.alipay.hbaseviewer.ext.consumerecord;

import java.util.Date;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.BytesRowKey;
import com.alipay.simplehbase.client.rowkeytextfun.RowKeyTextFunc;
import com.alipay.simplehbase.util.BytesUtil;
import com.alipay.simplehbase.util.DateUtil;
import com.alipay.simplehbase.util.StringUtil;

public class ConsumeRecordRowKeyFunc implements RowKeyTextFunc {

    @Override
    public RowKey func(String text) {

        String[] parts = text.split("\\$");

        String ownerCardNo = parts[0];
        Date gmtBizCreateDate = DateUtil.parse(parts[1], DateUtil.SecondFormat);
        String bizType = parts[2];
        String bizInNo = parts[3];

        // ownerCardNo
        byte[] ownerCardNoByte = Bytes.toBytes(StringUtil.reverse(ownerCardNo));

        // gmtBizCreate
        long gmtBizCreateFormatedLong = gmtBizCreateDate.getTime();
        long gmtBizCreateFormatedLongRev = Long.MAX_VALUE
                - gmtBizCreateFormatedLong;
        byte[] gmtBizCreateByte = Bytes.toBytes(gmtBizCreateFormatedLongRev);

        return new BytesRowKey(BytesUtil.merge(ownerCardNoByte, BytesUtil.ZERO,
                gmtBizCreateByte, BytesUtil.ZERO, Bytes.toBytes(bizType),
                BytesUtil.ZERO, Bytes.toBytes(bizInNo)));

    }

    @Override
    public String funcName() {
        return "consumerecordkey";
    }

    @Override
    public String desc() {
        return "consumerecordkey(\"userid$yyyy-MM-dd_HH:mm:ss$bizType$bizInNo\")";
    }
}
