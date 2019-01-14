package Model;

//This evaluation function calculates the score based on the amount of tiles for both players
public class TileCounter implements EvalFunction{

    private double evalWeight;

    //This is how much is added to the score for our own tiles.
    private int thisPlayerValue;
    //This is how much is subtracted from the score for the tiles of the opponent.
    private int opponentPlayerValue;

    public TileCounter() {
        this.thisPlayerValue = 1;
        this.opponentPlayerValue = 0;
        this.evalWeight = 0.01;
    }

    //custom evalWeight
    public TileCounter(double evalWeight) {
        this.thisPlayerValue = 1;
        this.opponentPlayerValue = 0;
        this.evalWeight = evalWeight;
    }

    //custom tileValues
    public TileCounter(int thisTileValue, int opponentTileValue) {
        this.thisPlayerValue = thisTileValue;
        this.opponentPlayerValue = opponentTileValue;
        this.evalWeight = 0.01;
    }

    //custom tileValues and evalWeight
    public TileCounter(int thisTileValue, int opponentTileValue, double evalWeight) {
        this.thisPlayerValue = thisTileValue;
        this.opponentPlayerValue = opponentTileValue;
        this.evalWeight = evalWeight;
    }

    @Override
    public int eval(int[][] board, int tile) {
        int score = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(thisPlayerValue != 0 && board[i][j] == tile) {
                    score += thisPlayerValue;
                } else if(opponentPlayerValue != 0 && board[i][j] == getOpponentColour(tile)) {
                    score -= opponentPlayerValue;
                }
            }
        }
        return score;
    }

    @Override
    public double getEvalWeight(){
        return evalWeight;
    }

    public int getOpponentColour(int tile) {
        if(tile == 1){
            return 2;
        } else {
            return 1;
        }
    }
}
