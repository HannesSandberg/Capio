package Level.tile;

import physics.AABoundingRect;

public class HookableTile extends Tile {
	 public HookableTile(int x, int y) {
	        super(x, y);
	        boundingShape = new AABoundingRect(x*32,y*32,32,32);
	    }
}
