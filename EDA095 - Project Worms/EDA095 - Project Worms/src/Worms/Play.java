package Worms;
import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Play extends BasicGameState {
	Image back;
	boolean quit = false;
	boolean win = false;
	ArrayList<GameObject> objectlist;
	ArrayList<Worm> playerlist;

	public Play(int state) {

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		playerlist.get(0).setPos(700f,300f);
		playerlist.get(1).setPos(100f, 300f);
	}
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// load all fonts, graphics, sounds, etc

		back = new Image("res/world/world.png");
		objectlist = new ArrayList<GameObject>();
		playerlist = new ArrayList<Worm>();

		playerlist.add(new Worm(700, 300, "A"));
		playerlist.add(new Worm(100, 300, "B"));
		playerlist.get(0).setDirection(false);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// draw all the graphic

		back.draw(0, 0);

		// Ritar upp spelare samt objekt.
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

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// game logic (AI, user input)

		// Collision detect
		for (GameObject objects : objectlist) {
			objects.update(delta * 1e-3f);

			for (int j = 0; j < playerlist.size(); j++) {

				if (objects.intersects(playerlist.get(j))) {
					GameData.getGameData();
					GameData.setWinner(playerlist.get(j).getName());
					sbg.enterState(2);
				}
			}
			if (objects.getX() > 900 || objects.getY() > 700) {
				objects = null;
			}
		}

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
		if ((userinput.isKeyDown(Input.KEY_SPACE))) {
			if (allowFire(playerlist.get(0))) {
				if (playerlist.get(0).isDirectionRight()) {
					playerlist.get(0).setLastFired();
					objectlist.add(new Missile(playerlist.get(0).getX() + 40,
							playerlist.get(0).getY() + 20, 'r'));
				} else if (!playerlist.get(0).isDirectionRight()) {
					playerlist.get(0).setLastFired();
					objectlist.add(new Missile(playerlist.get(0).getX() - 40,
							playerlist.get(0).getY() + 20, 'l'));
				}
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

		// missil för player2
		if ((userinput.isKeyDown(Input.KEY_X))) {
			if (allowFire(playerlist.get(1))) {
				if (playerlist.get(1).isDirectionRight()) {
					playerlist.get(1).setLastFired();
					objectlist.add(new Missile(playerlist.get(1).getX() + 40,
							playerlist.get(1).getY() + 20, 'r'));
				} else if (!playerlist.get(1).isDirectionRight()) {
					playerlist.get(1).setLastFired();
					objectlist.add(new Missile(playerlist.get(1).getX() - 40,
							playerlist.get(1).getY() + 20, 'l'));
				}
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

			if (userinput.isKeyDown(Input.KEY_M)) {
				sbg.enterState(0);
			}
		}
	}

	public boolean allowFire(Worm w) {
		long lastFired = w.getLastFired();
		long current = System.currentTimeMillis();

		if (current - lastFired >= 1000) {
			return true;
		}
		return false;
	}

	@Override
	public int getID() {
		return 1;
	}
}
