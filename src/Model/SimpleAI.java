package Model;
import java.util.ArrayList;
import java.util.Random;
import Model.State;

public class SimpleAI {
    private Rules rules = new Rules();
    private Random y;
    private int x_coor;
    private int y_coor;

    public SimpleAI(){
        y = new Random();
    }
    public int[][] pickMove(int[][] board2D, int tile){
        ArrayList<int[]> list = findMoves(board2D);
        int random;
        if(list == null){
            return null;
        }
        if(list.size() == 1){
            random = 0;
        }else {
            random = y.nextInt(list.size() - 1); //number from 1 to # legal moves
        }
        this.x_coor = list.get(random)[0];
        this.y_coor = list.get(random)[1];
        board2D[x_coor][y_coor] = tile;
        return board2D;
    }

    public ArrayList<int[]> findMoves(int[][] board2D){
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 0; i < board2D.length; i++) {
            for (int j = 0; j < board2D[0].length; j++) {
                if(board2D[i][j] == 3){
                    int[] position = new int[2];
                    position[0] = i; //x
                    position[1] = j; //y
                    moves.add(position);
                }
            }
        }
        if(moves.size() == 0){
            return null;
        }
        return moves;
    }

    public int getX(){
        return x_coor;
    }

    public int getY(){
        return y_coor;
    }
}
