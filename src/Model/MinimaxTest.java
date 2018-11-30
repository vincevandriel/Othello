package Model;


public class MinimaxTest {

    public static void main(String[] args){
        test2();
    }

    public static void test1() {
        Rules rules = new Rules();
        int[][] board = new int[8][8];
        board = rules.setupBoard(board);
        Root root = new Root(board, 1, 5);
        Minimax minimax = new Minimax(root);
    }

    public static void test2() {
        Rules rules = new Rules();
        int[][] board = new int[8][8];
        board = rules.setupBoard(board);
        Root root = new Root(board, 1, 5);
        AlphaBeta alphabeta = new AlphaBeta();
    }

}
