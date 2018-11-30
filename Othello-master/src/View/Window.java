package View;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;

    public Window(){
        this.frame = new JFrame("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayMenu(Menu menu){
        frame.setLayout(new BorderLayout());
        frame.add(menu, BorderLayout.SOUTH);
        frame.pack();
     }

    public void displayBoard(Board board){
        frame.setLayout(new BorderLayout());
        Dimension size = new Dimension(477,500);
        frame.setPreferredSize(size);
        frame.add(board);
        frame.pack();
    }

    public void displayBoth(Menu menu, Board board){
            frame.setLayout(new BorderLayout());
            Dimension size = new Dimension(477,500);
            frame.setPreferredSize(size);
             frame.add(board, BorderLayout.CENTER);
            frame.add(menu, BorderLayout.NORTH);
            frame.pack();
         }
         
    public void setVisible(boolean visible){
        this.frame.setVisible(visible);
    }

}
