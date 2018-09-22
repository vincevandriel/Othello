package Controller;

import View.Board;
import View.Window;

import java.awt.*;

public class MainController {
    private static Dimension DEFAULT_BOARD_SIZE = new Dimension(250,250);
    public void start(Window window){
        Board board = new Board(DEFAULT_BOARD_SIZE);
        window.displayBoard(board);
        window.setVisible(true);
        BoardController boardController = new BoardController();
        boardController.start(board);
    }
}
