import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Graphics {
    public static void initializeGraphics(){
        JFrame frame = new JFrame("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("assets\\Othello_Board.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Gets coordinates of mouse click in the frame (changes with resizing)
                int x=e.getX();
                int y=e.getY();

                //Gets total frame size and adjusts coordinate system to work with the top-left of the board being (0,0) and bottom-right being (400,400)
                Dimension size = frame.getContentPane().getSize();
                int width = x - ((size.width/2)-192);
                int height = y - ((size.height/2)-169);

                //Finally, converts pixels on the board to a coordinate system based on the squares, top-left being (1,1), bottom-right being (8,8)
                double squareX = Math.ceil(width/50.0);
                double squareY = Math.ceil(height/50.0);
                x = (int)squareX;
                y = (int)squareY;
                System.out.println(x+","+y);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
