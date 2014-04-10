package Worms;
public class GameData {

	private static String winningPlayer;
	private static GameData gamedata;

	public static GameData getGameData() {
		if (gamedata == null) {
			gamedata = new GameData();
		}
		return gamedata;
	}

	private GameData() {
		GameData.winningPlayer = "";
	}
	public static void setWinner(String winningPlayer){
		GameData.winningPlayer = winningPlayer;
	}
	public static String getWinner(){
		return winningPlayer;
	}
}