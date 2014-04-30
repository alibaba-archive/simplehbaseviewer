package com.alipay.hbaseviewer.ext.consumerecord;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.hbase.util.Bytes;
import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.BytesRowKey;
import com.alipay.simplehbase.client.rowkeytextfun.RowKeyTextFunc;
import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.util.BytesUtil;

import com.alipay.simplehbase.util.StringUtil;

public class ConsumeRecordReDoRowKeyFunc implements RowKeyTextFunc {

    @Override
    public RowKey func(String text) {
        try {

            String[] parts = text.split("\\$");

            String ownerCardNo = parts[0];
            String bizType = parts[1];
            String bizInNo = parts[2];

            // userId进行MD5加密
            String userIdMD5 = DigestUtils
                    .md5Hex(ownerCardNo.getBytes("utf-8"));

            // md5前4位
            byte[] userIdMD5Byte = Bytes.toBytes(userIdMD5.substring(0, 4));

            byte[] sep = Bytes.toBytes("_");

            return new BytesRowKey(BytesUtil.merge(userIdMD5Byte, sep,
                    Bytes.toBytes(StringUtil.reverse(ownerCardNo)), sep,
                    Bytes.toBytes(bizType), sep, Bytes.toBytes(bizInNo)));

        } catch (Exception e) {
            throw new SimpleHBaseException(e);
        }

    }

    @Override
    public String funcName() {
        return "consumerecordredokey";
    }

    @Override
    public String desc() {
        return "consumerecordredokey(\"ownerCardNo$bizType$bizInNo\")";
    }
}
