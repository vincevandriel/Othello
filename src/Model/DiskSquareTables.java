package Model;

public class DiskSquareTables implements EvalFunction{
    private int[][] dst;
    private int max;
    private int[][] board;
    private final double  evalWeight = 0.01;

    @Override
    public int eval(int[][] board, int currentColour) {
        generateDST(board);

        int result = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == currentColour) {
                    result += dst[i][j];
                }
            }
        }
        return result;
    }

    public void generateDST(int[][] board) {
        //If Dst not initialized or kept with wrong size, then make board with right size
        if(dst == null || dst.length == board.length) {
            dst = generateDst(board.length);
        }
    }

    //Based on: https://goo.gl/wCBa4K
    //Size must be even and 4 or larger!
    public int[][] generateDst(int size) {
        int[][] result = new int[size][size];

        //Values according to this picture given above
        int corner = 5;
        int x = -2;
        int c = -1;
        int middle = 2;
        int other = 1;
        int a = 1;
        int b = 1;

        //Value for all squares that don't have a special position
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                result[i][j] = other;
            }
        }

        //Corner Values
        result[0][0] = corner;
        result[result.length - 1][0] = corner;
        result[0][result.length - 1] = corner;
        result[result.length - 1][result.length - 1] = corner;

        //Middle (only works out if size is 8 or larger)
        if(size >= 8) {
            for(int i = 2; i < size - 2; i++) {
                for(int j = 2; j < size - 2; j++) {
                    result[i][j] = middle;
                }
            }
        }

        //A
        result[0][2] = a;
        result[0][size - 3] = a;
        result[2][0] = a;
        result[size - 3][0] = a;
        result[size - 1][2] = a;
        result[size - 1][size - 3] = a;
        result[2][size - 1] = a;
        result[size - 3][size - 1] = a;

        //B (only works if 8 or larger)
        if(size >= 8) {
            for(int i = 3; i < size - 3; i++) {
                result[0][i] = b;
            }
            for(int i = 3; i < size - 3; i++) {
                result[i][0] = b;
            }
            for(int i = 3; i < size - 3; i++) {
                result[size - 1][i] = b;
            }
            for(int i = 3; i < size - 3; i++) {
                result[i][size - 1] = b;
            }
        }

        //C
        result[0][1] = c;
        result[1][0] = c;
        result[0][size - 2] = c;
        result[size - 2][0] = c;
        result[1][size - 1] = c;
        result[size - 1][1] = c;
        result[size - 1][size - 2] = c;
        result[size - 2][size - 1] = c;

        //X
        result[1][1] = x;
        result[1][size - 2] = x;
        result[size - 2][1] = x;
        result[size - 2][size - 2] = x;


        return result;
    }

    public int getMax(int[][] board) {
        generateDST(board);
        int max = 0;
        for(int i = 0; i < dst.length; i++) {
            for(int j = 0; j < dst[i].length; j++) {
                max += dst[i][j];
            }
        }
        return max;
    }

    @Override
    public double getEvalWeight(){
        return evalWeight;
    }
}