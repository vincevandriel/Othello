package Controller;

import java.util.ArrayList;

public class Minimax<T>{

    public void Minimax(int[][] board){
        Rules rules = new Rules();
        int depth = 5;
    }

    public BoardController CurrentState(){          //wherever the current state of the game is stored

    }

    public TreeNode<T> node(TreeNode<T> parent, int depth){
        if(depth>0){
            depth--;
        }
        int nextTurn;

        return parent; //this is wrong change it
    }
}

//need to create a copy of the game