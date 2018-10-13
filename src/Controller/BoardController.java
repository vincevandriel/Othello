package Controller;

import Model.State;
import Model.Tile;
import View.Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardController{
    private boolean opponentTurn;
    private boolean gameDone;
    private Board board;
    private State state;
    private Rules rules;
    private ArrayList<Tile> tiles;
    private int botButton;
    private SimpleAI bot;
    private int[][] board2D;

    public void start(Board board){
        gameDone = false;
        Scanner in = new Scanner(System.in);
        System.out.println("Bot - press 0");
        System.out.println("No Bot - press 1");
        botButton = in.nextInt();
        in.close();
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
    private void boardClickHandler(Dimension pos) {
        double x = pos.getHeight();
        double y = pos.getWidth();
        int[][] tempBoard2D = rules.checkMoves(board2D, tile, x, y);
        if(rules.moveStatus(tempBoard2D, (int)x, (int)y) == 1 && gameDone == false){
            opponentTurn = true;
            board2D = tempBoard2D;
            board2D = rules.clear3s(board2D); //clear possible legal spots (marked as 3s)
            if (tile == 0) { //change tile on 2D board before flipping tiles
                board2D[(int) x][(int) y] = 2;
            } else {
                board2D[(int) x][(int) y] = 1;
            }
            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles); //add the new move and repaint
            board2D = rules.flip(board2D, x, y, tile);
            tiles = state.convertToCollection(board2D);
            board.addTiles(tiles); //add flipped tiles and repaint
            if (tile == 1) {
                tile = 0;
            } else {
                tile = 1;
            }
            if(board.getTiles().size() == 64){
                gameDone = true;
                System.out.println("GAME FINISHED");
                rules.countTiles(board2D);
            }
        } else if(gameDone == false){
            if(rules.moveStatus(tempBoard2D, (int)x, (int)y) == 0){
                System.out.println("NO MOVES AVAILABLE FOR YOU - switch turn to opponent.");
                opponentTurn = true;
            }else{
                System.out.println("Illegal move, try again.");
                opponentTurn = false;
            }
        }

        if (botButton == 0 && opponentTurn == true && gameDone == false) {
            int[][] stateBoard = board2D; //copy for state tree
            tempBoard2D = rules.checkMoves(board2D, tile, -1, -1);
            TreeNode<Integer> currentState = new TreeNode<>(-5,-5, null);
            currentState.addChildren(rules.getCoordinateList());
            bot = new SimpleAI(); //pass on board with available spots
            tempBoard2D = bot.pickMove(tempBoard2D, tile);
            if(tempBoard2D == null){
                System.out.println("NO MOVES AVAILABLE FOR AI - switch turn to opponent.");
            }else{
                tempBoard2D = rules.clear3s(tempBoard2D);
                tiles = state.convertToCollection(tempBoard2D);
                board.addTiles(tiles); //add the new move and repaint
                tempBoard2D = rules.flip(tempBoard2D, bot.getX(), bot.getY(), tile);
                tiles = state.convertToCollection(tempBoard2D);
                board.addTiles(tiles); //add flipped tiles and repaint
            }
            if (tile == 1) {
                tile = 0;
            } else {
                tile = 1;
            }
            if(board.getTiles().size() == 64){
                gameDone = true;
                System.out.println("GAME FINISHED");
                rules.countTiles(board2D);
            }
        }
    }
}
