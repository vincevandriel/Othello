package Controller;

import Model.*;
import View.Board;
import java.awt.*;
import java.util.ArrayList;

public class BoardController {

    public static Root root;
    private boolean gameDone;
    private Player P1;
    private Player P2;
    private Board board; //view
    private State state; //model
    private Rules rules; //model
    private ArrayList<Tile> tiles;
    private int[][] board2D;
    private TileCounter tileCounter;

    public void start(Board board) {
        this.board = board;
        P1 = new AlphaBeta(); //black tiles, bot
        P2 = new RandomAI(); //white tiles, bot
        board2D = new int[board.getBlockSize()][board.getBlockSize()];
        state = new State(board.getBlockSize());
        rules = new Rules();
        tileCounter = new TileCounter();
        board2D = rules.setupBoard(board2D);
        state.setCurrentBoard(board2D);
        tiles = state.convertToCollection(board2D);
        board.addTiles(tiles);
        board.addBoardClickEventListener(this::boardClickHandler);
        root = new Root(board2D, 1, 5);
    }

    private void boardClickHandler(Dimension pos) {

        double x = pos.getHeight();
        double y = pos.getWidth();

        state.setX((int)x);
        state.setY((int)y);

        if (!gameDone) {

            if (state.getTile() == 1) {
                P1.makeMove(state);
            } else {
                P2.makeMove(state);
            }
        }

        root.setRoot(state.getCurrentBoard(), state.getTile());
        tiles = state.convertToCollection(board2D);
        board.addTiles(tiles);
        checkGameStatus(); //check for game state
    }

    public void checkGameStatus() {
        if (board.getTiles().size() == 64) {
            gameDone = true;
            int black = tileCounter.eval(board2D, 1);
            int white = tileCounter.eval(board2D, 2);
            rules.result(black, white);
        }
    }
}