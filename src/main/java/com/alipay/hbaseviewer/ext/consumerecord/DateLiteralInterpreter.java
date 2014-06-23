package com.alipay.hbaseviewer.ext.consumerecord;

import java.util.Date;

import com.alipay.simplehbase.literal.AbstractLiteralInterpreter;
import com.alipay.simplehbase.util.DateUtil;

public class DateLiteralInterpreter extends AbstractLiteralInterpreter {

    @Override
    public Class getTypeCanInterpret() {
        return Date.class;
    }

    @Override
    protected Object interpret_internal(String literalValue) {
        return DateUtil.parse(literalValue, DateUtil.SecondFormat);
    }
}
