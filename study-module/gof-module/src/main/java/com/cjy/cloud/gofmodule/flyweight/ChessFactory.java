package com.cjy.cloud.gofmodule.flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessFactory {

    private static ChessFactory chessFactory = new ChessFactory();

    private Map<String, AbstractChess> chessCache = new HashMap<>();

    public static ChessFactory getInstance() {
        /*if (chessFactory == null) {
            chessFactory = new ChessFactory();
        }
        return chessFactory;*/
        return chessFactory;
    }


    public AbstractChess getChess(String color) {
        if (Objects.equals(color, "白")) {
            return chessCache.computeIfAbsent(color, o -> new WhiteChess());
        } else {
            return chessCache.computeIfAbsent(color, o -> new BlockChess());
        }
    }


    public int getChessNum() {
        return chessCache.size();
    }

}
