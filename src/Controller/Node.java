package Controller;

import java.util.ArrayList;

public class Node {
    private int x;
    private int y;
    private int tile;
    private int evalValue;
    private Node parent;
    private ArrayList<Node> children;

    public Node(Node parent, int x, int y, int tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
        this.parent = parent;
        children = new ArrayList<Node>();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Node getParent() {
        return parent;
    }
    public int getTile() {
        return tile;
    }
    public ArrayList<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void setEvalValue(int evalValue){
        this.evalValue = evalValue;
    }

    public int getEvalValue(){
        return evalValue;
    }

    public int[] getCoords(){
        int[] coords = new int[2];
        coords[0] = this.x;
        coords[1] = this.y;
        return coords;
    }
}
