package Model;

public class TileCounter2 implements EvalFunction{

    //This is how much is added to the score for our own tiles.
    final int thisTileValue = 2;
    //This is how much is subtracted from the score for the tiles of the opponent.
    final int opponentTileValue = 1;

    @Override
    public int eval(int[][] board, int colour) {
        int score = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == colour) {
                    score += thisTileValue;
                } else if(board[i][j] == swaptile(colour)) {
                    score -= opponentTileValue;
                }
            }
        }

        return score;
    }

    public int getMax(int[][] board) {
        return board.length*board.length*thisTileValue;
    }

    public int swaptile(int tile) {
        if(tile == 1){
            return 2;
        } else {
            return 1;
        }
    }
}
