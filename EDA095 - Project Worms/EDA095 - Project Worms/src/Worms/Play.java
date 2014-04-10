package Worms;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/// PROBLEM: arraylisten växer som satan när vi gör missiler.
/// kanske göra en worm som är ett object och jobba direkt på det istället för att ha den i objectlist - ha en egen lista?

public class Play extends BasicGameState {
	Image back;
	boolean quit = false;
	boolean win = false;
	ArrayList<GameObject> objectlist;
	ArrayList<Worm> playerlist;

	public Play(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// load all fonts, graphics, sounds, etc

		back = new Image("res/world/world.png");
		objectlist = new ArrayList<GameObject>();
		playerlist = new ArrayList<Worm>();
//		objectlist.add(new Worm(5, 5));
//		objectlist.add(new Worm(300, 300));
		
		playerlist.add(new Worm(5,5,"A"));
		playerlist.add(new Worm(300,300,"B"));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// draw all the graphic

		back.draw(0, 0);

		for (GameObject objects : objectlist) {
			objects.render(g);
		}
		for (Worm players : playerlist) {
			players.render(g);
		}

		// quit
		if (quit) {
			g.drawString("Resume (R)", 400, 200);
			g.drawString("Main Menu (M)", 400, 250);
			g.drawString("Quit (Q)", 400, 300);
			if (!quit) {
				g.clear();
			}
		}
		if (win) {
			g.drawString("WINNER", 400, 400);
		}
		

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// game logic (AI, user input)
		for (GameObject objects : objectlist) {
			objects.update(delta * 1e-3f);
			
			for (int j = 0; j < playerlist.size(); j++){
				if (objects.intersects(playerlist.get(j))){
					//Worm dead = playerlist.get(j); //Masken som dog :D
					playerlist.remove(j);
					
					System.out.println("Kollision");
					win = true;
				}
			}
			if ( objects.getX() > 900 || objects.getY() >700 ) {
				objects = null; // ???
			}
		}
		
//		for (GameObject objects : objectlist) {
//			objects.update(delta * 1e-3f);

			// float xpos = objects.getX(); --- GAMMAL COL DETECT
			// float ypos = objects.getY();
			// if (((xpos == x + 10f) || (xpos == x2 + 10f))
			// && ((ypos >= y && ypos <= (y + 30)) || (ypos >= y2 && ypos <= (y2
			// + 30)))) {
			// win = true;

			// }
//		}

		// player 1:
		Input userinput = gc.getInput();
		if ((userinput.isKeyDown(Input.KEY_RIGHT))) {
			playerlist.get(0).update(delta, 'r');
		}
		if ((userinput.isKeyDown(Input.KEY_LEFT))) {
			playerlist.get(0).update(delta, 'l');
		}

		if ((userinput.isKeyDown(Input.KEY_DOWN))) {
			playerlist.get(0).update(delta, 'd');
		}

		if ((userinput.isKeyDown(Input.KEY_UP))) {
			playerlist.get(0).update(delta, 'u');
		}

		// Missil:
		// collision detect samtidigt
		if ((userinput.isKeyDown(Input.KEY_SPACE))) {
			if (playerlist.get(0).isDirectionRight()) {
				objectlist.add(new Missile(playerlist.get(0).getX()+40, playerlist
						.get(0).getY()+20, 'r'));
			} else if (!playerlist.get(0).isDirectionRight()) {
				objectlist.add(new Missile(playerlist.get(0).getX()-40, playerlist
						.get(0).getY()+20, 'l'));
			}
		}

		// player2
		if ((userinput.isKeyDown(Input.KEY_D))) {
			playerlist.get(1).update(delta, 'r');
		}
		if ((userinput.isKeyDown(Input.KEY_A))) {
			playerlist.get(1).update(delta, 'l');
		}

		if ((userinput.isKeyDown(Input.KEY_S))) {
			playerlist.get(1).update(delta, 'd');
		}

		if ((userinput.isKeyDown(Input.KEY_W))) {
			playerlist.get(1).update(delta, 'u');
		}

		if ((userinput.isKeyDown(Input.KEY_X))) {
			// göra en tidskoll så man inte kan skjuta oändligt
			if (playerlist.get(1).isDirectionRight()) {
				objectlist.add(new Missile(playerlist.get(1).getX(), playerlist
						.get(1).getY(), 'r'));
			} else if (!objectlist.get(1).isDirectionRight()) {
				objectlist.add(new Missile(playerlist.get(1).getX(), playerlist
						.get(1).getY(), 'l'));
			}
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
