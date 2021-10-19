package com.cjy.cloud.technology.calculator.newidea;

/**
 * @author chenjingyu
 * @date 2021/9/20 0:07
 * @depiction 加法计算器
 */
public class AddCalculator extends NewCalculator {

    @Override
    public double getResult() {
        return getNumber1() + getNumber2();
    }
}
