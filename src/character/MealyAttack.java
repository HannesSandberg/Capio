package character;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import physics.AABoundingRect;
import Level.tile.LevelObject;

public class MealyAttack extends LevelObject {
	protected boolean moving = false;
	protected float accelerationSpeed = 1;
	protected float decelerationSpeed = 1f;
	protected float maximumSpeed = 1f;
	private float maxLength = 100;
	private float maxLengthCounter = 0;
	private boolean activated = false;
	private float damage = 2;
	protected Image sprite;
	private Sound swingSword;

	public MealyAttack(float x, float y) throws SlickException {
		super(x, y);
		sprite = new Image("data/img/character/sword.png");
		swingSword = new Sound("data/sound/remove_sword_from_sheath.wav");

	}

	public void hitt(Character target, Character hitter) {
		target.lowerHp(damage,hitter);
	}

	public boolean isActivated() {

		return activated;
	}

	public void activate(double alphaToMuse, float x, float y) {
		if(!activated){
		activated = true;
		super.x = x;
		super.y = y;
		super.boundingShape = new AABoundingRect(x, y, 12, 12);

		// Calculate the start velocity.
		float newVelocityX = decelerationSpeed * (float) Math.cos(alphaToMuse);
		setXVelocity(newVelocityX);
		float newVelocityY = decelerationSpeed * (float) Math.sin(alphaToMuse);
		setYVelocity(newVelocityY);
	
			swingSword.play();
		
		
		}

	}

	@Override
	public void setX(float f) {

		maxLengthCounter++;
		x = f;
		updateBoundingShape();

	}

	@Override
	public void setY(float f) {
		maxLengthCounter++;
		y = f;
		updateBoundingShape();

	}

	public void render(float offset_x, float offset_y) {
		if (activated) {
			sprite.draw(x - 2 - offset_x, y - 2 - offset_y);
		}
	}

	public void checkRange() {
		if (maxLengthCounter >= maxLength) {
			stop();
		}
	}

	public void stop() {

		super.boundingShape = null;
		activated = false;
		maxLengthCounter = 0;
		sprite.draw(1, 1);
		

	}
}
