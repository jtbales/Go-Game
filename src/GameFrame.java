import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GameFrame extends JFrame {

	private JPanel contentPane;
	private GameSettings gameSettings;
	private int d; //dimensions
	private BoardSquareButton boardSquares[][] = new BoardSquareButton[GameSettings.MAX_DIMENSIONS][GameSettings.MAX_DIMENSIONS];
	private GameData gameData;
	private JLabel lblTurn;
	private JLabel lblLastMoveLoc;
	private JLabel lblBlackScore, lblWhiteScore;
//	JSlider sliderCompSpeed;

	public GameFrame() {
		new GameFrame(new GameSettings());
	}
	public GameFrame(GameSettings gs) {
		gameData = new GameData(gs.getPlayer1(), gs.getPlayer2());
		gameSettings = gs;
		d = gameSettings.getDimensions();
//		d = 6;
		int boardSpacing = 5;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// The panel to hold the game board grid
		JPanel panelBoard = new JPanel();
		panelBoard.setBounds(6, 97, 375, 375);
		contentPane.add(panelBoard);
		panelBoard.setLayout(new GridLayout(d, d, boardSpacing, boardSpacing));
		
		JLabel lblTitle = new JLabel("Game on!");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblTitle.setBounds(250, 6, 192, 22);
		contentPane.add(lblTitle);
		
		//Display showing who's turn it is
		lblTurn = new JLabel("Shows who's turn it is.");
		lblTurn.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblTurn.setBounds(31, 40, 287, 45);
		contentPane.add(lblTurn);
		displayCurrentPlayer();
		
		//Black score
		JLabel lblBlack = new JLabel("Black:");
		lblBlack.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBlack.setBounds(393, 97, 74, 29);
		contentPane.add(lblBlack);
		
		lblBlackScore = new JLabel("2");
		lblBlackScore.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblBlackScore.setBounds(466, 97, 86, 29);
		contentPane.add(lblBlackScore);
		
		//White score
		JLabel lblWhite = new JLabel("White:");
		lblWhite.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblWhite.setBounds(393, 138, 74, 29);
		contentPane.add(lblWhite);
		
		lblWhiteScore = new JLabel("2");
		lblWhiteScore.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblWhiteScore.setBounds(466, 138, 86, 29);
		contentPane.add(lblWhiteScore);
		
		//Indicate the last move's location
		JLabel lblLastMove = new JLabel("Last move: ");
		lblLastMove.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLastMove.setBounds(393, 190, 109, 29);
		contentPane.add(lblLastMove);
		
		lblLastMoveLoc = new JLabel();
		lblLastMoveLoc.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblLastMoveLoc.setBounds(499, 190, 67, 29);
		contentPane.add(lblLastMoveLoc);
		
		//Allows the user to change the computer's play speed
//		JLabel lblCompSpeed = new JLabel("Computer's Speed");
//		lblCompSpeed.setHorizontalAlignment(SwingConstants.CENTER);
//		lblCompSpeed.setBounds(393, 267, 190, 22);
//		contentPane.add(lblCompSpeed);
//
//		sliderCompSpeed = new JSlider();
//		sliderCompSpeed.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				gameData.setCompSpeed(sliderCompSpeed.getValue());
//			}
//		});
//		sliderCompSpeed.setSnapToTicks(true);
//		sliderCompSpeed.setPaintTicks(true);
//		sliderCompSpeed.setValue(2);
//		sliderCompSpeed.setMaximum(4);
//		sliderCompSpeed.setBounds(393, 292, 190, 29);
//		contentPane.add(sliderCompSpeed);
//		
//		if (gameData.getPlayer2().getType() != Player.playerTypes.COMPTER) {
//			lblCompSpeed.setVisible(false);
//			sliderCompSpeed.setVisible(false);
//		}
		
		//Explain what player 1 is
//		JLabel lblPlayer = new JLabel("Player1");
//		lblPlayer.setBounds(182, 40, 286, 22);
//		contentPane.add(lblPlayer);
		
		//Explain what player 2 is
//		JLabel lblPlayer2 = new JLabel("Player2");
//		lblPlayer2.setBounds(182, 63, 286, 22);
//		contentPane.add(lblPlayer2);
		
		//Exit/End game button to home screen
		JButton btnEndgame = new JButton("End Game");
		btnEndgame.setToolTipText("Exit the game. All game progress will be lost.");
		btnEndgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame homeFrame = new HomeFrame();
				homeFrame.setVisible(true);
				dispose();
			}
		});
		btnEndgame.setBounds(508, 8, 86, 29);
		contentPane.add(btnEndgame);
		
		//Restart the game
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame restartFrame = new GameFrame(gameSettings);
				restartFrame.setVisible(true);
				dispose();
			}
		});
		btnRestart.setBounds(508, 38, 86, 29);
		contentPane.add(btnRestart);
		
		//Resize the icons to fit the gameboard squares
		BoardIcons bIconsFull = new BoardIcons();
		int scaleSize = panelBoard.getHeight() / d - boardSpacing;
		BoardIcons bIconsScaled = bIconsFull.ConvertAllSizes(scaleSize, scaleSize);
		
		//Generate the board square buttons
		for (int i = 0; i < d; i++)
		{
			for (int j = 0; j < d; j++)
			{
				boardSquares[i][j] = new BoardSquareButton(bIconsScaled, i, j);
				panelBoard.add(boardSquares[i][j]);
				boardSquares[i][j].setVisible(true);
				boardSquares[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boardSquareClicked(e);
					}
				});
			}
		}
		//Set starting pieces in the center
		int c = d / 2; //half/center
		boardSquares[c-1][c-1].setState(BoardSquareButton.states.WHITE_DISC);
		boardSquares[c][c].setState(BoardSquareButton.states.WHITE_DISC);
		boardSquares[c-1][c].setState(BoardSquareButton.states.BLACK_DISC);
		boardSquares[c][c-1].setState(BoardSquareButton.states.BLACK_DISC);
		
		calculatePossibleTargets();
		if (gameData.getCurrentPlayer().getType() == Player.playerTypes.COMPTER)
			computerMove();
	}
	
	private void displayCurrentPlayer() {
		if (gameData.getCurrentPlayer().getColor() == Player.playerColors.BLACK) {
			lblTurn.setText("Black's turn.");
			lblTurn.setForeground(Color.BLACK);
		}
		else {
			lblTurn.setText("White's turn.");
			lblTurn.setForeground(Color.LIGHT_GRAY);
		}
	}
	private void boardSquareClicked(ActionEvent e) {
		//Get the BoardSquareButton
		BoardSquareButton bsb = (BoardSquareButton) e.getSource();
		executeMove(bsb);
	}
	
	private void executeMove(BoardSquareButton target) {
		//Check if valid move
		if (target.getState() != BoardSquareButton.states.SHADOW) 
			target.setState(BoardSquareButton.states.RED);
		else {
			//flip 
			flipAroundTarget(target.getRow(), target.getColumn());
			if (gameData.getCurrentPlayer().getColor() == Player.playerColors.BLACK)
				target.setState(BoardSquareButton.states.BLACK_DISC);
			else
				target.setState(BoardSquareButton.states.WHITE_DISC);
			//After the move is made
			displayScore();
			resetStates();
			gameData.changeCurrentPlayer();
			displayCurrentPlayer();
			displayLastMove(target.getRow(), target.getColumn());
			if (!calculatePossibleTargets())
				return; //Exit is no moves left
			if (gameData.getCurrentPlayer().getType() == Player.playerTypes.COMPTER)
				computerMove(); //Change condition
		}
	}
	
	// Reset red and shadowDisc squares to blanks
	private void resetStates() {
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				if (boardSquares[i][j].getState() == BoardSquareButton.states.RED || boardSquares[i][j].getState() == BoardSquareButton.states.SHADOW)
					boardSquares[i][j].setState(BoardSquareButton.states.BLANK);
			}
		}
	}
	
	// Used for end of the game
	private void disableAllSquares() {
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				boardSquares[i][j].setEnabled(false);
			}
		}
	}
	
	private void displayLastMove(int row, int column) {
		lblLastMoveLoc.setText("(" + row + ", " + column + ")");
	}
	
	private void computerMove() {
//		if (gameData.getCompSpeed() > 0) {
//			long t0, t1;
//			t0 = System.currentTimeMillis();
//			do {
//				t1 = System.currentTimeMillis();
//				System.out.println(System.currentTimeMillis());
//			} while (t1-t0 < 1000 * gameData.getCompSpeed());
//			try {
//				TimeUnit.SECONDS.sleep(gameData.getCompSpeed());
//			} catch (InterruptedException e) {
//				System.out.println(e.getMessage());
//			}
//		}
		
		PossibleMove bestMove = new PossibleMove();
		//For easy difficulty computer or first move 
		if (gameData.getCurrentPlayer().getDifficulty() == Player.diffLevels.EASY || gameData.firstCompMove == true) {
			Random generator = new Random(); 
			int index = generator.nextInt(gameData.getPossibleMoves().size());
//			System.out.println("index: " + index + " and size: " + gameData.getPossibleMoves().size());
			bestMove = gameData.getPossibleMoves().get(index);
			gameData.firstCompMove = false;
		}
		//For Normal difficulty computer
		else {
			//Using Iterator
			ListIterator<PossibleMove> listIterator = gameData.getPossibleMoves().listIterator();
			while (listIterator.hasNext()) {
				PossibleMove possibleMove = listIterator.next();
				if (possibleMove.getFlipCount() > bestMove.getFlipCount())
					bestMove = possibleMove;
			}
		}
//		System.out.println("Best move is at " + bestMove.getRow() + ", " + bestMove.getColumn() + " with count of " + bestMove.getFlipCount());
		executeMove(boardSquares[bestMove.getRow()][bestMove.getColumn()]);
	}
	
	private boolean calculatePossibleTargets() {
		gameData.clearPossibleMoves();
		int moves = 0;
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				if (boardSquares[i][j].getState() == BoardSquareButton.states.BLANK) {
					int flipCount = flipCountForTarget(i, j);
					if (flipCount > 0) { //Possible move found
						moves++;
						gameData.addPossibleMove(new PossibleMove(i, j, flipCount));
						boardSquares[i][j].setState(BoardSquareButton.states.SHADOW);
//						System.out.println("Possible Move at " + i + ", " + j + " with count of " + flipCount);
					}
				}
			}
		}
		//End the game if there are no possible moves
		if (moves == 0) {
			if (gameData.scoreBlack > gameData.scoreWhite)
				lblTurn.setText("Black wins!");
			else if (gameData.scoreWhite > gameData.scoreBlack)
				lblTurn.setText("White wins!");
			else 
				lblTurn.setText("Tie game!");
			lblTurn.setForeground(Color.GREEN);
			disableAllSquares();
			return false;
		}
		return true;
	}
	
