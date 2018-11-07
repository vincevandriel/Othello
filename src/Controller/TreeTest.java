package Controller;

import Model.Rules;
import Controller.Root;

public class TreeTest {
    public static void main(String[] args) {
        Rules rules = new Rules();
        int[][] board = new int[8][8];
        board = rules.setupBoard(board);
        Root root = new Root(board, 1);

        root.print();
    }
}
