package com.cjy.cloud.technology.calculator.newidea;

import com.cjy.cloud.technology.calculator.Operator;

/**
 * @author chenjingyu
 * @date 2021/9/20 14:40
 * @depiction 类功能简述
 */
public class NewTest {

    public static void main(String[] args) {

        NewCalculator calculator = CalculatorFactory.getCal(Operator.ADD);
        calculator.setNumber1(1);
        calculator.setNumber2(2);
        System.out.println(calculator.getResult());
    }
}
