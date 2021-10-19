package com.cjy.cloud.technology.calculator.supermarket.commodity;

/**
 * @author chenjingyu
 * @date 2021/9/20 22:04
 * @depiction  超市- 一般商品
 */
public class OrdinaryCommodity extends AbstractCommodity {

    public OrdinaryCommodity(String name, Double price) {
        setName(name);
        setPrice(price);
    }
}
