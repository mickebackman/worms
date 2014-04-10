package Worms;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;



public class Worms extends StateBasedGame {
	public static final String gamename = "Worms";
	public static final int menu = 0; 
	public static final int play = 1; 
	
	
	public Worms(String gamename) {
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		
	
	}
	public static void main(String args[]) throws SlickException{
		AppGameContainer app;
		try {
			app = new AppGameContainer(new Worms(gamename));
			app.setDisplayMode(800, 600, false);
			app.start();
		}
		catch (SlickException e){
			e.printStackTrace();
		}
		
		
		
		
	}
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
		
	}
}

