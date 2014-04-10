package Worms;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
	Image back;
	Image playnow; 
	Image exitgame;

	public Menu(int state) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// draw all the graphic
		g.drawImage(back, 0, 0);
		playnow.draw(300,200);
		exitgame.draw(300,300);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// load all fonts, graphics, sounds, etc

		back = new Image("res/world/world.png");
		playnow = new Image("res/world/playnow.png");
		exitgame = new Image("res/world/exitgame.png");

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// game logic (AI, user input)
		Input userinput = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		
		// start button
		if ((xpos > 300 && xpos < 511) && (ypos > 339 && ypos < 400)) {
			if (userinput.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		
		//exit button
		if ((xpos > 300 && xpos < 511) && (ypos > 239 && ypos < 300)) {
			if (userinput.isMouseButtonDown(0)) {
				System.exit(0);
			}
		}

	}

	@Override
	public int getID() {
		return 0;
	}
}
