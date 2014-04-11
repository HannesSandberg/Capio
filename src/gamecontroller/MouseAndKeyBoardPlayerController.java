package gamecontroller;
 

 
import gamecontroller.PlayerController;

import org.newdawn.slick.Input;

import character.Player;
 
public class MouseAndKeyBoardPlayerController extends PlayerController {
 
 
    	 
        public MouseAndKeyBoardPlayerController(Player player) {
            super(player);
        }
     
        public void handleInput(Input i, int delta) {
            //handle any input from the keyboard
            handleKeyboardInput(i,delta);
            handleMouseInput(i, delta);
        }
        private void handleMouseInput(Input i, int delta){
        	if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
        		player.activateHook((i.getMouseX()), i.getMouseY());
        	}
        }
        private void handleKeyboardInput(Input i, int delta){
            //we can both use the WASD or arrow keys to move around, obviously we can't move both left and right simultaneously
            if(i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_LEFT)){
                player.moveLeft(delta);
                player.setMoving(true);
            }else if(i.isKeyDown(Input.KEY_D) || i.isKeyDown(Input.KEY_RIGHT)){
                player.moveRight(delta);
                player.setMoving(true);
            }
            else{
            	player.setMoving(false);
            }

            if(i.isKeyDown(Input.KEY_W) || i.isKeyDown(Input.KEY_UP)){
                player.moveUp(delta);
                player.setMoving(true);}
            else if(i.isKeyDown(Input.KEY_S) || i.isKeyDown(Input.KEY_DOWN)){
                player.moveDown(delta);
                player.setMoving(true);}

            if(!(player.isMoving())){
                //we dont move if we don't press left or right, this will have the effect that our player decelerates
//                player.setMoving(false);
                player.setXVelocity(0);
            	player.setYVelocity(0);
            }

        }
     
    }
 
