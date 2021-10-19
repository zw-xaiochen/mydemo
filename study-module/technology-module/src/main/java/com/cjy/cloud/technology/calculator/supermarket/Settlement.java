package com.cjy.cloud.technology.calculator.supermarket;

import com.cjy.cloud.technology.calculator.supermarket.commodity.AbstractCommodity;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author chenjingyu
 * @date 2021/9/20 22:34
 * @depiction 超市-商品结算
 */
public class Settlement {

    private Double countValue;

    /**
     * 传入购物车类,开始进行商品价格结算
     */
    Settlement(ShoppingCart shoppingCart) {

    }

    private Double getCountValue(ShoppingCart shoppingCart) {
        List<AbstractCommodity> commodities = shoppingCart.getCommodities();
        if (CollectionUtils.isEmpty(commodities)) {
            return 0.0;
        }
        return commodities.stream().mapToDouble(AbstractCommodity::getPrice).sum();
    }
}
