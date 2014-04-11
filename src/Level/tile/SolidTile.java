package Level.tile;

import physics.AABoundingRect;

public class SolidTile extends Tile {
 
    public SolidTile(int x, int y) {
        super(x, y);
        boundingShape = new AABoundingRect(x*32,y*18,32,32);
    }
 
}