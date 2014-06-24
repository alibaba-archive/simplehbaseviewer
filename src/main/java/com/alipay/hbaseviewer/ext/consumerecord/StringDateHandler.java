package com.alipay.hbaseviewer.ext.consumerecord;

import java.util.Date;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.type.AbstractTypeHandler;
import com.alipay.simplehbase.util.DateUtil;

/**
 * @author xinzhi
 * */
public class StringDateHandler extends AbstractTypeHandler {

    @Override
    protected boolean aboutToHandle(Class<?> type) {
        return type == Date.class;
    }

    @Override
    protected byte[] innerToBytes(Class<?> type, Object value) {
        String str = DateUtil.format((Date) value, "yyyy-MM-dd HH:mm:ss");
        return Bytes.toBytes(str);

    }

    @Override
    protected Object innerToObject(Class<?> type, byte[] bytes) {
        String str = Bytes.toString(bytes);
        return DateUtil.parse(str, "yyyy-MM-dd HH:mm:ss");
    }
}
