package com.alipay.hbaseviewer.ext.consumerecord;

import com.alipay.hbaseviewer.ext.DisplayOnlyStringRowKey;
import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.rowkey.handler.RowKeyHandler;
import com.alipay.simplehbase.type.ext.HexBytes;

public class ConsumeRecordRowKeyHandler2 implements RowKeyHandler {

    @Override
    public RowKey convert(byte[] row) {
        HexBytes bytes = new HexBytes(row);
        return new DisplayOnlyStringRowKey(bytes.toString());
    }

}
