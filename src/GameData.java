import java.util.LinkedList;

public class GameData {
	int scoreBlack;
	int scoreWhite;
	private Player currentPlayer;
//	private int compSpeed; // in seconds
	private LinkedList<PossibleMove> possibleMoves = new LinkedList<PossibleMove>();
	private Player player1;
	private Player player2;
	boolean firstCompMove;
	
	GameData(Player player1, Player player2) {
		scoreBlack = 2;
		scoreWhite = 2;
//		compSpeed = 2;
		this.player1 = player1;
		this.player2 = player2;
		//Set to the player that is black
		if (this.player1.getColor() == Player.playerColors.BLACK)
			currentPlayer = this.player1;
		else 
			currentPlayer = this.player2;
		firstCompMove = true;
	}
	
	public void changeCurrentPlayer() {
		if (currentPlayer == player1)
			currentPlayer = player2;
		else if (currentPlayer == player2)
			currentPlayer = player1;
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

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
//	public void setCompSpeed(int seconds) {
//		compSpeed = Math.abs(seconds);
//	}
//	
//	public int getCompSpeed() {
//		return compSpeed;
//	}
	
	public LinkedList<PossibleMove> getPossibleMoves() {
		return possibleMoves;
	}
	public void addPossibleMove(PossibleMove possibleMove) {
		possibleMoves.add(possibleMove);
	}
	public void clearPossibleMoves() {
		possibleMoves.clear();
	}
}











