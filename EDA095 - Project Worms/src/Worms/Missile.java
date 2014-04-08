package Worms;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Missile extends GameObject {

	/*
	 * First, listen for a space bar press. When this happens, create a new
	 * "bullet" object.
	 * 
	 * Give this object a movement direction and speed, and then gradually move
	 * it in that direction.
	 * 
	 * While moving it, also detect if it crosses path with an enemy. If it
	 * does, then remove the bullet and kill the enemy.
	 */
	public static float speed = 1000.0f;
	private Vector2f pos;
	private Image image;
	private char direction;

	public Missile(float x, float y, char direction) {
		pos = new Vector2f(x, y);
		this.direction = direction;
		try {
			image = new Image("res/world/missile.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void update(float delta) {
		if (direction == 'r') {
			pos.set((pos.getX() + (speed * delta)), pos.getY());
		} else if (direction == 'l') {
			pos.set((pos.getX() - (speed * delta)), pos.getY());
		}
	}

	public void render(Graphics g) {
		g.drawImage(image, pos.getX(), pos.getY());
	}
	public float getX(){
		return pos.getX();
	}
	public float getY(){
		return pos.getY();
	}
}
