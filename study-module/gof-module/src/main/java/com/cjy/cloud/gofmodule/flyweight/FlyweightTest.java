package com.cjy.cloud.gofmodule.flyweight;

public class FlyweightTest {

    public static void main(String[] args) {
        ChessFactory factory = ChessFactory.getInstance();
        AbstractChess white = factory.getChess("白");
        System.out.println(white.getShape());
        AbstractChess block = factory.getChess("黑");
        System.out.println(block.getShape());
        System.out.println("当前棋子数量:" + factory.getChessNum());
    }

}
