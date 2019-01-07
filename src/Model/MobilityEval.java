package Model;

public class MobilityEval implements EvalFunction{
    private static Rules rules;

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
                    amount++;
                }
            }
        }
        rules.clear3s(temp);
        return amount;
    }

    public int getMax(int[][] board) {
        return board.length*board.length;
    }
}
