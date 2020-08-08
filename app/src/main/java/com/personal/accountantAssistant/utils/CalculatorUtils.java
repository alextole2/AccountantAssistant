package com.personal.accountantAssistant.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class CalculatorUtils {

    public static BigDecimal getPercentRespectiveTo(BigDecimal xValue, BigDecimal totalValue) {
        return xValue
                .multiply(new BigDecimal(100))
                .divide(totalValue, RoundingMode.HALF_UP);
    }

    public static BigDecimal getMitDifferenceFromHigherOf(BigDecimal value1, BigDecimal value2) {
        final BigDecimal TWO = new BigDecimal(2);
        final int HALF_UP = BigDecimal.ROUND_HALF_UP;
        int compare = value1.compareTo(value2);
        return compare >= 0 ?
                value2.add(value1.subtract(value2).divide(TWO, HALF_UP)) :
                value1.add(value2.subtract(value1).divide(TWO, HALF_UP));
    }

    public static BigDecimal getDifferenceBetween(BigDecimal value1, BigDecimal value2) {
        int compare = value1.compareTo(value2);
        return compare >= 0 ?
                value1.subtract(value2) :
                value2.subtract(value1);
    }

    public static BigDecimal getNotZeroValue(BigDecimal value) {
        return value.equals(BigDecimal.ZERO) ? BigDecimal.ONE : value;
    }

    private static Integer withoutNullValue(Integer n) {
        return Optional.ofNullable(n).orElse(0);
    }

    private static Float withoutNullValue(Float n) {
        return Optional.ofNullable(n).orElse(00.00f);
    }

    private static Double withoutNullValue(Double n) {
        return Optional.ofNullable(n).orElse(00.00);
    }

    public static BigDecimal withoutNullValue(BigDecimal n) {
        return Optional.ofNullable(n).orElse(BigDecimal.ZERO);
    }

    private static Integer sum(Integer a,
                               Integer b) {
        return a + b;
    }

    private static Float sum(Float a,
                             Float b) {
        return a + b;
    }

    private static Double sum(Double a,
                              Double b) {
        return a + b;
    }

    public static BinaryOperator<Integer> accumulatedSum = (a, b) -> sum(withoutNullValue(a), withoutNullValue(b));

    public static BinaryOperator<BigDecimal> accumulatedDecimalSum = BigDecimal::add;

    public static BinaryOperator<Double> accumulatedDoubleSum = (a, b) -> sum(withoutNullValue(a), withoutNullValue(b));
}
