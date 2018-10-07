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

    /*public void placeTile(int x, int y, int tile){
        board2D = convertBoard2D(board);
        board2D = rules.checkMoves(board2D, 1, x, y);
        if(board2D == null){
            System.out.println("illegal move, try again");
        }else{
            board[size*y + x] = tile;
            board2D = rules.flip(board2D, x, y, 1);
            convertBoard1D(board2D);
            numTiles++;
        }
    }*/

    public int getTileIdAt(int x, int y){
        return board[size*y + x];
    }

    public ArrayList<Tile> listOccupiedTiles(){
        ArrayList<Tile> tiles = new ArrayList<>();
        for(int x = size; x-- > 0;){
            for(int y = size; y-- > 0;){
                int tileId = getTileIdAt(x,y);
                if(tileId > 0){
                    tiles.add(new Tile(new Dimension(x, y), tileId));
                }
            }
        }
        return tiles;
    }

    public int[][] convertBoard2D(int[] board){ //convert to 2D array
        int[][] board2D = new int[size][size];
        int counter = 0;
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                board2D[i][j] = board[counter];
                counter++;
            }
        }
        return board2D;
    }

    public void convertBoard1D(int[][] board2D){ //convert back to 1D array
        int counter = 0;
        for(int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                board[counter] = board2D[i][j];
                counter++;
            }
        }
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
