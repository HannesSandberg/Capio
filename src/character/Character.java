package character;
 
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Level.tile.LevelObject;
import physics.BoundingShape;
 
public abstract class Character  extends LevelObject{
 
    
    protected Image sprite;
 
    protected boolean                   moving = false;
    protected float                     accelerationSpeed = 1;
    protected float                     decelerationSpeed = 1;
    protected float                     maximumSpeed = 1;
 
    public Character(float x, float y) throws SlickException{
    	super(x,y);
        this.x = x;
        this.y = y;
        //in case we forget to set the image, we don't want the game to crash, but it still has to be obvious that something was forgotten
        sprite = new Image("data/img/placeholder_sprite.png");
    }
 
    public float getX(){
        return x;
    }
 
    public float getY(){
        return y;
    }
 
    public void render(){
        sprite.draw(x-2,y-2);
    }
    
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
 
    public void moveUp(int delta){
    	if (y_velocity < maximumSpeed){
    		y_velocity -= accelerationSpeed*delta;
    		if(y_velocity > maximumSpeed){
    			y_velocity = maximumSpeed;

    		}
    	}
    	moving = true;
    }
  
    public void moveDown(int delta){
    	if (y_velocity < maximumSpeed){
    		y_velocity += accelerationSpeed*delta;
    		if(y_velocity > maximumSpeed){
    			y_velocity = maximumSpeed;

    		}
    	}
    	moving =true;
    }
    public void moveLeft(int delta){
        //if we aren't already moving at maximum speed
        if(x_velocity > -maximumSpeed){
            //accelerate
            x_velocity -= accelerationSpeed*delta;
            if(x_velocity < -maximumSpeed){
                //and if we exceed maximum speed, set it to maximum speed
                x_velocity = -maximumSpeed;
            }
        }
        moving = true;
       // facing = Facing.LEFT;
    }
    
    public void moveRight(int delta){
        if(x_velocity < maximumSpeed){
            x_velocity += accelerationSpeed*delta;
            if(x_velocity > maximumSpeed){
                x_velocity = maximumSpeed;
            }
        }
       
        moving = true;
     //   facing = Facing.RIGHT;
    }
    public void setMoving(boolean b) {
		moving = b;
	}
    public boolean isMoving() {
		return moving;
	}
    public void decelerate(int delta) {
        
    }
    
}