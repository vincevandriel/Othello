package Controller;

import Model.State;
import java.util.ArrayList;

public class Tree {
    private int depth;

    public void Tree(State state){
        Rules rules = new Rules();
        depth = 5;
    }

    public int[][] copyBoard(int[][] board){
        return board;
    }
}
