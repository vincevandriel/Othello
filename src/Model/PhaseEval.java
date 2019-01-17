package Model;

public class PhaseEval implements EvalFunction{

    //This holds the current phase and is initialized at -1. However, this value should normally never reached since there are 0 to n-1 phases (where n is the amount of the evalFunctions given in the constructor)
    int phase = -1;
    //This is the evaluationfunctions to be used in each phase. This means, the amount of phases is equal to the amount of evalFunctions! However, most people divide the game into 3 phases.
    EvalFunction[] evalfunctions;
    double evalWeight;

    //No custom evalFunctions
    public PhaseEval() {
        MobilityEval mobilityEval = new MobilityEval();
        TileCounter tileCounter = new TileCounter(2, 1);
        evalfunctions = new EvalFunction[]{mobilityEval, mobilityEval, tileCounter};
        evalWeight = 1;
    }

    //custom evalFunctions
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