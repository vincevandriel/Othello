package Controller;

import Model.Rules;
import Model.SimpleAI;
import Model.State;
import Model.Tile;
import Model.Player;
import View.Board;
import javax.swing.Timer;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BoardController{

    private boolean gameDone;
    private Player P1;
    private Player P2;
    private Board board; //view
    private State state; //model
    private Rules rules; //model
    private ArrayList<Tile> tiles;
    private int[][] board2D;

    public void start(Board board, Player P1, Player P2){
        this.board = board;
        this.P1 = P1; //black tiles, bot
        this.P2 = P2; //white tiles, bot
        board2D = new int[board.getBlockSize()][board.getBlockSize()];
        state = new State(board.getBlockSize());
        rules = new Rules();
        board2D = rules.setupBoard(board2D);
        state.setCurrentBoard(board2D);
        tiles = state.convertToCollection(board2D);
        board.addTiles(tiles);
        board.addBoardClickEventListener(this::boardClickHandler);
    }

    private void boardClickHandler(Dimension pos) {

        double x = pos.getHeight();
        double y = pos.getWidth();

        if(!gameDone) {

            if (P1.isBot() && P2.isBot()) {
                System.out.println(P1.turnStatus());
                if (P1.turnStatus()) {
                    P1.makeMove(state);
                } else {
                    P2.makeMove(state);
                }
            }

            if (!P1.isBot() && P2.isBot()) {
                if (P1.turnStatus()) {
                    P1.makeMove(x, y, state);
                } else {
                    P2.makeMove(state);
                }
            }

            if (P1.isBot() && !P2.isBot()) {
                if (P1.turnStatus()) {
                    P1.makeMove(state);
                } else {
                    P2.makeMove(x, y, state);
                }
            }

            if (!P1.isBot() && !P2.isBot()) {
                if (P1.turnStatus()) {
                    P1.makeMove(x, y, state);
                } else {
                    P2.makeMove(x, y, state);
                }
            }

            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles);
            checkGameStatus(); //check for game state
        }
    }

    public void checkGameStatus(){
        if (board.getTiles().size() == 64) {
            gameDone = true;
            System.out.println("GAME FINISHED");
            rules.countTiles(board2D);
        }
    }
}