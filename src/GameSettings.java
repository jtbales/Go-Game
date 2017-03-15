
public class GameSettings {
	static enum matchTypes {
		PvP, PvC, CvC
	}
	private matchTypes matchType;
	private int dimensions;
	static int MAX_DIMENSIONS = 12;
	static int MIN_DIMENSIONS = 4;
	
	private Player player1;
	private Player player2;
	
	GameSettings() { //Default Settings
		dimensions = 4;
		player1 = new Player();
//		player1.setDifficulty(Player.diffLevels.EASY); 
		player2 = new Player();
		matchType = matchTypes.PvC;
	}
	
	public matchTypes getMatchType() {
		return matchType;
	}
	public void setMatchType(matchTypes matchType) {
		this.matchType = matchType;
		if (matchType == matchTypes.PvC) {
			player1.setType(Player.playerTypes.HUMAN);
			player2.setType(Player.playerTypes.COMPTER);
		} 
		else if (matchType == matchTypes.PvP) {
			player1.setType(Player.playerTypes.HUMAN);
			player2.setType(Player.playerTypes.HUMAN);
		}
		else if (matchType == matchTypes.CvC) {
			player1.setType(Player.playerTypes.COMPTER);
			player2.setType(Player.playerTypes.COMPTER);
		}
	}
	
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public void setDimensions(int d) {
		if (d > MAX_DIMENSIONS)
			dimensions = MAX_DIMENSIONS;
		else if (d < MIN_DIMENSIONS)
			dimensions = MIN_DIMENSIONS;
		else 
			dimensions = d;
	}
	
	public int getDimensions() {
		return dimensions;
	}
	
	public void setBlackPlayer(Player player) {
		if (player == player1) {
			player1.color = Player.playerColors.BLACK;
			player2.color = Player.playerColors.WHITE;
		}
		else if (player == player2) {
			player2.color = Player.playerColors.BLACK;
			player1.color = Player.playerColors.WHITE;
		}
	}
	
	public Player getBlackPlayer() {
		if (player1.getColor() == Player.playerColors.BLACK)
			return player1;
		else 
			return player2;
	}
	public Player getWhitePlayer() {
		if (player1.getColor() == Player.playerColors.WHITE)
			return player1;
		else 
			return player2;
	}
	
}
