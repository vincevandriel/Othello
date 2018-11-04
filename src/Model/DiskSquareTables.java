package Model;

public class DiskSquareTables implements EvalFunction{
    int[][] dst;

    public DiskSquareTables(int size) {
        dst = generate(size);
        pront(dst);
    }

    public static void main(String[] args) {
        DiskSquareTables dst = new DiskSquareTables(10);
    }

    public int[][] generate(int size){
        int[][] result = new int[size][size];

        for(int i = 2; i < size-2; i++) {
            for(int j = 2; j < size-2; j++) {
                result[i][j] = 1;
            }
        }
        return result;
    }

    @Override
    public int eval(int[][] board, int colour) {
        return 0;
    }
    //NOT DONE

    public void pront(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
