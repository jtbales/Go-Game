
public class PossibleMove {
	private int row;
	private int column;
	private int flipCount;
	
	PossibleMove() {}
	
	PossibleMove (int row, int column, int flipCount) {
		this.row = row;
		this.column = column;
		this.flipCount = flipCount;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getFlipCount() {
		return flipCount;
	}

	public void setFlipCount(int flipCount) {
		this.flipCount = flipCount;
	}
}
