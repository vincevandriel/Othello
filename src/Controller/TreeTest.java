package Controller;

import Model.Rules;
import Controller.Root;

import java.util.ArrayList;

public class TreeTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Rules rules = new Rules();
        int[][] board = new int[8][8];
        board = rules.setupBoard(board);
        Root root = new Root(board, 1);

        root.print();
    }

    public static void test2() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0, 1);
        list.add(0, 2);
        System.out.println(list.get(0));
        list.remove(0);
        System.out.println(list.get(0));
     }
}
