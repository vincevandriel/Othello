package Controller;

import Model.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Rules {

    ArrayList<TreeNode<Integer>> spotsCoordinates = new ArrayList<>();

    public int[][] setupBoard(int[][] board) {
        int half = board.length/2;
        board[half - 1][half - 1] = 1;
        board[half][half - 1] = 2;
        board[half - 1][half] = 2;
        board[half][half] = 1;

        return board;
    }

    public ArrayList<Tile> convertToCollection(int[][] board){
        ArrayList<Tile> result = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != 0) {
                    result.add(new Tile(new Dimension(j+1, i+1), board[i][j]));
                }
            }
        }
        return result;
    }

    public void pront(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * method that checks the neighbor (empty) spots of each opponent tile placed on the board. While checking each opponent tile's neighbors,
     * when an empty one is found, method checks whether placing a tile there will make a legal by moving in the direction to the opponent tile being checked
     * and checking whether there exists a line of opponent tiles ending in a tile of the current color to close the move. If so, the empty neighbor spot around the opponent
     * checked spot will be marked with a 3, which represents a possible spot to make a move. This process is done for every possible empty cell surrounding every
     * opponent tile set on the board.
     *
     * @param color to represent who wants to make the move and therefore find the correct opponent existing tiles
     */
    public int[][] checkMoves(int[][] board, int color, double x, double y) {
        if(color == 0){
            color = 2;
        }else{
            color = 1;
        }
        int opponentColor;
        if (color == 1) {
            opponentColor = 2;
        } else {
            opponentColor = 1;
        }
        ArrayList<int[]> opponentSpots = new ArrayList<>();
        opponentSpots = findOpponentSpots(board, color, opponentColor);
        int counter = 0;
        while (counter < opponentSpots.size()) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int xCoor = opponentSpots.get(counter)[0];
                    int yCoor = opponentSpots.get(counter)[1];
                    if (((xCoor + i) >= 0) && ((xCoor + i) < board.length) && ((yCoor + j) >= 0) && ((yCoor + j) < board[0].length) && board[xCoor + i][yCoor + j] == 0) {
                        int temp_i = -1 * i;
                        int temp_j = -1 * j;
                        while (((xCoor + temp_i) >= 0) && ((xCoor + temp_i) < board.length) && ((yCoor + temp_j) >= 0) && ((yCoor + temp_j) < board[0].length) && (board[xCoor + temp_i][yCoor + temp_j] == opponentColor)) {
                            xCoor += temp_i;
                            yCoor += temp_j;
                        }
                        if (((xCoor + temp_i) >= 0) && ((xCoor + temp_i) < board.length) && ((yCoor + temp_j) >= 0) && ((yCoor + temp_j) < board[0].length) && board[xCoor + temp_i][yCoor + temp_j] == color) {
                            board[opponentSpots.get(counter)[0]+i][opponentSpots.get(counter)[1]+j] = 3;
                            int[] coordinates = new int[2];
                            spotsCoordinates.add(new TreeNode<Integer>(opponentSpots.get(counter)[0]+i, opponentSpots.get(counter)[1]+j, null));
                        }
                    }
                }
            }
            counter++;
        }
        return board;
    }

    /**
     * This method goes through the board and finds the coordinates of the opponent tiles already set on the board.
     * Then all these coordinates are saved into arrays, which are saved at the same time within an arrayList.
     * @param color to represent who wants to make the move and therefore find the correct opponent existing tiles
     * @param opponentColor to represent the opponent color to the variable "color"
     * @return arrayList containing arrays with coordinates x and y of each opponent tile on the board
     */

    public ArrayList<int[]> findOpponentSpots(int[][] board, int color, int opponentColor){
        ArrayList<int[]> opponentSpots = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == opponentColor){
                    int[] position = new int[2];
                    position[0] = i; //x
                    position[1] = j; //y
                    opponentSpots.add(position);
                }
            }
        }
        return opponentSpots;
    }

    /**
     * This method is initialized after a piece was placed. It flips the respective pieces of the opponent.
     **/
    public int[][] flip (int[][] board, double x, double y, int currentColour){
        if(currentColour == 0){
            currentColour = 2;
        }else{
            currentColour = 1;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && i == j)) {
                    int amount = checkDirection(board, x, y, i, j, currentColour);
                    if (amount != 0) {
                        int currentX = (int)x + i;
                        int currentY = (int)y + j;
                        for (int k = 0; k < amount; k++) {
                            board[currentX][currentY] = currentColour;
                            currentX += i;
                            currentY += j;
                        }
                    }
                }
            }
        }
        return board;
    }

    /**
     * @return amount of chips being flipped in one direction. If amount is 0, no chips can be flipped.
     * x and y refer to the coordinates in the grid.
     * xDirection and yDirection show the x-and y-Distance and Direction that is traveled when checking. They should only be set to 1, 0 or -1.
     * <p>
     * As an example: If both are set to -1, the direction that is checked is up left
     * If xDirection is set to 0, and yDirection is set to 1, the direction that is checked is straight down.
     **/
    public int checkDirection (int[][] board, double x, double y, int xDirection, int yDirection, int currentColour){
        int currentX = (int)x + xDirection;
        int currentY = (int)y + yDirection;

        int amount = 0;
        int opposingColour;

        if (currentColour == 1) {
            opposingColour = 2;
        } else if (currentColour == 2) {
            opposingColour = 1;
        } else {
            System.out.println("ERROR!");
            return 0;
        }

        while (currentX >= 0 && currentX < board.length && currentY >= 0 && currentY < board.length && board[currentX][currentY] == opposingColour){
            amount++;
            currentX += xDirection;
            currentY += yDirection;
        }
        if((currentX >= 0 && currentX < board.length && currentY >= 0 && currentY < board.length && board[currentX][currentY] == 0) || (currentX < 0 || currentX >= board.length || currentY < 0 || currentY >= board.length)){
            return 0;
        }
        return amount;
    }

    public int[][] clear3s(int[][] board){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] == 3){
                    board[i][j] = 0;
                }
            }
        }
        return board;
    }

    public int moveStatus(int[][] board, int x, int y){
        int counter = 0;
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] == 3){
                    counter++;
                }
            }
        }
        if(counter == 0){
            return 0; // no possible moves
        }
        if(board[x][y] == 3){
            return 1; //move is legal
        }
        if(board[x][y] != 3 && counter>0){
            return -1; //move not legal
        }
        return -100000;
    }

    public void countTiles(int[][] board){
        int counterBlack = 0;
        int counterWhite = 0;
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] == 1){
                    counterBlack++;
                }else{
                    counterWhite++;
                }
            }
        }
        if(counterBlack>counterWhite){
            System.out.println("Winner is player with Black tiles!");
        }
        else if(counterBlack==counterWhite){
            System.out.println("Draw");
        }
        else{
            System.out.println("Winner is player with White tiles!");
        }
        System.out.println("Black tiles - " + counterBlack);
        System.out.println("White tiles - " + counterWhite);
    }

    public ArrayList<TreeNode<Integer>> getCoordinateList(){
        return spotsCoordinates;
    }
}
