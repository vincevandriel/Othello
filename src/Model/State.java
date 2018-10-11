package Model;
import Controller.Rules;
import java.awt.*;
import java.util.ArrayList;

public class State {
    private int[] board;
    private int numTiles;
    private int size;
    private Rules rules;
    private int[][] board2D;
    public State(int size){
        rules = new Rules();
        board = new int[size * size];
        numTiles = 0;
        this.size = size;
    }

    public int[] scanBoard() {
        int[] result = new int[2];
        for(int i = 0; i < board2D.length; i++) {
            for(int j = 0; j < board2D[0].length; j++) {
                if(board2D[i][j] == 1) {
                    result[0] += 1;
                } else if(board2D[i][j] == 2){
                    result[1] += 1;
                }
            }
        }
        return result;
    }

    public ArrayList<Tile> convertToCollection(int[][] board){
        ArrayList<Tile> result = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != 0 || board[i][j] != 3) {
                    if(board[i][j] == 2){
                        result.add(new Tile(new Dimension(j, i), board[i][j]-2));
                    }else if(board[i][j] == 1){
                        result.add(new Tile(new Dimension(j, i), board[i][j]));
                    }
                }
            }
        }
        return result;
    }
}
