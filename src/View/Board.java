package View;

import Model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import static java.lang.Math.min;

public class Board extends JPanel{
    private static Image background = null;
    private static Image[] tileImages = null;
    private Collection<Tile> tiles;
    private ArrayList<Consumer<Dimension>> listener = new ArrayList<>();
    private boolean tenxten = Window.isTenxten();
    private static int blockSize;
    public Board(Dimension size){
        super();
        if(background == null){
            try{
                if(tenxten) {
                    background = ImageIO.read(new File("assets/Othello_Board_10x10.png"));
                    blockSize = 10;
                } else {
                    background = ImageIO.read(new File("assets/Othello_Board.png"));
                    blockSize = 8;
                }
            } catch (IOException e){
                System.out.println("Board loading failed...");
            }
        }
        if(tileImages == null){
            tileImages = new Image[2];
            try{
                tileImages[0] = ImageIO.read(new File("assets/WhiteTile.png"));
                tileImages[1] = ImageIO.read(new File("assets/BlackTile.png"));
            } catch (IOException e){
                System.out.println("Tile loading failed...");
            }
        }
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setSize(size);
        this.tiles = new ArrayList<>();
        addMouseListener(new Listener());
    }

    public int getBlockSize(){
        return blockSize;
    }

    @Override
    public void paint(Graphics g){
        Dimension size = getSize();
        int smaller = min(size.width, size.height);
        size.height = smaller;
        size.width = smaller;
        g.drawImage(background, 0,0, size.width,size.height,null);

        for(Tile tile : this.tiles){
            this.drawTile(tile, g);
        }
    }

    public Dimension getBoardSize(){
        Dimension size = new Dimension(getSize());
        int smaller = min(size.width, size.height);
        size.height = smaller;
        size.width = smaller;
        return size;
    }

    public void setTiles(Collection<Tile> tiles){
        this.tiles = tiles;
    }

    public Collection<Tile> getTiles(){
        return tiles;
    }

    public void addTile(Tile tile){
        tiles.add(tile);
    }

    public void addTiles(ArrayList<Tile> tileCollection){
        tiles.clear();
        for(int i = 0; i<tileCollection.size(); i++){
            tiles.add(tileCollection.get(i));
        }
        repaint();
    }

    public void addBoardClickEventListener(Consumer<Dimension> listener){
        this.listener.add(listener);
    }
    private void drawTile(Tile tile, Graphics g){
        Dimension pos = translateToScreenPosition(tile.position);
        Dimension boardSize = getBoardSize();
        Dimension tileSize = new Dimension(boardSize.width/getBlockSize(), boardSize.height/getBlockSize());
        g.drawImage(tileImages[tile.tileId], pos.width,pos.height, tileSize.width, tileSize.height, null);
    }
    private Dimension translateToScreenPosition(Dimension boardPosition){
        boardPosition = new Dimension(boardPosition);
        boardPosition.width = (int) (((double) boardPosition.width)/blockSize * getBoardSize().width);
        boardPosition.height = (int) (((double) boardPosition.height)/blockSize * getBoardSize().height);
        return boardPosition;
    }
    private Dimension translateToBoardPosition(int x, int y){
        int board_x =(int) ((((double) x)/getBoardSize().width ) * blockSize);
        int board_y = (int) ((((double) y)/getBoardSize().height) * blockSize);
        if(board_x >= blockSize || board_y >= blockSize){
            return null;
        } else {
            return new Dimension(board_x, board_y);
        }
    }

    private class Listener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            Dimension board_pos = translateToBoardPosition(e.getX(), e.getY());
            System.out.println(board_pos.width +", "+board_pos.height);
            if (listener != null && board_pos != null) {
                for(Consumer<Dimension> c : listener) {
                    c.accept(board_pos);
                }
            }
        }
    }
}
