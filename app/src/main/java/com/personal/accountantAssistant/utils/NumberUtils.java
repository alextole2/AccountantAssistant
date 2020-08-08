package com.personal.accountantAssistant.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    private static final String ZERO_STR = "0";
    private static final String ONE_STR = "1";
    private static final int DECIMAL_PLACES = 2;

    public static boolean toBoolean(final String numericString) {
        Boolean result = Boolean.FALSE;
        if (numericString.equals(ZERO_STR)) {
            result = Boolean.FALSE;
        } else if (numericString.equals(ONE_STR)) {
            result = Boolean.TRUE;
        }
        return result;
    }

    public static Double roundTo(final Double value) {
        return roundTo(new BigDecimal(value.toString()))
                .doubleValue();
    }

    public static Float roundTo(final Float value) {
        return roundTo(new BigDecimal(value.toString()))
                .floatValue();
    }

    public static BigDecimal roundTo(final BigDecimal value) {
        return value
                .setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
    }
}
