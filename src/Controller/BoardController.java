package Controller;

import Model.State;
import Model.Tile;
import View.Board;

import java.awt.*;
import java.util.ArrayList;

public class BoardController{
    private Board board;
    private State state;
    private Rules rules;
    private ArrayList<Tile> tiles;
    private int[][] board2D;
    public void start(Board board){
        this.board = board;
        board2D = new int[board.getBlockSize()][board.getBlockSize()];
        state = new State(board.getBlockSize());
        rules = new Rules();
        board2D = rules.setupBoard(board2D);
        tiles = state.convertToCollection(board2D);
        board.addTiles(tiles);
        board.addBoardClickEventListener(this::boardClickHandler);
    }
    int tile = 1; //black starts first
    private void boardClickHandler(Dimension pos){
        rules.pront(board2D);
        double x = pos.getHeight();
        double y = pos.getWidth();
        int[][] tempBoard2D = rules.checkMoves(board2D, tile, x, y);
        if(tempBoard2D != null){
            board2D = tempBoard2D;
            board2D = rules.clear3s(board2D); //clear possible legal spots (marked as 3s)
            if(tile == 0){ //change tile on 2D board before flipping tiles
                board2D[(int)x][(int)y] = 2;
            }else{
                board2D[(int)x][(int)y] = 1;
            }
            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles); //add the new move and repaint
            board2D = rules.flip(board2D, x, y, tile);
            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles); //add flipped tiles and repaint
            if(tile == 1){
                tile = 0;
            }else{
                tile = 1;
            }
        }else{
            System.out.println("Illegal move, try again.");
        }
    }
}