package Controller;
import java.util.ArrayList;
import java.util.List;

public class Root {
    private int[][] board;
    private int tile;
    private ArrayList<Node> children;

    public Root(int[][] board,int tile){
        board = this.board;
        tile = this.tile;
    }
    /*
    public void addChildren(List<Node> children){ //checked
            for(int i = 0; i<children.size(); i++){
                children.get(i).setParent(this);
                this.children.add(children.get(i));
            }
    }
    */

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

    public int reverseTile(int tile) {
        if(tile == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int[][] retrieveBoard(int[][] currentBoard, Node start) {
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
        int[][] board = currentBoard;
        for(int i = 0; i < moves.size(); i++) {
            currentBoard = rules.flip(currentBoard, moves.get(0).getX(), moves.get(0).getY(), moves.get(0).getTile());
            moves.remove(0);
        }
        return currentBoard;
    }

}
