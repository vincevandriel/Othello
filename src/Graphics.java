import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Graphics implements Core{

    //Creates JFrame and loads the background image

    public void initializeGraphics(){
        JFrame frame = new JFrame("Othello");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("assets\\Othello_Board.png")))));
        }catch (IOException e){
            System.out.println("Error in loading Othello_Board.png");
        }

        frame.pack();
        frame.setVisible(true);

        //MouseListener initialized and mouse click events begin here
        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                //Gets coordinates of mouse click in the frame & the size of the frame (changes with resizing)
                int x=e.getX();
                int y=e.getY();
                Dimension size = frame.getContentPane().getSize();
                Dimension mouseCoord = new Dimension(x,y);

                //Calculates mouse coordinates in term of the game board
                Dimension boardCoord = boardCoordinates(mouseCoord,size);
                Dimension squareCoord = squareCoordinates(boardCoordinates(mouseCoord,size));

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

    //Adjusts coordinate system in pixels to work with the top-left of the board being (0,0) and bottom-right being (400,400)

    private Dimension boardCoordinates(Dimension coords, Dimension size){

        int width = coords.width - ((size.width/2)-192);
        int height = coords.height - ((size.height/2)-169);
        Dimension boardCoor = new Dimension(width,height);
        return boardCoor;

    }

    //Converts pixels on the board to a coordinate system based on the squares, top-left being (1,1), bottom-right being (8,8)

    private Dimension squareCoordinates(Dimension coords){

        double squareX = Math.ceil(coords.width/50.0);
        double squareY = Math.ceil(coords.height/50.0);
        int x = (int)squareX;
        int y = (int)squareY;
        System.out.println(x+","+y);
        coords = new Dimension(x,y);
        return coords;
    }

    //Determines actual location of square in pixels in the jFrame

    private Dimension pixelCoordinates(Dimension coords){

        int locationX = coords.width + 192;
        int locationY = coords.height + 169;
        coords = new Dimension(locationX,locationY);
        return coords;
    }

}
