package Controller;
import Model.EvalFunction;

import java.util.ArrayList;


public class Minimax implements EvalFunction {

    private Root root;
    private int depth;
    private Node tempNode;
    private ArrayList<Node> possibleMoves;
    private int bestValue;

    public Minimax(Root root){
        possibleMoves = new ArrayList<>();
        this.root = root;
        tempNode = new Node(null, -1, -1, root.getTile());
        this.depth = root.getDepth();
        for(int i = 0; i < root.getChildren().size(); i++) {
            tempNode.addChild(root.getChildren().get(i));
        }
        bestValue = minimax(tempNode, depth, true);
        findBestPath(possibleMoves, bestValue);
    }

    @Override
    public int eval(int[][] board, int tile) {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == tile) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int minimax(Node node, int depth, boolean max_player) {

        int value;

        if (node.getChildren() == null || depth == 0) {
            if(depth == root.getDepth()-1) {
                possibleMoves.add(node);
            }
            int result = eval(root.retrieveBoard(node), root.getTile());
            node.setEvalValue(result);
            return result;
        }
        if (max_player) {
            value = Integer.MIN_VALUE;
            for (Node node1 : node.getChildren()) {
                value = Math.max(value, minimax(node1, (depth-1),false));
            }
            node.setEvalValue(value);
            return value;
        } else {
            value = Integer.MAX_VALUE;
            for (Node node1 : node.getChildren()) {
                value = Math.min(value, minimax(node1, (depth-1), true));
            }
            node.setEvalValue(value);
            if(depth == root.getDepth()-1) {
                possibleMoves.add(node);
            }
            return value;
        }
    }

    public int[] findBestPath(ArrayList<Node> possibleMoves, int bestValue){
        for(int i = 0; i < possibleMoves.size(); i++){
            if(possibleMoves.get(i).getEvalValue() == bestValue){
                int[] coords = new int[2];
                coords[0] = possibleMoves.get(i).getX();
                coords[1] = possibleMoves.get(i).getY();
                return coords;
            }
        }
        return null;
    }
}

