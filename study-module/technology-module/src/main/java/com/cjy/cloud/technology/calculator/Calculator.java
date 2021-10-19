package com.cjy.cloud.technology.calculator;

import java.util.Objects;

/**
 * @author chenjingyu
 * @date 2021/9/18 17:56
 * @depiction 计算器类
 */
@Deprecated
public class Calculator {

    /**
     * 如需要增加新的计算方法,需要在switch中加入新的case
     * 增加case时,有误修改其他算式的风险,为了确保功能正常,就需要对所有的算式进行测试
     * 增加了时间成本
     *
     */
    public static double getResult(Double number1, Double number2, Operator operator) {
        if (Objects.isNull(operator)) {
            return 0D;
        }
        double result;
        switch (operator) {
            case ADD:
                result = number1 + number2;
                break;
            case DIVISION:
                result = number1 / number2;
                break;
            default:
                result = 0D;
                break;
        }
        return result;
    }
}
