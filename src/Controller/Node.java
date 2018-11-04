package Controller;

import java.util.List;

public class Node {
   private Node parent;
   private List<Node> children;
   private int x;
   private int y;
   private int tile;

    public Node(Node parent, int x, int y, int tile){
        x = this.x;
        y = this.y;
        tile = this.tile;
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

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void setChildren(List<Node> children) {
        this.children =  children;
    }
}
