package com.cjy.cloud.gofmodule.builder;

public class Computer {
    private String mainBoard; // 主板

    private String power; // 电源

    private String mouse; // 鼠标

    private String keyboard; // 键盘

    private String display; // 显示器

    private Computer(Builder builder) {
        this.mainBoard = builder.mainBoard;
        this.power = builder.power;
        this.mouse = builder.mouse;
        this.keyboard = builder.keyboard;
        this.display = builder.display;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "mainBoard='" + mainBoard + '\'' +
                ", power='" + power + '\'' +
                ", mouse='" + mouse + '\'' +
                ", keyboard='" + keyboard + '\'' +
                ", display='" + display + '\'' +
                '}';
    }


    public static class Builder {

        private String mainBoard; // 主板

        private String power; // 电源

        private String mouse; // 鼠标

        private String keyboard; // 键盘

        private String display; // 显示器

        public Builder(String mainBoard, String power){
            this.mainBoard = mainBoard;
            this.power = power;
        }

        public Builder setMouse(String mouse) {
            this.mouse = mouse;
            return this;
        }

        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }

        public Computer builder() {
            return new Computer(this);
        }
    }
}
