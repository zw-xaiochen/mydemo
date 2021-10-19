package com.cjy.cloud.technology.calculator.newidea;

import com.cjy.cloud.technology.calculator.Operator;

/**
 * @author chenjingyu
 * @date 2021/9/20 16:34
 * @depiction 计算器工厂
 */
public class CalculatorFactory {

    public static NewCalculator getCal(Operator operator) {
        NewCalculator calculator;
        switch (operator) {
            case ADD:
                calculator = new AddCalculator();
                break;
            default:
                calculator = null;
        }
        return calculator;
    }
}
