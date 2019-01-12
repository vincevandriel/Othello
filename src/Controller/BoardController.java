package Controller;

import Model.*;
import View.Board;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
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
    //private boolean notDone = false;
    //private int blackWin = 0;
    //private int whiteWin = 0;
    //private int tie = 0;
    private int black = 0;
    private int white = 0;
    private boolean bot;
    //private ArrayList<int[]> winLoss = new ArrayList<>();

    public void start(Board board) {
        this.board = board;
        board2D = new int[board.getBlockSize()][board.getBlockSize()];
        state = new State(board.getBlockSize());
        rules = new Rules();
        tileCounter = new TileCounter();
        board2D = rules.setupBoard(board2D);
        state.setCurrentBoard(board2D);
        tiles = state.convertToCollection(board2D);
        board.addTiles(tiles);
        root = new Root(board2D, 1, 5);
        P1 = new MonteClappo(1, 100, new DiskSquareTables2()); //black tiles, bot
        P2 = new AlphaBeta(new DiskSquareTables2(),2,7); //black tiles, bot

        if (P1.getClass().getName().equals("Model.Human") || P2.getClass().getName().equals("Model.Human")) { //if there's at least one human
            if(!P1.getClass().getName().equals("Model.Human") || !P2.getClass().getName().equals("Model.Human")){ //if there's a human and a bot (in any order)
                bot = true;
                board.addBoardClickEventListener(this::boardClickHandler);
                if(!P1.getClass().getName().equals("Model.Human")){ //if there is a bot, prioritize bot in case it's Player 1
                    moveChoice();
                } //else, simply start with human player
            }else{ //both players are human, no bots
                board.addBoardClickEventListener(this::boardClickHandler);
                bot = false;
            }
        }else{ //both players are bots
            playBotGame();
        }
    }

    private void boardClickHandler(Dimension pos) {

        double x = pos.getHeight();
        double y = pos.getWidth();

        state.setX((int) x);
        state.setY((int) y);

        if (!gameDone) {
            moveChoice();
            if(bot) { //if any player is the bot (that is, if bot = true)
                moveChoice();
            }
        }

        updateBoard();
        if(gameOver(state.getCurrentBoard())){
            gameDone = true;
            results(); //check for game state
        }
    }

    public void results() {
            black = tileCounter.eval(board2D, 1);
            white = tileCounter.eval(board2D, 2);
            System.out.println("black tiles = " + black);
            System.out.println("white tiles = " + white);
            //P1.avgDuration();
    }

    /* -- method for experiments

    public void printResults(){
        System.out.println("games won by player 1 (black) = " + blackWin);
        System.out.println("games won by player 2 (white) = " + whiteWin);
        System.out.println("games tied = " + tie);
    }

    */


    public void playBotGame() {
        while(!gameOver(state.getCurrentBoard())){
            moveChoice();
        }
        results(); //check for game state

       /* --- This section will simply reset the board and give info on number of games won by each player
          --- (when we want to play n number of games)

        if(black>white){
            blackWin++;
        }else if(white>black){
            whiteWin++;
        }else{
            tie++;
        }

        board2D = rules.setupBoard(board2D);
        state.setCurrentBoard(board2D);
        tiles = state.convertToCollection(board2D);
        board.addTiles(tiles);
        root.setRoot(state.getCurrentBoard(), state.getTile());
        black = 0;
        white = 0;

        */
    }

    /*public void botMakeMove() {
        moveChoice();
    }
    */

    public boolean gameOver(int[][] board) { //TEMPORAL METHOD, not sure where it should go
        int[][] tempBoard = rules.clone(board);
        int[][] tempBoardTile1 = rules.clone(rules.checkMoves(tempBoard, 1));
        int[][] tempBoardTile2 = rules.clone(rules.checkMoves(tempBoard, 2));
        tempBoard = rules.clear3s(tempBoard);
        for(int i = 0; i<tempBoardTile1.length; i++){
            for(int j = 0; j< tempBoardTile1[0].length; j++){
                if(tempBoardTile1[i][j] == 3 || tempBoardTile2[i][j] == 3){
                    return false;
                }
            }
        }
        return true;
    }

    public void moveChoice() {  //who will be making the next move
        if (state.getTile() == 1) {
            P1.makeMove(state);
        } else {
            P2.makeMove(state);
        }
        updateBoard();
    }

    public void updateBoard(){ //update board in graphics every time a move is made
        root.setRoot(state.getCurrentBoard(), state.getTile());
        tiles = state.convertToCollection(state.getCurrentBoard());
        board.addTiles(tiles);
    }
}