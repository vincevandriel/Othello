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

    //private boolean player1Turn;
    private boolean gameDone;
    private Player player1;
    private Player player2;
    private Board board; //view
    private State state; //model
    private Rules rules; //model
    private ArrayList<Tile> tiles;
    //private int botButton;
    private int[][] board2D;

    public void start(Board board, Player player1, Player player2){
        //player1Turn = true;
        //gameDone = false;
        this.board = board;
        this.player1 = player1; //black tiles, bot
        this.player2 = player2; //white tiles, bot
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

        if(player1.isBot() && player2.isBot()){
            player1.makeMove(state);
            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles);
            player2.makeMove(state);
        }

        /* STILL WORKING ON THESE NEXT COMBINATIONS OF PLAYERS, for now test on bot against bot

        if(!player1.isBot() && player2.isBot()){
            player1.makeMove(x, y, state);
            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles);
            player2.makeMove(state);
        }

        if(player1.isBot() && !player2.isBot()){
            player1.makeMove(state);
            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles);
            player2.makeMove(x, y, state);
        }

        if(!player1.isBot() && !player2.isBot()){
            player1.makeMove(x, y, state);
            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles);
            player2.makeMove(x, y, state);
        }

        */

        tiles = state.convertToCollection(board2D);
        board.addTiles(tiles);
        checkGameStatus(); //check for game state
    }

    public void checkGameStatus(){
        if (board.getTiles().size() == 64) {
            gameDone = true;
            System.out.println("GAME FINISHED");
            rules.countTiles(board2D);
        }
    }
}