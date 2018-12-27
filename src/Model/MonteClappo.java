package Model;

import java.util.ArrayList;
import java.util.Random;

public class MonteClappo implements Player {
    Rules rules;
    private int times;
    int tile;
    private ArrayList<int[]> initialMoves = new ArrayList<>();

    public MonteClappo(int tile, int times) {
        this.times = times;
        this.tile = tile;
        rules = new Rules();
    }

    @Override
    public void makeMove(State currentState) {
         int[][] currentboard = clone(currentState.getCurrentBoard());
        int[] bestMove = new int[2];
         double highestWinRate = 0;
        ArrayList<int[][]> boards = getFirstMoves(currentState);
        int size = boards.size();
        for(int i = 0; i < size; i++){
           double winRate = evaluateMove(boards.get(i), tile, times);
           if(winRate > highestWinRate){
               highestWinRate = winRate;
               bestMove[0] = initialMoves.get(i)[0];
               bestMove[1] = initialMoves.get(i)[1];
           }

        }

        int[][] tempBoard = currentState.getCurrentBoard();
        if(size == 0) {
            System.out.print("No possible moves for Monte-Carlo Bot. Switching player.");
            currentState.switchTile();
        } else {
            tempBoard[bestMove[0]][bestMove[1]] = tile;
            tempBoard = rules.flip(tempBoard, (double) bestMove[0], (double) bestMove[1], tile);
            currentState.setCurrentBoard(tempBoard);
            currentState.switchTile();
        }
    }

    //plays the specified amount of games given by times and returns the win rate
    //colour is the colour playing next
    public double evaluateMove(int[][] board, int colour, int times) {
        double wins = 0;
        double losses = 0;
        for (int i = 0; i < times; i++) {
            int[][] currentBoard = clone(board);
            if(playGame(currentBoard, colour)){
                wins++;
            }else{
                losses++;
            }
        }

        return (wins / (wins + losses)) + 1;
    }

    public int[][] playRandomMove(int[][] board, int colour) {
        Random y = new Random();
        int[][] tempBoard = clone(board);
        tempBoard = rules.checkMoves(tempBoard, colour);
        ArrayList<int[]> availMoves = new ArrayList<>();

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
            return tempBoard;
        } else {
            int random = y.nextInt(availMoves.size());
            tempBoard[availMoves.get(random)[0]][availMoves.get(random)[1]] = colour;
            tempBoard = rules.clear3s(tempBoard);
            tempBoard = rules.flip(tempBoard, (double) availMoves.get(random)[0], (double) availMoves.get(random)[1], colour);
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
            int[][] tempBoard = clone(board);
            boolean done = false;
                while (!done) {
                    if(gameOver(tempBoard)) {
                        done = true;
                    }
                tempColour = swapColours(tempColour);
                tempBoard = playRandomMove(tempBoard, tempColour);
                }
            return checkGame(tempBoard, colour);
        }

        public boolean checkGame(int[][] board, int colour){ //THIS is where WE FIND THE NULL
            int[][] tempBoard = clone(board);

            int black = 0;
            int white = 0;
            for(int i = 0; i < tempBoard.length; i++){
                for(int j = 0; j < tempBoard[0].length; j++){
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
            int[][] tempBoard = clone(board);
            int[][] tempBoardTile1 = clone(rules.checkMoves(tempBoard, 1));
            int[][] tempBoardTile2 = clone(rules.checkMoves(tempBoard, 2));
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

        public ArrayList<int[][]> getFirstMoves(State currentState){
            initialMoves.clear();
            ArrayList<int[][]> boards = new ArrayList<>();
            int[][] board = clone(currentState.getCurrentBoard());
            board = rules.checkMoves(board, tile);
            for(int i = 0; i<board.length; i++){
                for(int j = 0; j< board[0].length; j++){
                    if(board[i][j] == 3){
                        int[][] tempBoard = clone(board);
                        tempBoard[i][j] = tile;
                        tempBoard = rules.clear3s(tempBoard);
                        tempBoard = rules.flip(tempBoard, (double) i, (double) j, tile);
                        boards.add(tempBoard);
                        int[] coords = new int[2];
                        coords[0] = i;
                        coords[1] = j;
                        initialMoves.add(coords);
                    }
                }
            }
            return boards;
        }

        public int[][] clone(int[][] board){
            int[][] tempBoard = new int[board.length][board[0].length];
            for(int x = 0; x < board.length; x++){
                for(int y = 0; y< board[0].length; y++){
                    tempBoard[x][y] = board[x][y];
                }
            }
            return tempBoard;
        }

}
