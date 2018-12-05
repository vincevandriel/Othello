package Model;
import java.util.ArrayList;
import java.util.Random;

public class RandomAI implements Player {

    private Random y;
    private int x_coor;
    private int y_coor;
    private Rules rules;
    private int tile;

    public RandomAI(){
        rules = new Rules();
        y = new Random();
    }
    public int[][] pickMove(int[][] board2D, int tile){
        ArrayList<int[]> list = findMoves(board2D);
        int random;
        if(list == null){
            return null;
        }else {
            random = y.nextInt(list.size()); //number from 1 to # legal moves
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

    @Override
    public void makeMove(State currentState) {
        this.tile = currentState.getTile();
        int[][] board2D = currentState.getCurrentBoard(); //get current state
        board2D = rules.checkMoves(board2D, tile);
        board2D = pickMove(board2D, tile);
        if (board2D == null) {
            System.out.println("NO MOVES AVAILABLE FOR AI - switch turn to opponent.");
        } else {
            board2D = rules.clear3s(board2D); //clear possible legal spots (marked as 3s)
            board2D = rules.flip(board2D, getX(), getY(), tile);
            currentState.setCurrentBoard(board2D); //update state after move
        }
        currentState.switchTile();
    }

    public int getX(){
        return x_coor;
    }

    public int getY(){
        return y_coor;
    }
}
