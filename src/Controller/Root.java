package Controller;

import Controller.Node;
import Model.Rules;

import java.util.ArrayList;

public class Root {
    private int[][] board;
    //current Tile, so 1 = black and 2 = white
    private int tile;
    private final int DEPTH = 3;
    private Rules rules;
    private ArrayList<Node> children;

    public Root(int[][] board, int tile) {
        this.board = board;
        this.tile = tile;
        rules = new Rules();
        children = new ArrayList<Node>();
        generateTree();
    }

    public void generateTree() {
        generateRootChildren();
        ArrayList<Node> currentChildren = new ArrayList<Node>(children);

        //DEPTH - 1 because the children of the root already exist
        for (int i = 0; i < DEPTH - 1; i++) {
            int size = currentChildren.size();
            for (int j = 0; j < size; j++) {
                generateNodeChildren(currentChildren.get(0));
                currentChildren.addAll(currentChildren.get(0).getChildren());
                currentChildren.remove(0);
            }
        }
    }

    //Generates all children of the root
    public void generateRootChildren() {
        int[][] tempBoard = rules.checkMoves(board, tile);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (tempBoard[i][j] == 3) {
                    Node tempNode = new Node(null, i, j, reverseTile(tile));
                    //System.out.println(tile + ": X = " + i + " Y = " + j);
                    children.add(tempNode);
                }
            }
        }
        rules.clear3s(board);
    }

    //Generates all the children of a given node
    public void generateNodeChildren(Node node) {
        int[][] tempBoard = rules.checkMoves(retrieveBoard(node), node.getTile());

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (tempBoard[i][j] == 3) {
                    Node tempNode = new Node(node, i, j, reverseTile(node.getTile()));
                    //System.out.println(node.getTile() + ": X = " + i + " Y = " + j);
                    node.addChild(tempNode);
                }
            }
        }
    }

    public int reverseTile(int tile) {
        if (tile == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    public int[][] retrieveBoard(Node start) {
        ArrayList<Node> moves = new ArrayList<Node>();

        Node currentNode = start;
        boolean done = false;
        while (!done) {
            moves.add(0, currentNode);

            if (currentNode.getParent() != null) {
                currentNode = currentNode.getParent();
            } else {
                done = true;
            }
        }

        int[][] tempBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tempBoard[i][j] = board[i][j];
            }
        }

        int size = moves.size();
        for (int i = 0; i < size; i++) {
            tempBoard[moves.get(0).getX()][moves.get(0).getY()] = reverseTile(moves.get(0).getTile());
            tempBoard = rules.flip(tempBoard, moves.get(0).getX(), moves.get(0).getY(), reverseTile(moves.get(0).getTile()));
            moves.remove(0);
        }
        return tempBoard;
    }

    public void print() {
            /*
            System.out.println(children.size());
            for(int i = 0; i < children.size(); i++) {
                pront(retrieveBoard(children.get(i)));
            }
            */

            /*
            //System.out.println(children.get(0).getChildren().size());
            for(int i = 0; i < children.get(0).getChildren().size(); i++) {
                pront(retrieveBoard(children.get(0).getChildren().get(i)));
            }
            */

        //System.out.println(children.get(0).getChildren().size());
        for (int i = 0; i < children.get(0).getChildren().get(0).getChildren().size(); i++) {
            pront(retrieveBoard(children.get(0).getChildren().get(0).getChildren().get(i)));
        }

    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void pront(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getDepth(){
        return DEPTH;
    }

    public int getTile(){
        return tile;
    }
}
