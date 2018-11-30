package Controller;

import java.awt.*;

public class dimensionConversion {
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

    public Dimension mouseToSquare(Dimension coords, Dimension size){
        coords = squareCoordinates(boardCoordinates(coords,size));
        return coords;

    }
}
