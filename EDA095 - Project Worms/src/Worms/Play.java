package Worms;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {
	Animation wormanim, wormanim2, movingleft, movingright, movingleft2, movingright2;
	Image back, worm, worm2;
	Image missile;
	int[] duration = {200, 200};
	
	float x = 500.0f;
	float y = 200.0f;
	float x2 = 200.0f;
	float y2 = 200.0f;
	float speed = 0.2f;
	boolean quit = false;
	boolean win = false;
	ArrayList<GameObject> objectlist;
//	float shiftX = x + 400;
//	float shiftY = y + 300;

	public Play(int state) {
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// load all fonts, graphics, sounds, etc
		quit = false;
		back = new Image("res/world/world.png");
		objectlist = new ArrayList<GameObject>();
//		worm = new Image("res/world/worm.png");
		missile = new Image("res/world/missile.png");
		//player 2
//		worm2 = new Image("res/world/worm.png");
		
		Image[] walkRight = {new Image("res/world/wormright.png"), new Image("res/world/wormright.png")};
		Image[] walkLeft = {new Image("res/world/wormleft.png"),new Image("res/world/wormleft.png")};
		
		
		movingleft = new Animation(walkLeft, duration, false);
		movingright = new Animation(walkRight, duration, false);
		
		
		wormanim = movingleft;
		wormanim2 = movingright;
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// draw all the graphic
		back.draw(0, 0);
		wormanim.draw(x, y);
		wormanim2.draw(x2, y2);
		
		for (GameObject objects : objectlist) {
			objects.render(g);
		}
		
		//quit 
		if(quit){
			g.drawString("Resume (R)", 400, 200);
			g.drawString("Main Menu (M)", 400, 250);
			g.drawString("Quit (Q)", 400, 300);
			if(!quit){
				g.clear();
			}
		}
		if(win){
			g.drawString("WINNER", 400,400);
		}
	}



	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// game logic (AI, user input)
		for (GameObject objects : objectlist) {
			objects.update(delta*1e-3f);
			float xpos = objects.getX();
			float ypos = objects.getY();
			if (( (xpos == x+10f) || (xpos == x2+10f)) && ( (ypos >= y && ypos <= (y+30)) || (ypos >= y2 && ypos <= (y2+30)))) {
			win = true;
			
			}
		}
		
		
		
		//player 1:
		Input userinput = gc.getInput();
		if ((userinput.isKeyDown(Input.KEY_RIGHT) && (x < 750))){
			wormanim = movingright;
			x += speed * delta;
		}
		if ((userinput.isKeyDown(Input.KEY_LEFT) && x >= 0)){
			wormanim = movingleft;
			x -= speed * delta;
		}
		
		if ((userinput.isKeyDown(Input.KEY_DOWN) && y < 540)){
			y += speed * delta;
		}
		
		if ((userinput.isKeyDown(Input.KEY_UP) && y >= 0)){
			y -= speed * delta;
		}
		
		//gör nytt missilobjekt - låt det röra sig i rätt riktning sen köra collision detect samtidigt
		if ((userinput.isKeyDown(Input.KEY_SPACE))) {
			if (wormanim == movingright) {
				objectlist.add(new Missile(x,y, 'r'));
			}
			else if (wormanim == movingleft) {
				objectlist.add(new Missile(x,y, 'l'));
			}
		}
			
			
		
		
		
		//player2 
		if ((userinput.isKeyDown(Input.KEY_D) && (x2 < 750))){
			wormanim2 = movingright;
			x2 += speed * delta;
		}
		if ((userinput.isKeyDown(Input.KEY_A) && x2 >= 0)){
			wormanim2 = movingleft;
			x2 -= speed * delta;
		}
		
		if ((userinput.isKeyDown(Input.KEY_S) && y2 < 540)){
			y2 += speed * delta;
		}
		
		if ((userinput.isKeyDown(Input.KEY_W) && y2 >= 0)){
			y2 -= speed * delta;
		}
		
		
		
		
		// QUIT MENU
		if (userinput.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
		}
		if (quit) {
			if (userinput.isKeyDown(Input.KEY_Q)) {
				System.exit(0);
			}
			if (userinput.isKeyDown(Input.KEY_R)) {
				quit = false;
			}
			if (userinput.isKeyDown(Input.KEY_Q)) {
				sbg.enterState(0);
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}
}
