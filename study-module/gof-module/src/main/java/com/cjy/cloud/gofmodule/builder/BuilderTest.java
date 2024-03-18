package com.cjy.cloud.gofmodule.builder;

public class BuilderTest {

    public static void main(String[] args) {
        /*Computer computer = new Computer("华硕", "宏碁");
        computer.setDisplay("hp");
        System.out.println(computer);*/
        /*Computer computer = new Computer.Builder("华硕", "宏碁").setMouse("MI").setKeyboard("hp").builder();
        System.out.println(computer);*/
        NewComputer newComputer =
                new PlanAComputerBuilder().builderMainBoard("华硕").builderPower("宏碁").builderMouse("MI").builder();
    }
}
