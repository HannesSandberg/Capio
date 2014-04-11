package character;

import org.newdawn.slick.Image;

import physics.AABoundingRect;
import physics.BoundingShape;
import Level.tile.LevelObject;

public class Hook extends LevelObject{
//	private boolean activated;
	    
	    protected Image sprite;
	    private double alpha;
	    protected boolean                   moving = false;
	    protected float                     accelerationSpeed = 1;
	    protected float                     decelerationSpeed = 1;
	    protected float                     maximumSpeed = 1;
	public Hook(float px, float py, float mx, float my) {
		super(px, py);
//		activated = false;
		super.boundingShape = new AABoundingRect(x, y, 12, 12);
		if((mx-px)==0){
			if(my<py){
				alpha = 90;
			}
			else{
				alpha = 270;
			}
		
		}
		else if((my-px)==0){
			if(mx<px){
				alpha = 0;
			}
			else{
				alpha = 180;
			}
		}
		else {
			if(mx<px && my < py ){
				alpha =180 - Math.abs(Math.toDegrees(Math.atan((my-py)/(mx-px))));
			}
			else if(mx<px && my > py ){
				alpha = 180 + Math.abs(Math.toDegrees(Math.atan((my-py)/(mx-px))));
			}
			else if(mx>px && my > py ){
				alpha = 360 - Math.abs(Math.toDegrees(Math.atan((my-py)/(mx-px))));
			}
			else{
				alpha = Math.abs(Math.toDegrees(Math.atan((my-py)/(mx-px))));
			}
			
		}
					
	}
	
	
	
//	 public BoundingShape getBoundingShape(){
////	        if(activated){
//	        return boundingShape;
////	        }����
////	        return null;
//	    }
//	  public void activateHook(float x, float y){
//		  boundingShape.updatePosition(x,y);
//		  activated = true;
//	 }
	  
}
