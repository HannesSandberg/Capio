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
	    private float                       maxLength= 2000;
	    private float                       maxLengthCounter= 0;
	    private boolean 				 	activated = false;
	    private Character 					character=null;
	    private float                       hookDamage = 10;
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
	public void returnToPlayer(Character target, Character hitter ) {
		this.character=target;
		target.lowerHp(hookDamage,hitter);
		target.setHooked(true);
		
	}
	public boolean isExpanding() {
		if(maxLengthCounter>maxLength||character!=null){
			return false;
		}
		return true;
	}
	public void activateHook(double alphaToMuse, float x, float y) {
		if(!activated){
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
			activated = false;
		    maxLengthCounter = 0;
			sprite.draw(1,1);
			if(character != null){
			character.setHooked(false);
			character=null;
			}
			
		}
		
		  public void render(float offset_x, float offset_y){
		    	 
              
	             
			if(activated){
				sprite.draw(x-2-offset_x, y-2-offset_y);
			}
		}
	
	

}
