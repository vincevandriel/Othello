package Controller;
import Model.Rules;


public class MinimaxTest {

    public static void main(String[] args){
        test2();
    }

    public static void test1() {
        Rules rules = new Rules();
        int[][] board = new int[8][8];
        board = rules.setupBoard(board);
        Root root = new Root(board, 1);
        root.generateTree();
        Minimax minimax = new Minimax(root);
    }

    public static void test2() {
        Rules rules = new Rules();
        int[][] board = new int[8][8];
        board = rules.setupBoard(board);
        Root root = new Root(board, 1);
        AlphaBeta alphabeta = new AlphaBeta(root);
    }

}
