package Controller;
import java.util.ArrayList;
import java.util.Random;

public class SimpleAI {

    private Random y;
    private int x_coor;
    private int y_coor;

    public SimpleAI(){
        y = new Random();
    }
    public int[][] pickMove(int[][] board, int tile){
        if(tile == 0){
            tile = 2;
        }
        ArrayList<int[]> list = findMoves(board);
        int random;
        if(list == null){
            return null;
        }else {
            if(list.size() == 1){
                random = 0;
            }else {
                random = y.nextInt(list.size() - 1); //number from 1 to # legal moves
            }
            this.x_coor = list.get(random)[0];
            this.y_coor = list.get(random)[1];
            board[x_coor][y_coor] = tile;
            return board;
        }
    }

    public ArrayList<int[]> findMoves(int[][] board){
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 3){
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
