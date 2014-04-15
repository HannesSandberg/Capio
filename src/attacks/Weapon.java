package attacks;

import org.newdawn.slick.Image;

import character.Character;
import Level.tile.LevelObject;

public abstract class Weapon extends LevelObject {
	protected boolean moving = false;
	protected float accelerationSpeed = 1;
	protected float decelerationSpeed = 1f;
	protected float maximumSpeed = 1f;
	private float maxLength = 100;
	private float maxLengthCounter = 0;
	private boolean activated = false;
	private float damage = 10;
	protected Image sprite;
	
	public Weapon(float x, float y) {
		super(x, y);
	}

	
	
	
	
	
	public void hitt(Character target, Character hitter ) {
	
		target.lowerHp(damage,hitter);
	
	}
	public abstract void stop();
	public abstract void activate();
	public boolean isActivated() {
		return activated;
	}
	public void render(float offset_x, float offset_y) {
		if (activated) {
			sprite.draw(x - 2 - offset_x, y - 2 - offset_y);
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

}
