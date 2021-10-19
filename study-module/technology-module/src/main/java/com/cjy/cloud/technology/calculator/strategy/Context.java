package com.cjy.cloud.technology.calculator.strategy;

import com.cjy.cloud.technology.calculator.Operator;
import com.cjy.cloud.technology.calculator.newidea.AddCalculator;
import com.cjy.cloud.technology.calculator.newidea.NewCalculator;

/**
 * @author chenjingyu
 * @date 2021/9/20 17:05
 * @depiction 策略模式-上下文
 */
public class Context {
    private NewCalculator calculator;


    public Context(Operator operator) {
        switch (operator) {
            case ADD:
                calculator = new AddCalculator();
                break;
            default:
                calculator = null;
        }
    }

    public double getResult() {
        return calculator.getResult();
    }



}
