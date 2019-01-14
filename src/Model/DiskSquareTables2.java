package Model;

public class DiskSquareTables2 extends DiskSquareTables implements EvalFunction{
    //Disk-Square Tables which takes the enemy tiles into account
    private int[][] dst;
    private double evalWeight;

    public DiskSquareTables2() {
        this.evalWeight = 0.06;
    }

    public DiskSquareTables2(int evalWeight) {
        this.evalWeight = evalWeight;
    }

    @Override
    public int eval(int[][] board, int currentColour) {
        //If Dst not initialized or kept with wrong size, then make board with right size
        if(dst == null || dst.length == board.length) {
            dst = super.generateDst(board.length);
        }

        int result = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == currentColour) {
                    result += dst[i][j];
                } else if(board[i][j] == swapTile(currentColour)){
                    result -=dst[i][j];
                }
            }
        }
        return result;
    }

    public int swapTile(int tile) {
        if(tile == 1){
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public double getEvalWeight(){
        return evalWeight;
    }
}
