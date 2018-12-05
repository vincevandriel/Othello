package Model;

import Controller.BoardController;
import View.Board;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;

public class AlphaBeta implements Player {

    public Root root;
    private int tile;
    private int depth;
    private Rules rules;
    private Node tempNode;
    private ArrayList<Node> possibleMoves;
    private int bestValue;
    private EvalFunction evalFunction;

    public AlphaBeta(EvalFunction evalFunction, int tile, int depth){
        this.tile = tile;
        //root = new Root(BoardController.root.getBoard(), tile ,depth);
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
        bestValue = alphaBeta(tempNode, depth, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int[] coords = findBestPath(possibleMoves, bestValue);
        if(coords == null){
            currentState.switchTile();
            return;
        }
        //System.out.println(coords[0] + "and" + coords[1]);
        int[][] board2D = currentState.getCurrentBoard(); //get current state
        board2D[coords[0]][coords[1]] = tile;
        board2D = rules.flip(board2D, coords[0], coords[1], root.getTile());
        currentState.setCurrentBoard(board2D);
        currentState.switchTile();
    }

    public int alphaBeta(Node node, int depth, boolean max_player, int alpha, int beta) {

        int min;
        int max;

        if (node.getChildren() == null || depth == 0) {
            /*if(depth == root.getDepth()-1 && node.getChildren() == null) {
                possibleMoves.add(node);
            }*/
            int result = eval(root.retrieveBoard(node), tile);
            node.setEvalValue(result);
            return result;
        }
        if (max_player) {
            max = Integer.MIN_VALUE;
            for (Node node1 : node.getChildren()) {
                int eval = alphaBeta(node1, (depth-1),false, alpha, beta);
                max = Math.max(max, eval);
                alpha = Math.max(alpha, eval);
                node1.setEvalValue(eval);
                if(depth == root.getDepth()) {
                    possibleMoves.add(node1);
                }
                node.setEvalValue(max);
                if(beta <= alpha) {
                    break;
                }
            }
            return max;
        } else {
            min = Integer.MAX_VALUE;
            for (Node node1 : node.getChildren()) {
                int eval = alphaBeta(node1, (depth-1), true, alpha, beta);
                min = Math.min(min, eval);
                beta = Math.min(beta, eval);
                node1.setEvalValue(eval);
                node.setEvalValue(min);
                if(beta <= alpha) {
                    break;
                }
            }
            //node.setEvalValue(min);
           /* if(depth == root.getDepth()-1) {
                possibleMoves.add(node);
            }*/
            return min;
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
        tempNode = new Node(null, -1, -1, tile);
            for(int i = 0; i < root.getChildren().size(); i++) {
            tempNode.addChild(root.getChildren().get(i));
        }
    }
}

