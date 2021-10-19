package com.cjy.cloud.technology.calculator.supermarket.discount;

/**
 * @author chenjingyu
 * @date 2021/9/21 2:00
 * @depiction 直接折扣
 */
public class DirectDiscount {
    private Double discount;

    DirectDiscount(double discount) {
        this.discount = discount;
    }

    public Double getResult(Double value) {
        return value * discount;
    }
}