//	private boolean attemptAroundTarget(int row, int column) {
//		if (cascadeDirection(row, column, row-1, column-1, false) != 0 || cascadeDirection(row, column, row-1, column-0, false) != 0
//				|| cascadeDirection(row, column, row-0, column-1, false) != 0 || cascadeDirection(row, column, row+1, column-0, false) != 0
//				|| cascadeDirection(row, column, row-0, column+1, false) != 0 || cascadeDirection(row, column, row+1, column-1, false) != 0
//				|| cascadeDirection(row, column, row-1, column+1, false) != 0 || cascadeDirection(row, column, row+1, column+1, false) != 0)
//			return true;
//		return false;
//	}
	
	private int flipCountForTarget(int row, int column) {
		int count = 0;
//		for (int i = row-1; i <= row+1; i++) {
//			for (int j = column-1; j <= column+1; j++) {
//				if (i != 0 || j != 0)
//					count += cascadeDirection(row, column, i, j, false);
//			}
//		}
		count += cascadeDirection(row, column, row-1, column-1, false);
		count += cascadeDirection(row, column, row-1, column-0, false);
		count += cascadeDirection(row, column, row-0, column-1, false);
		count += cascadeDirection(row, column, row+1, column-0, false);
		count += cascadeDirection(row, column, row-0, column+1, false);
		count += cascadeDirection(row, column, row+1, column-1, false);
		count += cascadeDirection(row, column, row-1, column+1, false);
		count += cascadeDirection(row, column, row+1, column+1, false);
		return count;
	}
	
	private void flipAroundTarget(int row, int column) {
//		for (int i = row-1; i <= row+1; i++) {
//			for (int j = column-1; j <= column+1; j++) {
//				if (i != 0 || j != 0)
//					cascadeDirection(row, column, i, j, true);
//			}
//		}
		cascadeDirection(row, column, row-1, column-1, true);
		cascadeDirection(row, column, row-1, column-0, true);
		cascadeDirection(row, column, row-0, column-1, true);
		cascadeDirection(row, column, row+1, column-0, true);
		cascadeDirection(row, column, row-0, column+1, true);
		cascadeDirection(row, column, row+1, column-1, true);
		cascadeDirection(row, column, row-1, column+1, true);
		cascadeDirection(row, column, row+1, column+1, true);
		return;
	}
	
	private int cascadeDirection(int iR, int iC, int nR, int nC, boolean flip) { //i for initial, n for Next, R for Row, C for Column
		if (iR == nR && iC == nC)
			return 0;
		if (!checkInBounds(nR, nC))
			return 0;
		//Must be next to opposite disc
		if (compareNext(nR, nC) != -1)
			return 0;
		//Get direction
		int dirR = nR - iR;
		int dirC = nC - iC;
		nR += dirR;
		nC += dirC;
//		System.out.println(dirR + " " + dirC);
		int count = 1;
		while (true) {
			if (!checkInBounds(nR, nC)) //end out of bounds
				return 0;
			int next = compareNext(nR, nC);
			if (next == 1) { //end found
				if (flip) {
					if (gameData.getCurrentPlayer().getColor() == Player.playerColors.BLACK)
						flipBetween(iR, iC, nR, nC, BoardSquareButton.states.BLACK_DISC);
					else if (gameData.getCurrentPlayer().getColor() == Player.playerColors.WHITE)
						flipBetween(iR, iC, nR, nC, BoardSquareButton.states.WHITE_DISC);
				}
//				System.out.println("End found: " + nR + ", " + nC + ". With count of " + count + " between.");
				return count;
			}
			if (next == 0) //end not found;
				return 0; 
			nR += dirR;
			nC += dirC;
			count++;
		}
	}
	
	private void flipBetween(int iR, int iC, int fR, int fC, BoardSquareButton.states newState) { //initial and final row and column
		//Get direction
//		System.out.println("flip between " + iR + ", " + iC + " and " + fR + ", " + fC);
		int dirR = fR - iR;
		int dirC = fC - iC;
		//Set direction to increments
		if (dirR > 0)
			dirR = 1;
		else if (dirR < 0)
			dirR = -1;
		
		if (dirC > 0)
			dirC = 1;
		else if (dirC < 0)
			dirC = -1;
		
		iR += dirR;
		iC += dirC;
//		System.out.println("before while loop " + iR + ", " + iC + " and " + fR + ", " + fC);
		while (iR != fR || iC != fC) { //Move from initial to final disc
			//Assuming that the state is the opposite disc state
//			System.out.println("flipping " + iR + ", " + iC);
			boardSquares[iR][iC].setState(newState);
			iR += dirR;
			iC += dirC;
		}
	}
	
	private void displayScore() {
		int black = 0;
		int white = 0;
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				if (boardSquares[i][j].getState() == BoardSquareButton.states.BLACK_DISC)
					black++;
				else if (boardSquares[i][j].getState() == BoardSquareButton.states.WHITE_DISC)
					white++;
			}
		}
		gameData.scoreBlack = black;
		lblBlackScore.setText(Integer.toString(black));
		gameData.scoreWhite = white;
		lblWhiteScore.setText(Integer.toString(white));
	}
	
	private int compareNext(int nR, int nC) { //next row and column
		//1 is player owned, 0 is blank, -1 is opponent owned /////////////////////////
		if (boardSquares[nR][nC].getState() == BoardSquareButton.states.BLANK)
			return 0;
		if (gameData.getCurrentPlayer().getColor() == Player.playerColors.BLACK) {
//			System.out.println("black player current");
			if (boardSquares[nR][nC].getState() == BoardSquareButton.states.BLACK_DISC)
				return 1;
			if (boardSquares[nR][nC].getState() == BoardSquareButton.states.WHITE_DISC)
				return -1; 
		}
		if (gameData.getCurrentPlayer().getColor() == Player.playerColors.WHITE) {
//			System.out.println("white player current");
			if (boardSquares[nR][nC].getState() == BoardSquareButton.states.BLACK_DISC)
				return -1;
			if (boardSquares[nR][nC].getState() == BoardSquareButton.states.WHITE_DISC)
				return 1; 
		}
		return 0; //for any other state
	}
	private boolean checkInBounds(int r, int c) { //row, column
		if (r < 0 || r > d - 1)
			return false;
		if (c < 0 || c > d - 1)
			return false;
		return true;
	}
}













