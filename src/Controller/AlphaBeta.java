package Controller;

import Model.EvalFunction;

import java.util.ArrayList;

public class AlphaBeta implements EvalFunction {

        private Root root;
        private int depth;
        private Node tempNode;
        private ArrayList<Node> possibleMoves;
        private int bestValue;

        public AlphaBeta(Root root){
            possibleMoves = new ArrayList<>();
            this.root = root;
            tempNode = new Node(null, -1, -1, root.getTile());
            this.depth = root.getDepth();
            for(int i = 0; i < root.getChildren().size(); i++) {
                tempNode.addChild(root.getChildren().get(i));
            }
            bestValue = alphaBeta(tempNode, depth, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
            int[] coords = findBestPath(possibleMoves, bestValue);
            for(int i = 0; i < coords.length; i++){
                System.out.println(coords[i]);
            }
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

        public int alphaBeta(Node node, int depth, boolean max_player, int alpha, int beta) {

            int min;
            int max;

            if (node.getChildren() == null || depth == 0) {
                if(depth == root.getDepth()-1) {
                    possibleMoves.add(node);
                }
                int result = eval(root.retrieveBoard(node), root.getTile());
                node.setEvalValue(result);
                return result;
            }
            if (max_player) {
                max = Integer.MIN_VALUE;
                for (Node node1 : node.getChildren()) {
                    int eval = alphaBeta(node1, (depth-1),false, alpha, beta);
                    max = Math.max(max, eval);
                    alpha = Math.max(alpha, eval);

                    if(beta <= alpha) {
                        break;
                    }
                }
                node.setEvalValue(max);
                return max;
            } else {
                min = Integer.MAX_VALUE;
                for (Node node1 : node.getChildren()) {
                    int eval = alphaBeta(node1, (depth-1), true, alpha, beta);
                    min = Math.min(min, eval);
                    beta = Math.min(beta, eval);
                    if(beta <= alpha) {
                        break;
                    }
                }
                node.setEvalValue(min);
                if(depth == root.getDepth()-1) {
                    possibleMoves.add(node);
                }
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
    }
