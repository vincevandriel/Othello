package Model;

import java.awt.*;
import java.util.ArrayList;

public class State {
    private int[] board;
    private int numTiles;
    private int size;
    public State(int size){
        board = new int[size * size];
        numTiles = 0;
        this.size = size;
    }

    public void placeTile(int x, int y, int tile){
        board[size*y + x] = tile;
        numTiles++;

    }

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

}
