package Controller;

import View.Board;
import View.Window;
import Model.Player;

import java.awt.*;

public class MainController {
    private static Dimension DEFAULT_BOARD_SIZE = new Dimension(250,250);
    public void start(Window window){
        Player P1 = new Player(1, null);
        Player P2 = new Player(true, 2, P1);
        Board board = new Board(DEFAULT_BOARD_SIZE);
        window.displayBoard(board);
        window.setVisible(true);
        BoardController boardController = new BoardController();
        boardController.start(board,P1,P2);
    }
}
