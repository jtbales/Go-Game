import java.awt.Image;

import javax.swing.ImageIcon;

public class BoardIcons {
	ImageIcon blankImage,
	blackImage,
	whiteImage,
	redImage,
	yellowImage,
	shadowImage;
	
	BoardIcons(){
		blankImage = new ImageIcon(this.getClass().getResource("teal.png"));
		blackImage = new ImageIcon(this.getClass().getResource("blackDisc.png"));
		whiteImage = new ImageIcon(this.getClass().getResource("whiteDisc.png"));
		redImage = new ImageIcon(this.getClass().getResource("red.png"));
		yellowImage = new ImageIcon(this.getClass().getResource("yellow.png"));
		shadowImage = new ImageIcon(this.getClass().getResource("shadowDisc.png"));
	}
	
	BoardIcons(ImageIcon blank, ImageIcon black, ImageIcon white, ImageIcon red, ImageIcon yellow, ImageIcon shadow) {
		blankImage = blank;
		blackImage = black;
		whiteImage = white;
		redImage = red;
		yellowImage = yellow;
		shadowImage = shadow;
	}
	
	public static ImageIcon ConvertSize(ImageIcon imgIcon, int width, int height) {
		Image image = imgIcon.getImage(); 
		Image newImg = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);   
		return new ImageIcon(newImg);
	}
	
	public BoardIcons ConvertAllSizes(int width, int height) {
		ImageIcon blank = BoardIcons.ConvertSize(blankImage, width, height);
		ImageIcon black = BoardIcons.ConvertSize(blackImage, width, height);
		ImageIcon white = BoardIcons.ConvertSize(whiteImage, width, height);
		ImageIcon red = BoardIcons.ConvertSize(redImage, width, height);
		ImageIcon yellow = BoardIcons.ConvertSize(yellowImage, width, height);
		ImageIcon shadow = BoardIcons.ConvertSize(shadowImage, width, height);
		return new BoardIcons(blank, black, white, red, yellow, shadow);
	}
}
