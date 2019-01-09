package Model;

import Controller.BoardController;
import View.Board;

import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class AlphaBeta implements Player {

    public Root root;
    private int tile;
    private long numMoves = 0;
    private long sumTime = 0;
    private int depth;
    private Rules rules;
    private Node tempNode;
    private ArrayList<Node> possibleMoves;
    private double bestValue;
    private EvalFunction evalFunction;
    private HashMap<Integer, Node> killerMoves =  new HashMap<>();

    public AlphaBeta(EvalFunction evalFunction, int tile, int depth){
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
        numMoves++;
        setRoot();
        killerMoves.clear();
        long startTime = System.nanoTime();
        bestValue = alphaBeta(tempNode, depth, true, Double.MIN_VALUE, Double.MAX_VALUE);
        System.out.println("bestValue = " + bestValue);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        sumTime = sumTime + duration;
        System.out.println("duration of method = " + duration);
        int[] coords = findBestPath(possibleMoves, bestValue);
        if(coords == null){
            currentState.switchTile();
            return;
        }
        int[][] board2D = currentState.getCurrentBoard(); //get current state
        board2D[coords[0]][coords[1]] = tile;
        board2D = rules.flip(board2D, coords[0], coords[1], root.getTile());
        currentState.setCurrentBoard(board2D);
        currentState.switchTile();
    }

    public double alphaBeta(Node node, int depth, boolean max_player, double alpha, double beta) {

        Random random = new Random();

        double min;
        double max;

        if (node.getChildren() == null || depth == 0) {
            /*if(depth == root.getDepth()-1 && node.getChildren() == null) {
                possibleMoves.add(node);
            }*/
            double result = eval(root.retrieveBoard(node), tile);
            double temp = random.nextDouble();
            node.setEvalValue(result + temp);
            return (result + temp);
        }
        if (max_player) {
            max = -Double.MAX_VALUE;
            for (Node child : node.getChildren()) {
                double eval = alphaBeta(child, (depth-1),false, alpha, beta);
                max = Math.max(max, eval);
                alpha = Math.max(alpha, eval);
                child.setEvalValue(eval);
                if(depth == root.getDepth()) {
                    possibleMoves.add(child);
                }
                //node.setEvalValue(max);
                if(beta <= alpha) { //add killer moves (nodes) whenever they prune
                    killerMoves.put((depth - 1), child); //ad// d move which caused pruning deeper
                    orderChildren(node, depth);
                    break;
                }
            }
            node.setEvalValue(max);
            return max;
        } else {
            min = Double.MAX_VALUE;
            for (Node child : node.getChildren()) {
                double eval = alphaBeta(child, (depth-1), true, alpha, beta);
                min = Math.min(min, eval);
                beta = Math.min(beta, eval);
                child.setEvalValue(eval);
                node.setEvalValue(min);
                if(beta <= alpha) {
                    killerMoves.put((depth-1), child); //add move which caused pruning deeper
                    orderChildren(node, depth);
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

    public int[] findBestPath(ArrayList<Node> possibleMoves, double bestValue){
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

    public void orderChildren(Node node, int depth) {
        int index = -1; //this is to order nodes based on which one is a killer move in other plays
        int childrenSize = node.getChildren().size();
        ArrayList<Node> tempChildren; //copy to rearrange children

        if (node.getChildren() != null) {

            tempChildren = new ArrayList<>(node.getChildren());

            for (int i = 0; i < tempChildren.size(); i++) {

                if (tempChildren.get(i) == killerMoves.get(depth - 1)) {
                    index = i; //set index to swap orders
                }
            }
            if (index > 0) {
                Node tempNode = tempChildren.get(0);
                tempChildren.set(0, killerMoves.get(depth - 1));
                tempChildren.set(index, tempNode);
                node.setChildren(tempChildren);
            }
        }
    }


    public void setRoot(){
        root = new Root(BoardController.root.getBoard(), tile ,depth);
        possibleMoves = new ArrayList<>();
        tempNode = new Node(null, -1, -1, tile);
         for(int i = 0; i < root.getChildren().size(); i++) {
            tempNode.addChild(root.getChildren().get(i));
        }
    }

    public void avgDuration(){
        long duration =  (sumTime / numMoves);
        System.out.println("Average length = " + duration);
    }
}