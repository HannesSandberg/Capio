package physics;

import java.util.ArrayList;

import Level.tile.Tile;

public abstract class BoundingShape {
	 
    public boolean checkCollision(BoundingShape by){
        if(by instanceof AABoundingRect)
            return checkCollision((AABoundingRect) by);
        return false;
    }
 
    public abstract boolean checkCollision(AABoundingRect box);
 
    public abstract void updatePosition(float newX, float newY);
 
    public abstract void movePosition(float x, float y);
 
    public abstract ArrayList<Tile> getTilesOccupying(Tile[][] tiles);
 
 
}