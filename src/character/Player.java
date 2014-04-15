package character;
 
//import game.enums.Facing;
//import game.physics.AABoundingRect;
import physics.AABoundingRect;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Game.Game;
 
public class Player extends Character {

    public Player(float x, float y) throws SlickException{
        super(x,y);
        sprite = new Image("data/img/character/Robot.png");
         hook = new Hook();
    
    boundingShape = new AABoundingRect(x+3, y, sprite.getWidth(), sprite.getHeight());
    
    accelerationSpeed = 0.001f;
    maximumSpeed = 1f;
//    maximumFallSpeed = 10f;
    decelerationSpeed = 10f;
  
}
    public void updateBoundingShape(){
        boundingShape.updatePosition(x,y);
    }
	public void activateHook() {
		hook.activateHook(alpha,x,y);
		}
	public void activateMealyAttack() {
		mealyAttack.activate(alpha,x,y);
		}
    public void setAlphaToMouse(float mouseX, float mouseY ){
  
		alpha = getAlpha(x- offset_x,y-offset_y,mouseX, mouseY);
		
	}
    private double getAlpha(float centerX, float centerY, float objX, float objY) {
    	double alpha = 0;
		if((objX-centerX)==0){
			if(objY<centerY){
				alpha = Math.PI/2;
			}
			else{
				alpha = Math.PI*(3/2);
			}
		
		}
		else if((objY-centerX)==0){
			if(objX<centerX){
				alpha = 0;
			}
			else{
				alpha = Math.PI;
			}
		}
		else {
			if(objX<centerX && objY < centerY ){
				alpha =Math.PI + Math.abs((Math.atan((objY-centerY)/(objX-centerX))));
			}
			else if(objX<centerX && objY > centerY ){
				alpha = Math.PI - Math.abs(Math.atan((objY-centerY)/(objX-centerX)));
			}
			else if(objX>centerX && objY > centerY ){
				alpha = Math.abs(Math.atan((objY-centerY)/(objX-centerX)));
			}
			else{
				alpha = 2*Math.PI - Math.abs(Math.atan((objY-centerY)/(objX-centerX)));
			}
		
		}
		return alpha;
	}
    
    
    
   
    
}