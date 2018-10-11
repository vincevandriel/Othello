package Controller;

import View.Board;
import View.Window;
import View.Menu;
import java.awt.*;

public class MainController {


    public void start(Window window){

        Menu menu = new Menu(window);
        window.displayMenu(menu);
        window.setVisible(true);



        //


    }

//    public void start(Window window1, Window window2) {
//        Board board = new Board(DEFAULT_BOARD_SIZE);
//        Menu menu = new Menu();
//
//        window1.displayMenu(menu);
//        window2.displayBoard(board);
//
//        window1.setVisible(true);
//        window2.setVisible(true);
//
//        BoardController boardController = new BoardController();
//        boardController.start(board, menu);
//
//
//    }

}
