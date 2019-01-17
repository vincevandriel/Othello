package Model;

public class PhaseEval implements EvalFunction{

    int phase = -1;
    EvalFunction[] evalfunctions;
    double evalWeight;

    public PhaseEval() {
        evalfunctions = new EvalFunction[]{new MobilityEval(), new MobilityEval(), new TileCounter()};
        evalWeight = 1;
    }

    public PhaseEval(EvalFunction[] evalFunctions) {
        this.evalfunctions = evalFunctions;
    }

    @Override
    public int eval(int[][] board, int tile) {
        determinePhase(board);
        return evalfunctions[phase].eval(board, tile);
    }

    @Override
    public double getEvalWeight() {
        if(phase == -1) {
            System.out.println("This evaluation function has multiple weights: For each phase one. It seems like these phases have not been initialized yet. To initialize, evaluate once.");
            return Double.MIN_VALUE;
        } else {
            return evalfunctions[phase].getEvalWeight();
        }
    }

    //This method determines the current phase depending on the amount of tiles are already placed.
    //THIS MUST RETURN AN INT!!! We have a phase 0 instead of 1 :)
    public void determinePhase(int[][] board){

        //If phase == eval.functions.length we are at the last phase (we start counting at 0). Don't go higher than this! Else we just leave the phase like it is
        if(phase != evalfunctions.length -1) {
            //How long a phase lasts. 4 are subtracted for the 4 first tiles.
            int phaselength = (board.length * board[0].length)/evalfunctions.length - 4;
            int tiles = 0;

            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j] == 1 || board[i][j] == 2) {
                        tiles++;
                    }
                }
            }
            phase = tiles/phaselength;
        }
    }

}