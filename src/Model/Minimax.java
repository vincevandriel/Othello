package Model;

import Controller.BoardController;

import java.util.ArrayList;


public class Minimax implements Player {

    private Root root;
    private int depth;
    private int tile;
    private Node tempNode;
    private ArrayList<Node> possibleMoves;
    private int bestValue;
    private Rules rules;
    private EvalFunction evalFunction;

    public Minimax(EvalFunction evalFunction, int tile, int depth){
        this.tile = tile;
        this.depth = depth;
        rules = new Rules();
        this.evalFunction = evalFunction;
    }

    public int eval(int[][] board, int tile) {
        return evalFunction.eval(board, tile);
    }

    @Override
    public void makeMove(State currentState) {
        setRoot();
        bestValue = minimax(tempNode, depth, true);
        int[] coords = findBestPath(possibleMoves, bestValue);
        if(coords == null){
            currentState.switchTile();
            System.out.println("no available moves with minimax");
            return;
        }
        int[][] board2D = currentState.getCurrentBoard(); //get current state
        board2D[coords[0]][coords[1]] = tile;
        board2D = rules.flip(board2D, coords[0], coords[1], root.getTile());
        currentState.setCurrentBoard(board2D);
        currentState.switchTile();
    }

    public int minimax(Node node, int depth, boolean max_player) {

        int value;

        if (node.getChildren() == null || depth == 0) {
            int result = eval(root.retrieveBoard(node), root.getTile());
            node.setEvalValue(result);
            return result;
        }
        if (max_player) {
            value = Integer.MIN_VALUE;
            for (Node node1 : node.getChildren()) {
                int eval = minimax(node1, (depth-1),false);
                value = Math.max(value, eval);
                node1.setEvalValue(eval);
                if(depth == root.getDepth()){
                    possibleMoves.add(node1);
                }
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

    public void setRoot(){
        root = new Root(BoardController.root.getBoard(), tile ,depth);
        possibleMoves = new ArrayList<>();
        tempNode = new Node(null, -1, -1, root.getTile());
        for(int i = 0; i < root.getChildren().size(); i++) {
            tempNode.addChild(root.getChildren().get(i));
        }
    }
}

