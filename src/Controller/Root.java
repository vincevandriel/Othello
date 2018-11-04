package Controller;
import View.Board;

import java.util.ArrayList;

public class Root {
    private int[][] board;
    private int tile;
    private ArrayList<Node> children;

    public Root(int[][] board,int tile){
        board = this.board;
        tile = this.tile;
    }

    public void addChildren(int[][] board, int tile) {
        Rules rules = new Rules();
        int[][] tempBoard = rules.checkMoves(board, tile, 0, 0);

        tile = reverseTile(tile);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(tempBoard[i][j] == 3) {
                    children.add(new Node(null, i, j, tile));
                }
            }
        }
    }

    public void addChildren() {
        
    }

    public int reverseTile(int tile) {
        if(tile == 1) {
            return 2;
        } else {
            return 1;
        }
    }


    public int[][] retrieveBoard(Node start) {
        ArrayList<Node> moves = new ArrayList<Node>();

        Node currentNode = start;
        boolean done = false;
        while(!done) {
            moves.add(0, currentNode);

            if(currentNode.getParent() != null) {
                currentNode = currentNode.getParent();
            } else {
                done = true;
            }
        }

        Rules rules = new Rules();
        int[][] tempBoard = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                tempBoard[i][j] = board[i][j];
            }
        }

        for(int i = 0; i < moves.size(); i++) {
            tempBoard[moves.get(0).getX()][ moves.get(0).getY()] = tile;
            tempBoard = rules.flip(tempBoard, moves.get(0).getX(), moves.get(0).getY(), moves.get(0).getTile());
            moves.remove(0);
        }
        return tempBoard;
    }



}
