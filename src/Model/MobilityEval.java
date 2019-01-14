package Model;

//This evaluation function calculates the score based on amount of moves that can be done for both players
public class MobilityEval implements EvalFunction{

    private static Rules rules;
    private double evalWeight;

    //This is how much is added to the score for the moves of the player
    private int thisPlayerValue;
    //This is how much is subtracted from the score for the moves of the opponent
    private int opponentPlayerValue;

    public MobilityEval() {
        this.thisPlayerValue = 1;
        this.opponentPlayerValue = 0;
        rules = new Rules();
        this.evalWeight = 0.01;
    }

    //custom evalWeight
    public MobilityEval(double evalWeight) {
        this.thisPlayerValue = 1;
        this.opponentPlayerValue = 0;
        rules = new Rules();
        this.evalWeight = evalWeight;
    }

    //custom tileValues
    public MobilityEval(int thisTileValue, int opponentTileValue) {
        this.thisPlayerValue = thisTileValue;
        this.opponentPlayerValue = opponentTileValue;
        rules = new Rules();
        this.evalWeight = 0.01;
    }

    //custom tileValues AND evalWeight
    public MobilityEval(int thisTileValue, int opponentTileValue, double evalWeight) {
        this.thisPlayerValue = thisTileValue;
        this.opponentPlayerValue = opponentTileValue;
        rules = new Rules();
        this.evalWeight = evalWeight;
    }

    @Override
    public int eval(int[][] board, int tile) {
        int score = 0;
        if(thisPlayerValue != 0) {
            score += thisPlayerValue*checkMovesAmount(board, tile);
        }
        if(opponentPlayerValue != 0) {
            score -= opponentPlayerValue*checkMovesAmount(board, swaptile(tile));
        }
        return score;
    }

    @Override
    public double getEvalWeight(){
        return evalWeight;
    }

    public int swaptile(int tile) {
        if(tile == 1){
            return 2;
        } else {
            return 1;
        }
    }

    //Returns the amount of moves for the given player/tile
    public int checkMovesAmount(int [][] board, int tile) {
        //Make a new, temporary board to see the amount of moves
        int[][] temp = rules.checkMoves(board, tile);
        int amount = 0;

        //check the amount of moves available for this player
        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[0].length; j++) {
                if(temp[i][j] == 3) {
                    amount++;
                }
            }
        }

        //clear 3's and return
        rules.clear3s(temp);
        return amount;
    }
}
