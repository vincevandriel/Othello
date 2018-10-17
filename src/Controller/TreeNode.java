package Controller;
import java.util.ArrayList;
import java.util.List;

public class TreeNode<T>{

    //public ArrayList<TreeNode<T>> list = new ArrayList<>();
    private int depth;
    private static int counter = 0;
    private T x = null;
    private T y = null;
    private List<TreeNode<T>> children = new ArrayList<>();
    private TreeNode<T> parent = null;

    public TreeNode(T x, T y, TreeNode<T> parent){
        this.x = x;
        this.y = y;
        setParent(parent);
    }
    public TreeNode<T> getRoot(){ //checked
        TreeNode<T> tempParent = parent;
        while(tempParent.getParent() != null){
            tempParent = tempParent.getParent();
        }
        return tempParent;
    }

    public TreeNode<T> addChild(TreeNode<T> child){ //checked
        children.add(child);
        counter++;
        return child;
    }

    public void addChildren(List<TreeNode<T>> children){ //checked
        for(int i = 0; i<children.size(); i++){
            children.get(i).setParent(this);
            this.children.add(children.get(i));
        }
    }

    public List<TreeNode<T>> getChildren(){ //checked
        return children;
    }

    public T getX(){
        return x;
    }

    public T getY(){
        return y;
    }

    public void setData(T x, T y){ //checked
        this.x = x;
        this.y = y;
        // Finish this method
    }

    public void setParent(TreeNode<T> parent){ //checked
        this.parent = parent;
        // Finish this method
    }

    public TreeNode<T> getParent(){ //checked
        if(parent == null){
            return null;
        }else{
            return parent;
        }
    }

/*  public void visit(TreeNode<T> node, TreeNode<T> node_x, TreeNode<T> node_y){
    if(node == node_x){
      list.add(node_x);
    }else if(node == node_y){
      list.add(node_y);
    }
  }*/

  /*public ArrayList<TreeNode<T>> preOrder(TreeNode<T> root, TreeNode<T> node_x, TreeNode<T> node_y){
    boolean done = false;
    if(!done){
      if(list.size() < 3){
        visit(root, node_x, node_y);
        for(TreeNode<T> children : root.getChildren()){
          preOrder(children, node_x, node_y);
        }
      }else{
        done = true;
      }
    }
    return list;
  }*/

    public TreeNode<T> getCommonAncestor(TreeNode<T> node_x, TreeNode<T> node_y){ //checked
        ArrayList<TreeNode<T>> list = new ArrayList<>();
        boolean done = false;

        while(node_x != null){
            list.add(node_x);
            node_x = node_x.getParent();
        }

        while(node_y != null){
            if(list.contains(node_y)){
                return node_y;
            }
            node_y = node_y.getParent();
        }
        return null;
    }
}
