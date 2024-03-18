package com.cjy.cloud.gofmodule.builder;

public interface ComputerBuilder {

    ComputerBuilder builderMainBoard(String brand);

    ComputerBuilder builderPower(String brand);

    ComputerBuilder builderMouse(String brand);

    NewComputer builder();

}
