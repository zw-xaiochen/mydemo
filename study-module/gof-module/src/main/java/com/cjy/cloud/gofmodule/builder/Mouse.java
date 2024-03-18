package com.cjy.cloud.gofmodule.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mouse {
    private String brand;

    private String type;

    public Mouse(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }
}
