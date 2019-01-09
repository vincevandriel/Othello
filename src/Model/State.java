package Model;
import java.awt.*;
import java.util.ArrayList;

public class State { //part of the model

    private int[][] board2D;
    private int tile;
    private int x;
    private int y;

    public State(int size){
        tile = 1;
        board2D = new int[size][size];
    }


    public int[][] getCurrentBoard(){
        return board2D;
    }

    public void setCurrentBoard(int[][] board2D){
        this.board2D = board2D;
    }

    /*
    public void print(){
        for(int i = 0; i < board2D.length; i++){
            for(int j = 0; j<board2D[0].length; j++){
                System.out.print(board2D[i][j]);
            }
            System.out.println();
        }
    }
    */

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

    public int switchTile() {
        if (tile == 1) {
            tile = 2;
        } else {
            tile = 1;
        }
        return tile;
    }

    public int getTile(){
        return tile;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
