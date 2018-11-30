package Controller;

import View.Board;
import View.Window;
import Model.*;

import java.awt.*;

public class MainController {
    private static Dimension DEFAULT_BOARD_SIZE = new Dimension(250,250);
    public void start(Window window){
        Human P1 = new Human(1);
        AlphaBeta P2 = new AlphaBeta();
        Board board = new Board(DEFAULT_BOARD_SIZE);
        window.displayBoard(board);
        window.setVisible(true);
        BoardController boardController = new BoardController();
        boardController.start(board,P1,P2);
    }
}
