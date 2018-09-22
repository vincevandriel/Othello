package Controller;

import Model.State;
import Model.Tile;
import View.Board;

import java.awt.*;

public class BoardController{
    private Board board;
    private State state;
    public void start(Board board){
        this.board = board;
        state = new State(board.getBlockSize());
        board.addBoardClickEventListener(this::boardClickHandler);
    }
    int tile = 0;
    private void boardClickHandler(Dimension pos){
        board.addTile(new Tile(pos, tile));
        tile = (tile + 1) %2;
        board.repaint();
    }
}