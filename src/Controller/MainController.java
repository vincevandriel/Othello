package Controller;

import View.Board;
import View.Window;
import Model.Player;

import java.awt.*;

public class MainController {
    private static Dimension DEFAULT_BOARD_SIZE = new Dimension(250,250);
    public void start(Window window){
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Board board = new Board(DEFAULT_BOARD_SIZE);
        window.displayBoard(board);
        window.setVisible(true);
        BoardController boardController = new BoardController();
        boardController.start(board,player1,player2);
    }
}
