import Controller.BoardController;
import View.Board;
import View.Window;

import java.awt.*;

public class Main {

    private static Dimension DEFAULT_BOARD_SIZE = new Dimension(250,250);

    public static void main(String[] args){
        Board board = new Board(DEFAULT_BOARD_SIZE);
        Window window = new Window();
        window.displayBoard(board);
        window.setVisible(true);
        BoardController boardController = new BoardController();
        boardController.start(board);
    }
}
