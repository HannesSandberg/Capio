package character;
 
//import game.enums.Facing;
//import game.physics.AABoundingRect;
import physics.AABoundingRect;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
 
public class Player extends Character {
	private Hook hook;
    public Player(float x, float y) throws SlickException{
        super(x,y);
        sprite = new Image("data/img/character/darthvader.png");
       // hook = new Hook(x, y);
    
    boundingShape = new AABoundingRect(x+3, y, 26, 32);
    
    accelerationSpeed = 0.001f;
    maximumSpeed = 1f;
//    maximumFallSpeed = 10f;
    decelerationSpeed = 10f;
    hook = null; //new Hook(x , y);
}
    public void updateBoundingShape(){
        boundingShape.updatePosition(x,y);
    }
	public void activateHook(float mouseX, float mouseY) {
		System.out.println("skapar hook");
		
		
	}
	
}