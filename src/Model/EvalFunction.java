package Model;

public interface EvalFunction {
    public int eval(int[][] board, int tile);
    public double getEvalWeight();
}
