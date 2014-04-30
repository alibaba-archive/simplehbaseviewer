package com.alipay.hbaseviewer.ext.consumerecord;

import org.apache.hadoop.hbase.util.Bytes;
import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.BytesRowKey;
import com.alipay.simplehbase.client.rowkeytextfun.RowKeyTextFunc;
import com.alipay.simplehbase.util.BytesUtil;

import com.alipay.simplehbase.util.StringUtil;

public class ConsumeRecordBizInNoRowKeyFunc implements RowKeyTextFunc {

    @Override
    public RowKey func(String text) {

        String[] parts = text.split("\\$");

        String ownerCardNo = parts[0];
        String bizType = parts[1];
        String bizInNo = parts[2];

        return new BytesRowKey(BytesUtil.merge(
                Bytes.toBytes(StringUtil.reverse(ownerCardNo)), BytesUtil.ZERO,
                Bytes.toBytes(bizType), BytesUtil.ZERO, Bytes.toBytes(bizInNo)));

    }

    @Override
    public String funcName() {
        return "bizinnokey";
    }

    @Override
    public String desc() {
        return "bizinnokey(\"ownerCardNo$bizType$bizInNo\")";
    }
}
