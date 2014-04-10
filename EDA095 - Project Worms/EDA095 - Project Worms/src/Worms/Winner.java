package Worms;
import java.io.InputStream;
import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

public class Winner extends BasicGameState {

	Image back;
	Image exitgame;
	Image playnow;
	private TrueTypeFont font; 

	public Winner(int state) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// draw all the graphic
		g.drawImage(back, 0, 0);
		exitgame.draw(300, 300);
		playnow.draw(300, 200);
		
		
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("/res/world/font.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(50f); // set font size
			font = new TrueTypeFont(awtFont2, false);
			} catch (Exception e) {
			e.printStackTrace();
			}  
		
		
		GameData.getGameData();
		String name = GameData.getWinner();
		font.drawString(180,60, "The winner is", Color.black);
		font.drawString(380, 120, name, Color.black);

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// load all fonts, graphics, sounds, etc

		back = new Image("res/world/world.png");
		exitgame = new Image("res/world/exitgame.png");
		playnow = new Image("res/world/playnow.png");

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// game logic (AI, user input)
		Input userinput = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();

		// exit button
		if ((xpos > 300 && xpos < 511) && (ypos > 239 && ypos < 300)) {
			if (userinput.isMouseButtonDown(0)) {
				System.exit(0);
			}
		}
		// start button
		if ((xpos > 300 && xpos < 511) && (ypos > 339 && ypos < 400)) {
			if (userinput.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}

	}

	@Override
	public int getID() {
		return 2;
	}
}
