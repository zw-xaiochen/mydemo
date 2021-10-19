package com.cjy.cloud.technology.calculator.supermarket.discount;

/**
 * @author chenjingyu
 * @date 2021/9/21 1:45
 * @depiction 满减折扣
 */
public class ThresholdSubDiscount {
    private int threshold;

    private int subValue;

    public ThresholdSubDiscount(int threshold, int subValue) {
        this.threshold = threshold;
        this.subValue = subValue;
    }

    public Double getResult(Double value) {
        int number = (int)(value / threshold);
        return value - number * subValue;
    }
}
