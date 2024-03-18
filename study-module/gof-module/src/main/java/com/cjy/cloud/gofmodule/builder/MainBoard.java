package com.cjy.cloud.gofmodule.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainBoard {
    private String brand;

    private String createDate;

    public MainBoard(String brand, String createDate) {
        this.brand = brand;
        this.createDate = createDate;
    }
}
