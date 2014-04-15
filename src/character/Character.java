package character;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import Level.tile.LevelObject;
import physics.AABoundingRect;
import physics.BoundingShape;

public abstract class Character extends LevelObject {

	protected Image sprite;

	protected boolean moving = false;
	protected float accelerationSpeed = 1;
	protected float decelerationSpeed = 1;
	protected float maximumSpeed = 1;
	protected Hook hook = null;
	protected double alpha = 90;
	protected float offset_x;
	protected float offset_y;
	protected float currentHealth;
	protected float maxHealth = 40;
	protected MealyAttack mealyAttack = null;
	private Boolean dead;
	private Image deadsprite;
	private ArrayList<Sound> killingSound;
	private Sound hittSound;
	private int kills;
	private int killingSoundIndex;
	private boolean hooked = false;

	Character(float x, float y) throws SlickException {
		super(x, y);
		this.x = x;
		this.y = y;
		kills = 0;
		killingSoundIndex=0;
		// in case we forget to set the image, we don't want the game to crash,
		// but it still has to be obvious that something was forgotten
		sprite = new Image("data/img/placeholder_sprite.png");
		deadsprite = new Image("data/img/character/bloodspray.png");
		boundingShape = new AABoundingRect(x + 3, y, 26, 32);
		currentHealth = maxHealth;
		this.mealyAttack = new MealyAttack(1, 1);
		dead = false;
		killingSound = new ArrayList<Sound>();
		killingSound.add(new Sound("data/sound/firstblood.wav"));
		killingSound.add(new Sound("data/sound/Double_Kill.wav"));
		killingSound.add(new Sound("data/sound/triple_kill.wav"));
		killingSound.add(new Sound("data/sound/HolyShit.wav"));
		killingSound.add(new Sound("data/sound/GodLike.wav"));
		
		hittSound = new Sound("data/sound/body_impact_3_with_grunt_.wav");
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void render(float offset_x, float offset_y, Graphics g) {
		this.offset_x = offset_x;
		this.offset_y = offset_y;
		if(!dead){
		float healthWidth = 30;
		float healthHeight = 5;
		float healthX = x - offset_x;
		float healthY = y - 10 - offset_y;
		float healthGAWidth = ((float) currentHealth / (float) maxHealth)
				* (float) healthWidth;
		g.setColor(Color.red);
		g.fillRect(healthX, healthY, healthWidth, healthHeight);
		g.setColor(Color.green);
		g.fillRect(healthX, healthY, healthGAWidth, healthHeight);
		
		sprite.draw(x - 2 - offset_x, y - 2 - offset_y);
		// g.setColor(Color.black);
		// g.drawRect(x-2-offset_x, y-2-offset_y, maxHealth, 5);
		// g.setColor(Color.green);
		// g.drawRect(x-offset_x, y-10-offset_y, currentHealth, 5);
		//
		hook.render(offset_x, offset_y);
		mealyAttack.render(offset_x, offset_y);
		}
		else{
			deadsprite.draw(x - 20 - offset_x, y - 20 - offset_y);
			}
		if(killingSoundIndex < kills&&killingSoundIndex<killingSound.size()){
			killingSound.get(killingSoundIndex).play();
			killingSoundIndex++;
		}
		
		
	}

	public void updateBoundingShape() {
		
			boundingShape.updatePosition(x + 3, y);
		
		
	}

	public void moveUp(int delta) {
		if (y_velocity < maximumSpeed) {
			y_velocity -= accelerationSpeed * delta;
			if (y_velocity > maximumSpeed) {
				y_velocity = maximumSpeed;

			}
		}
		moving = true;
	}

	public void moveDown(int delta) {
		if (y_velocity < maximumSpeed) {
			y_velocity += accelerationSpeed * delta;
			if (y_velocity > maximumSpeed) {
				y_velocity = maximumSpeed;

			}
		}
		moving = true;
	}

	public void moveLeft(int delta) {
		// if we aren't already moving at maximum speed
		if (x_velocity > -maximumSpeed) {
			// accelerate
			x_velocity -= accelerationSpeed * delta;
			if (x_velocity < -maximumSpeed) {
				// and if we exceed maximum speed, set it to maximum speed
				x_velocity = -maximumSpeed;
			}
		}
		moving = true;
		// facing = Facing.LEFT;
	}

	public void moveRight(int delta) {
		if (x_velocity < maximumSpeed) {
			x_velocity += accelerationSpeed * delta;
			if (x_velocity > maximumSpeed) {
				x_velocity = maximumSpeed;
			}
		}

		moving = true;
		// facing = Facing.RIGHT;
	}

	public void setMoving(boolean b) {
		moving = b;
	}

	public boolean isMoving() {
		return moving;
	}

	public void decelerate(int delta) {

	}

	public void lowerHp(float damage, Character hitter) {
		currentHealth = currentHealth - damage;
		if (currentHealth <= 0&&!dead) {
			dead = true;
			hitter.addKill();

			
		}
		hittSound.play();
	}
	public void addKill(){
		kills++;
	}
	public Hook getHook() {
		// TODO Auto-generated method stub
		return hook;
	}
	public boolean isHooked(){
		return hooked;
	}
	public void setHooked(boolean status){
		hooked =status;
	}
	public MealyAttack getMealyAttack() {
		// TODO Auto-generated method stub
		return mealyAttack;
	}
	public boolean isDead(){
		if(dead){
			this.setXVelocity(0);
			this.setYVelocity(0);
			boundingShape.updatePosition(1, 1);
			}
		return dead;
	}
	
}