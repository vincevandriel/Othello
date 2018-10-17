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

    public void makeChildren(){

    }

    public void addChildren(List<TreeNode<Node>> children){ //checked
        for(int i = 0; i<children.size(); i++){
            children.get(i).setParent(this);
            this.children.add(children.get(i));
        }
    }

}
