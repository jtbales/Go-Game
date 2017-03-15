
public class Player {
	playerColors color;
	playerTypes type;
	diffLevels difficulty;
	
	static enum playerColors {
		BLACK, WHITE
	}
	static enum playerTypes {
		HUMAN, COMPTER
	}
	static enum diffLevels {
		EASY, NORMAL
	}
	
	Player() {
		color = playerColors.BLACK;
		type = playerTypes.HUMAN;
		difficulty = diffLevels.NORMAL;
	}

	public playerColors getColor() {
		return color;
	}

	public void setColor(playerColors color) {
		this.color = color;
	}

	public playerTypes getType() {
		return type;
	}

	public void setType(playerTypes type) {
		this.type = type;
	}

	public diffLevels getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(diffLevels difficulty) {
		this.difficulty = difficulty;
	}
	
}
