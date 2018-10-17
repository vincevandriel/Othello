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
    public void setParent(Node parent){ //checked
        this.parent = parent;
        // Finish this method
    }
    public void addChildren(List<Node> children) { //checked
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParent(this);
            this.children.add(children.get(i));
        }


    }
}
