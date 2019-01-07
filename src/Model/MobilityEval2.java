package Model;

public class MobilityEval2 implements EvalFunction{
    private static Rules rules;

    //This is how much is added to the score for our own tiles.
    final int thisTileValue = 1;
    //This is how much is subtracted from the score for the tiles of the opponent.
    final int opponentTileValue = 1;

    @Override
    public int eval(int[][] board, int tile) {
        if(rules == null) {
            rules = new Rules();
        }
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

    public int swaptile(int tile) {
        if(tile == 1){
            return 2;
        } else {
            return 1;
        }
    }
}
