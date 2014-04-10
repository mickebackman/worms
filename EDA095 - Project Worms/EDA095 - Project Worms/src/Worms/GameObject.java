package Worms;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject {
	public static final int player = 0;
	public static final int missile = 1;
	private Shape boundingBox;

	
	public void update(float delta, char key) {}
	public void update(float delta) {}
	
	public abstract void render(Graphics g);
	public abstract float getX(); 
	public abstract float getY();
	public abstract boolean isDirectionRight();
	public abstract int objectId();
	
	
	public Shape getBoundingBox() {
	  return this.boundingBox;
	}
	
	public boolean intersects(GameObject gameobject) {
	    if (this.getBoundingBox() == null) {
	        return false;
	    }
	    return this.getBoundingBox().intersects(gameobject.getBoundingBox());
	}
	
	
	
}
