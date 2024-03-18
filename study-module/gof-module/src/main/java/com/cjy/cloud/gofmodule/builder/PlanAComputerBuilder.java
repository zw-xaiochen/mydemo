package com.cjy.cloud.gofmodule.builder;

public class PlanAComputerBuilder implements ComputerBuilder{
    private MainBoard mainBoard;

    private Power power;

    private Mouse mouse;

    @Override
    public ComputerBuilder builderMainBoard(String brand) {
        this.mainBoard = new MainBoard(brand, "2023-08-28");
        return this;
    }

    @Override
    public ComputerBuilder builderPower(String brand) {
        this.power = new Power(brand, "5V");
        return this;
    }

    @Override
    public ComputerBuilder builderMouse(String brand) {
        this.mouse = new Mouse("brand", "通用型号");
        return this;
    }

    @Override
    public NewComputer builder() {
        return new NewComputer(mainBoard, power, mouse);
    }
}
