package com.cjy.cloud.technology.calculator.supermarket.commodity;

/**
 * @author chenjingyu
 * @date 2021/9/20 21:48
 * @depiction 商品抽象类
 */
public abstract class AbstractCommodity {

    private String name;

    private Double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
}
