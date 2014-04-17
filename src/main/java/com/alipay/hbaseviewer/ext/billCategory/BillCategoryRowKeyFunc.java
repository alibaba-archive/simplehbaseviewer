package com.alipay.hbaseviewer.ext.billCategory;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.BytesRowKey;
import com.alipay.simplehbase.client.rowkeytextfun.RowKeyTextFunc;
import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.util.BytesUtil;
import com.alipay.simplehbase.util.StringUtil;

public class BillCategoryRowKeyFunc implements RowKeyTextFunc {

    @Override
    public RowKey func(String text) {
        try {
            String[] parts = text.split("_");
            String userId = parts[0];
            String billCycle = parts[1];

            // userId进行MD5加密
            String userIdMD5 = DigestUtils.md5Hex(userId.getBytes("utf-8"));

            // md5前4位
            byte[] userIdMD5Byte = Bytes.toBytes(userIdMD5.substring(0, 4));

            // userId
            byte[] userIdByte = Bytes.toBytes(StringUtil.reverse(userId));

            // 账期
            int reverseCycle = 100000000 - Integer.parseInt(billCycle);
            byte[] cycleByte = Bytes.toBytes(reverseCycle + "");

            return new BytesRowKey(BytesUtil.merge(userIdMD5Byte,
                    Bytes.toBytes("_"), userIdByte, Bytes.toBytes("_"),
                    cycleByte));
        } catch (Exception e) {
            throw new SimpleHBaseException(e);
        }
    }

    @Override
    public String funcName() {
        return "billCategoryRowKey";
    }

    @Override
    public String desc() {
        return "billCategoryRowKey(\"userid_billCycle\")";
    }

}
