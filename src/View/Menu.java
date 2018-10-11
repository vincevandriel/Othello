package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Controller.BoardController;
import Controller.MainController;

public class Menu extends JPanel {

    private boolean startClicked;
    private JButton start, exit;
    private int playerSelected1, playerSelected2;
    private static Dimension DEFAULT_BOARD_SIZE = new Dimension(250,250);

    public Menu(Window window) {






        // The block above before the while loop and startclicked true = menu not displayed, only board
        // The block beneath the while loop and startclicked true = menu on top of board, game started
        // The block beneath the while loop and startclicked false = nothing happens
        // The block above the while loop and startclicked - false = menu not displayed, only board

        //



        JPanel south = new JPanel(new FlowLayout());
        JPanel center = new JPanel();
        JPanel east = new JPanel();
        JPanel west = new JPanel();
        JPanel north = new JPanel();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        String[] players = {"Human", "Algorithm 1", "Algorithm 2"};
        JButton start = new JButton("Start");
        JButton exit = new JButton("Exit");

        JComboBox p1 = new JComboBox(players);
        JComboBox p2 = new JComboBox(players);

        south.add(p1);
        south.add(p2);
        south.add(start);
        south.add(exit);
        this.add(south, BorderLayout.SOUTH);



        ButtonHandler handler = new ButtonHandler();
        start.addActionListener(handler);
        startClicked = true;


        while(startClicked == false) {
            p1.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (e.getStateChange() == ItemEvent.SELECTED)
                                System.out.println(players[p1.getSelectedIndex()]);
                            playerSelected1 = p1.getSelectedIndex();

                        }
                    }
            );

            p2.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (e.getStateChange() == ItemEvent.SELECTED)
                                System.out.println(players[p2.getSelectedIndex()]);
                            playerSelected2 = p2.getSelectedIndex();


                        }
                    }
            );



        }



        Board board = new Board(DEFAULT_BOARD_SIZE);
        window.displayBoard(board);
        window.setVisible(true);

        BoardController boardController = new BoardController();
        boardController.start(board, playerSelected1, playerSelected2);


        // Call boardcontroller.start after while loop


    }

    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Hey you pressed me you son of a bitch");
            startClicked = true;






        }
    }

    public int getPlayerSelected(int player) {
        if (player == 1) {
            return playerSelected1;
        }
        if (player == 2) {
            return playerSelected2;
        }
        else {
            return 0;
        }
    }


}
