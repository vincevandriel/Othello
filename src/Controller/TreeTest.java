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

        int[] array= new int[2];
        array[1] = 1;
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
}
