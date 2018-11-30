package Model;

import java.awt.*;

public class Tile {
    public Dimension position;
    public int tileId;
    public Tile(Dimension position, int tileId){
        this.position = position;
        this.tileId = tileId;
    }
}
