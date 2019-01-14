package Model;

public class MobilityEval2 implements EvalFunction{
    private static Rules rules;
    private double evalWeight;

    //This evaluation function calculates the score based on amount of moves that can be done later on in the game WHILE ALSO taking the enemy moves into account
    //This is how much is added to the score for our own tiles.
    final int thisTileValue = 1;
    //This is how much is subtracted from the score for the tiles of the opponent.
    final int opponentTileValue = 1;

    public MobilityEval2() {
        rules = new Rules();
        this.evalWeight = 0.01;
    }

    public MobilityEval2(int evalWeight) {
        rules = new Rules();
        this.evalWeight = evalWeight;
    }

    @Override
    public int eval(int[][] board, int tile) {
        int amount = 0;
        int[][] temp = rules.checkMoves(board, tile);
        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[0].length; j++) {
                if(temp[i][j] == 3) {
                    amount += thisTileValue;
                }
            }
        }
        rules.clear3s(temp);

        temp = rules.checkMoves(board, swaptile(tile));
        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[0].length; j++) {
                if(temp[i][j] == 3) {
                    amount -= opponentTileValue;
                }
            }
        }
        rules.clear3s(temp);
        return amount;
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
}
