package Worms;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Worm extends GameObject {

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
	private static float speed = 0.5f;
	private float x, y;
	private int[] duration = { 200, 200 };
	private Animation movingleft, movingright, wormanim;
	private Shape boundingBox;
	private String name;
	
	
	public Worm(float x, float y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		try {

			Image[] walkRight = { new Image("res/world/wormright.png"),
					new Image("res/world/wormright.png") };
			Image[] walkLeft = { new Image("res/world/wormleft.png"),
					new Image("res/world/wormleft.png") };

			movingleft = new Animation(walkLeft, duration, false);
			movingright = new Animation(walkRight, duration, false);
			wormanim = movingright;
			
			boundingBox = new Rectangle(x, y, 40f, 60f);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	public Shape getBoundingBox() {
		  return this.boundingBox;
		}

	public void update(float delta, char key) {
		if (key == 'r') {
			wormanim = movingright;
			x += speed * delta;
			boundingBox.setX(x);
			if (x > 800) {
				x = -49;
				boundingBox.setX(x);
			}
		}
		if (key == 'l') {
			wormanim = movingleft;
			x -= speed * delta;
			boundingBox.setX(x);
			if (x < -49) {
				x = 800;
				boundingBox.setX(x);
			}
		}
		if (key == 'u') {
			y -= speed * delta;
			boundingBox.setY(y);
			if (y < -56) {
				y = 600;
				boundingBox.setY(y);
			}
		}
		if (key == 'd') {
			y += speed * delta;
			boundingBox.setY(y);
			if (y > 600) {
				y = -56;
				boundingBox.setY(y);
			}
		}
	}

	public void render(Graphics g) {
		g.drawAnimation(wormanim, x, y);
	}
	

	public float getX() {
		return x;
	}
	public boolean isDirectionRight() {
		return (wormanim == movingright);
	}

	public float getY() {
		return y;
	}
	public int objectId(){
		return player;
	}
	public boolean isEnemy(Worm w){
		if(w.name != this.name){
			return true;
		}
		return false;
		
	}
}
