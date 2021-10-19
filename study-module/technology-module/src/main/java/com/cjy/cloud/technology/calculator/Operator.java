package com.cjy.cloud.technology.calculator;

/**
 * @author chenjingyu
 * @date 2021/9/18 17:59
 * @depiction 运算符号枚举
 */
public enum Operator {
    ADD("加法", "+"),

    DIVISION("除法", "/")
   ;

   private String op_name;

   private String op_value;

    Operator(String op_name, String op_value) {
        this.op_name = op_name;
        this.op_value = op_value;
    }

    public String getOp_name() {
        return op_name;
    }

    public String getOp_value() {
        return op_value;
    }

    public static Operator getOperator(String op_value) {
        for (Operator operator : Operator.values()) {
            if (operator.getOp_value().equals(op_value)) {
                return operator;
            }
        }
        return null;
    }
}
