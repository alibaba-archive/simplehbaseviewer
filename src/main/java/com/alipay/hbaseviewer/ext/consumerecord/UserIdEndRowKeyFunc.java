package com.alipay.hbaseviewer.ext.consumerecord;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.BytesRowKey;
import com.alipay.simplehbase.client.rowkey.RowKeyUtil;
import com.alipay.simplehbase.client.rowkeytextfun.RowKeyTextFunc;

import com.alipay.simplehbase.util.StringUtil;

public class UserIdEndRowKeyFunc implements RowKeyTextFunc {

    @Override
    public RowKey func(String text) {

        String ownerCardNo = text.trim();

        // ownerCardNo
        byte[] ownerCardNoByte = Bytes.toBytes(StringUtil.reverse(ownerCardNo));

        return RowKeyUtil
                .getEndRowKeyOfPrefix(new BytesRowKey(ownerCardNoByte));
    }

    @Override
    public String funcName() {
        return "uidrowkeyend";
    }

    @Override
    public String desc() {
        return "uidrowkeyend(userid)";
    }

}
