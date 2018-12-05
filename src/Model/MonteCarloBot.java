package Model;

import java.util.ArrayList;
import java.util.Random;

public class MonteCarloBot implements Player {
    public static Root root;
    Rules rules;
    boolean timebased;
    int times;
    int tile;
    private Random random;
    private int[][] board;
    private ArrayList<int[]> initialMoves = new ArrayList<>();

    public MonteCarloBot(int tile) {
        this.tile = tile;
        random = new Random();
        rules = new Rules();
    }

    //This is just so that the bot implements Player... Should this return coordinates?
    @Override
    public void makeMove(State currentState) {
         int[][] currentboard = currentState.getCurrentBoard();
        int[] bestMove = new int[2];
         double highestWinRate = 0;
        ArrayList<int[][]> boards = getFirstMoves(currentState);
        for(int i = 0; i< boards.size(); i++){
           double winRate = evaluateMove(currentboard, tile, 100);
           if(winRate > highestWinRate){
               highestWinRate = winRate;
               bestMove[0] = initialMoves.get(i)[0];
               bestMove[1] = initialMoves.get(i)[1];
           }
        }
        int[][] tempBoard = currentState.getCurrentBoard();
        tempBoard[bestMove[0]][bestMove[1]] = tile;
        tempBoard = rules.flip(tempBoard, (double) bestMove[0], (double) bestMove[1], tile);
        //currentState.setCurrentBoard(tempBoard);
    }

    //plays the specified amount of games given by times and returns the win rate
    //colour is the colour playing next
    public double evaluateMove(int[][] board, int colour, int times) {
        int wins = 0;
        int losses = 0;
        for (int i = 0; i < times; i++) {
            rules.pront(board);
            int[][] currentBoard = board;
            if(playGame(currentBoard, colour)){
                wins++;
            }else{
                losses++;
            }
        }
        return (double) wins / (wins + losses);
    }

    public int[][] playRandomMove(int[][] board, int colour) {
        Random y = new Random();
        int[][] tempBoard = board;
        tempBoard = rules.checkMoves(tempBoard, colour);
        ArrayList<int[]> availMoves = new ArrayList<int[]>();

        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[0].length; j++) {
                if (tempBoard[i][j] == 3) {
                    int[] move = new int[2];
                    move[0] = i;
                    move[1] = j;
                    availMoves.add(move);
                }
            }
        }

        if (availMoves.size() == 0) {
            return null;
        } else {
            int random = y.nextInt(availMoves.size());
            tempBoard[availMoves.get(random)[0]][availMoves.get(random)[1]] = colour;
            tempBoard = rules.flip(board, (double) availMoves.get(random)[0], (double) availMoves.get(random)[1], colour);
            return tempBoard;
        }
    }

        public int swapColours ( int tile){
            if (tile == 1) {
                return 2;
            } else {
                return 1;
            }
        }

        public boolean playGame(int[][] board, int colour) {
            int tempColour = colour;
            int[][] tempBoard = board;
            while (!gameOver(tempBoard)) {
                tempColour = swapColours(tempColour);
                tempBoard = playRandomMove(tempBoard, tempColour);
            }

            return checkGame(tempBoard, colour);
        }

        public boolean checkGame(int[][] board, int colour){
            int[][] tempBoard = board;
            int black = 0;
            int white = 0;
            for(int i = 0; i < tempBoard.length; i++){
                for(int j = 0; j < tempBoard.length; j++){
                    if(tempBoard[i][j] == 1){
                        black++;
                    }else if(tempBoard[i][j] == 2){
                        white++;
                    }
                }
            }
            if((colour == 1 && (black>white)) || (colour == 2 && (black<white))){
                return true;
            }else{
                return false;
            }
        }

        public boolean gameOver(int[][] board){

            int[][] tempBoard = board;
            int[][] tempBoardTile1 = rules.checkMoves(tempBoard, 1);
            int[][] tempBoardTile2 = rules.checkMoves(tempBoard, 2);

            for(int i = 0; i<tempBoardTile1.length; i++){
                for(int j = 0; j< tempBoardTile1[0].length; j++){
                    if(tempBoardTile1[i][j] == 3 || tempBoardTile2[i][j] == 3){
                        return false;
                    }
                }
            }
            return true;
        }

        public ArrayList<int[][]> getFirstMoves(State currentState){
            ArrayList<int[][]> boards = new ArrayList<>();
            int[][] board = currentState.getCurrentBoard();
            board = rules.checkMoves(board, tile);
            for(int i = 0; i<board.length; i++){
                for(int j = 0; j< board[0].length; j++){
                    if(board[i][j] == 3){
                        int[][] tempBoard = board;
                        tempBoard[i][j] = tile;
                        tempBoard = rules.clear3s(tempBoard);
                        tempBoard = rules.flip(tempBoard, (double) i, (double) j, tile);
                        boards.add(tempBoard);
                        int[] coords = {i, j};
                        initialMoves.add(coords);
                    }
                }
            }

            return boards;
        }
}
