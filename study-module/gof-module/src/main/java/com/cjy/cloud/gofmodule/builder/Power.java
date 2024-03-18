package com.cjy.cloud.gofmodule.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Power {

    private String brand;

    private String size;

    public Power(String brand, String size) {
        this.brand = brand;
        this.size = size;
    }
}
