import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardSquareButton extends JButton {
//	byte state; //0 is black, 1 is black, 2 is white
	private BoardIcons icons;
	static enum states {
		BLANK, 
		BLACK_DISC, 
		WHITE_DISC, 
		RED, //Invalid choice 
		YELLOW, //Last move
		SHADOW //Possible move
	}
	private states state;
	private int row, column;
	
	BoardSquareButton(BoardIcons icons, int row, int column) {
		this.icons = icons;
		setState(states.BLANK);
		this.row = row;
		this.column = column;
	}
	
	public void setState(states newState) {
		switch (newState)
		{
			case BLANK: 
				this.setIcon(icons.blankImage);
				state = states.BLANK;
				break;
			case BLACK_DISC:
				this.setEnabled(false);
				this.setDisabledIcon(icons.blackImage);
				state = states.BLACK_DISC;
				break;
			case WHITE_DISC:
				this.setEnabled(false);
				this.setDisabledIcon(icons.whiteImage);
				state = states.WHITE_DISC;
				break;
			case RED:
				this.setIcon(icons.redImage);
				state = states.RED;
				break;
			case YELLOW:
				this.setIcon(icons.yellowImage);
				state = states.YELLOW;
				break;
			case SHADOW:
				this.setIcon(icons.shadowImage);
				state = states.SHADOW;
				break;
			default:
				break;
		}
	}
	
	public states getState() {
		return state;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}
