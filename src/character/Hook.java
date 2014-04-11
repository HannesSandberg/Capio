package character;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import physics.AABoundingRect;
import physics.BoundingShape;
import Level.tile.LevelObject;

public class Hook extends LevelObject{
//	private boolean activated;
	    
	    protected Image sprite;
	   
	    protected boolean                   moving = false;
	    protected float                     accelerationSpeed = 1;
	    protected float                     decelerationSpeed = 1f;
	    protected float                     maximumSpeed = 1f;
	    private boolean                     expanding = false; 
	    private float                       maxLength= 1000;
	    private float                       maxLengthCounter= 0;
	    private boolean 				 	activated = false;
	    private Character 					character=null;
	   
	public Hook() throws SlickException {
		super(0, 0);
		sprite = new Image("data/img/character/Bone_Hook.png");
					
	}
	public boolean isActivated() {
	
		return activated;
	}
	public float getVelocitySpeed() {
		
		return decelerationSpeed;
	}
	public void returnToPlayer(Character ch) {
		this.character=ch;
		expanding= false;
	}
	public boolean isExpanding() {
		if(maxLengthCounter>maxLength){
			expanding=false;
		}
		return expanding;
	}
	public void activateHook(double alphaToMuse, float x, float y) {
		expanding= true;
		activated = true;
		super.x = x;
		super.y = y;
		super.boundingShape = new AABoundingRect(x, y, 12, 12);
		
		//Calculate the start velocity. 
		float newVelocityX = decelerationSpeed * (float) Math.cos(alphaToMuse);
		setXVelocity(newVelocityX);
		float newVelocityY = decelerationSpeed * (float) Math.sin(alphaToMuse);
		setYVelocity(newVelocityY);
		
	}
		@Override
	   public void setX(float f){
		
			maxLengthCounter++;
		    x = f;
	        updateBoundingShape();
	  
	    }
		@Override
	    public void setY(float f){
			maxLengthCounter++;
			y = f;
	        updateBoundingShape();
	      
	    }
		public Character getHookedPlayer() {
			return character;
		}
		public void stop() {
			
			super.boundingShape=null;
			expanding= false;
			activated = false;
		    maxLengthCounter = 0;
			sprite.draw(1,1);
			if(character != null){
			character.setXVelocity(0);
			character.setYVelocity(0);
			}
			character=null;
		}
		
		public void render(){
				
			if(activated){
			 sprite.draw(x-2,y-2);
			}
		}
	
	

}
