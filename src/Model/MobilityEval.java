package Model;

public class MobilityEval implements EvalFunction{

    //This evaluation function calculates the score based on amount of moves that can be done later on in the game

    private static Rules rules;
    private final double evalWeight = 0.01;

    public MobilityEval() {
        rules = new Rules();
    }

    @Override
    public int eval(int[][] board, int tile) {
        int amount = 0;
        int[][] temp = rules.checkMoves(board, tile);
        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[0].length; j++) {
                if(temp[i][j] == 3) {
                    amount++;
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
}
