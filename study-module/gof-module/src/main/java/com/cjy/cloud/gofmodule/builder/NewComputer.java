package com.cjy.cloud.gofmodule.builder;

public class NewComputer {

    private MainBoard mainBoard;

    private Power power;

    private Mouse mouse;


    public NewComputer(MainBoard mainBoard, Power power, Mouse mouse) {
        this.mainBoard = mainBoard;
        this.power = power;
        this.mouse = mouse;
    }

    public MainBoard getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(MainBoard mainBoard) {
        this.mainBoard = mainBoard;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
}
