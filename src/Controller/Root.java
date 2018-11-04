package Controller;
import Model.Rules;

import java.util.ArrayList;

public class Root {
    private int[][] board;
    //current Tile, so 1 = black and 2 = white
    private int tile;
    private ArrayList<Node> children;

    public Root(int[][] board,int tile){
        this.board = board;
        this.tile = tile;
        children = new ArrayList<Node>();
    }

    public void addChildren(int[][] board) {
        Rules rules = new Rules();
        int[][] tempBoard = rules.checkMoves(board, tile);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(tempBoard[i][j] == 3) {
                    children.add(new Node(null, i, j, reverseTile(tile)));
                }
            }
        }
    }

    public void addChildren(Node node) {
        Rules rules = new Rules();
        int[][] tempBoard = rules.checkMoves(retrieveBoard(node), node.getTile());

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(tempBoard[i][j] == 3) {
                    children.add(new Node(node, i, j, reverseTile(node.getTile())));
                }
            }
        }

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
