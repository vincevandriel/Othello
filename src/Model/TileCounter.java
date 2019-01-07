package Model;

public class TileCounter implements EvalFunction{

    @Override
    public int eval(int[][] board, int colour) {
        int score = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == colour) {
                    score++;
                }
            }
        }

        return score;
    }
    public int getMax(int[][] board) {
        return board.length*board.length;
    }
}
