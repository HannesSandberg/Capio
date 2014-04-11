package physics;

import java.util.ArrayList;

import Level.Level;
import Level.tile.LevelObject;
import Level.tile.Tile;
import character.Character;
import character.Hook;
public class Physics {
 
   // private final float gravity = 1f;
 

    public void handlePhysics(Level level, int delta){
    	handelHooks(level, delta);
        handleCharacters(level,delta);
    }

	private void handelHooks(Level level, int delta) {
		float newVelocityX = 0;
		float newVelocityY = 0;
		Character hookedPlayer = null;
		for (character.Character player : level.getCharacters()) {
			Hook hook = player.getHook();

			if (hook.isActivated()) {
				hookedPlayer = hook.getHookedPlayer();

				if (hookedPlayer == null) {
					for (character.Character ch : level.getCharacters()) {
						if (player != ch) {
							if (hook.getBoundingShape().checkCollision(
									ch.getBoundingShape())) {
								hook.returnToPlayer(ch);

							}
						}
					}
				}
				if (!hook.isExpanding()) {

					double alpha = getAlpha(hook.getX(), hook.getY(),
							player.getX(), player.getY());
					float velocitySpeed = hook.getVelocitySpeed();
					newVelocityX = velocitySpeed * (float) Math.cos(alpha);
					hook.setXVelocity(newVelocityX);

					newVelocityY = velocitySpeed * (float) Math.sin(alpha);
					hook.setYVelocity(newVelocityY);

				

				}
				hookedPlayer = hook.getHookedPlayer();
				if (hookedPlayer != null) {
					hookedPlayer.setXVelocity(newVelocityX);
					hookedPlayer.setYVelocity(newVelocityY);
				}
				handleGameObject(hook, level, delta);
				if (hook.getBoundingShape().checkCollision(
						player.getBoundingShape())
						&& !hook.isExpanding()) {
					hook.stop();
				}
			}
		}

	}

	private void handleCharacters(Level level, int delta){
        for(character.Character c : level.getCharacters()){
 
            //and now decelerate the character if he is not moving anymore
            if(!c.isMoving()){
                c.decelerate(delta);
            }
           
            handleGameObject(c,level,delta);
        }
    }
    
    
    private boolean checkCollision(LevelObject obj, Tile[][] mapTiles){
        //get only the tiles that matter
        ArrayList<Tile> tiles = obj.getBoundingShape().getTilesOccupying(mapTiles);
        for(Tile t : tiles){
            //if this tile has a bounding shape
            if(t.getBoundingShape() != null){
                if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
                    return true;
                }
            }
        }
        return false;
    }
 
   
    

    
    private void handleGameObject(LevelObject obj, Level level, int delta){
    
        //first update the onGround of the object
      //  obj.setOnGround(isOnGroud(obj,level.getTiles()));
 
        //now apply gravitational force if we are not on the ground or when we are about to jump
//        if(!obj.isOnGround() || obj.getYVelocity() < 0)
//            obj.applyGravity(gravity*delta);
//        else
//            obj.setYVelocity(0);
 
        //calculate how much we actually have to move
        float x_movement = obj.getXVelocity()*delta;
        float y_movement   = obj.getYVelocity()*delta;
    	
        //we have to calculate the step we have to take
        float step_y = 0;
        float step_x = 0;
 
        if(x_movement != 0){
        	
            step_y = Math.abs(y_movement)/Math.abs(x_movement);
            if(y_movement < 0)
                step_y = -step_y;
 
            if(x_movement > 0)
                step_x = 1;
            else
                step_x = -1;
 
            if((step_y > 1 || step_y < -1) && step_y != 0){
                step_x = Math.abs(step_x)/Math.abs(step_y);
                if(x_movement < 0)
                    step_x = -step_x;
                if(y_movement < 0)
                    step_y = -1;
                else
                    step_y = 1;
            }
        }else if(y_movement != 0){
        
        	
            step_x = Math.abs(x_movement)/Math.abs(y_movement);
            if(x_movement < 0)
                step_x = -step_x;
 
            if(y_movement > 0)
                step_y = 1;
            else
                step_y = -1;
 
            if((step_x > 1 || step_x < -1) && step_x != 0){
                step_y = Math.abs(step_y)/Math.abs(step_x);
                if(x_movement < 0)
                    step_y = -step_y;
                if(x_movement < 0)
                    step_x = -1;
                else
                    step_x = 1;
            }
        }
 
        //and then do little steps until we are done moving
        while(x_movement != 0 || y_movement != 0){
  
            //we first move in the x direction
            if(x_movement != 0){
                //when we do a step, we have to update the amount we have to move after this
                if((x_movement > 0 && x_movement < step_x) || (x_movement > step_x  && x_movement < 0)){
                    step_x = x_movement;
                    x_movement = 0;
                }else
                    x_movement -= step_x;
 
                //then we move the object one step
                obj.setX(obj.getX()+step_x);
 
                //if we collide with any of the bounding shapes of the tiles we have to revert to our original position
                if(checkCollision(obj,level.getTiles())){
                	
                   //undo our step, and set the velocity and amount we still have to move to 0, because we can't move in that direction
                    obj.setX(obj.getX()-step_x);
                    if(obj instanceof Hook){
                     Hook hook = (Hook) obj;
                    	obj.setXVelocity(hook.getXVelocity() * (-1));
                    }
                    else{
                    obj.setXVelocity(0);
                    }
                    x_movement = 0;
                }
 
            }
            //same thing for the vertical, or y movement
            if(y_movement != 0){
                if((y_movement > 0 && y_movement < step_y) || (y_movement > step_y  && y_movement < 0)){
                    step_y = y_movement;
                    y_movement = 0;
                }else
                    y_movement -= step_y;
 
                obj.setY(obj.getY()+step_y);
 
                
              
                if(checkCollision(obj,level.getTiles())){
                    obj.setY(obj.getY()-step_y);
                    if(obj instanceof Hook){
                        Hook hook = (Hook) obj;
                       	obj.setYVelocity(hook.getYVelocity() * (-1));
                       }
                       else{
                       obj.setYVelocity(0);
                       }
                    y_movement = 0;
                   
                }
            }
        }
    
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