import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameSettupFrame extends JFrame {

	private JPanel contentPane;
	private GameSettings gameSettings;
	private JRadioButton rdbtnPvC, rdbtnPvP, rdbtnCvC;
	private JSlider dimensionsSlider;
	private JLabel lblDimensionsValue;
	private final ButtonGroup BTN_GRP_MATCH_TYPE = new ButtonGroup();
	private final ButtonGroup BTN_GRP_START = new ButtonGroup();
	private JRadioButton rdbtnP1Starts, rdbtnP2Starts;
	
	/**
	 * Create the frame.
	 */
	public GameSettupFrame() {
		new GameSettupFrame(new GameSettings());
	}
	public GameSettupFrame(GameSettings gs) {
		gameSettings = gs;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Title
		JLabel lblTitle = new JLabel("Game Settup");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 23));
		lblTitle.setBounds(143, 6, 160, 39);
		contentPane.add(lblTitle);
		
		//Match Type
		JLabel lblMatchType = new JLabel("Match Type");
		lblMatchType.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblMatchType.setBounds(32, 46, 127, 31);
		contentPane.add(lblMatchType);
		
		rdbtnPvC = new JRadioButton("Player vs Computer");
		rdbtnPvC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBTN_GRP_START();
			}
		});
		BTN_GRP_MATCH_TYPE.add(rdbtnPvC);
		rdbtnPvC.setBounds(32, 82, 160, 23);
		contentPane.add(rdbtnPvC);
		if (gameSettings.getMatchType() == GameSettings.matchTypes.PvC)
			rdbtnPvC.setSelected(true);
		
		rdbtnPvP = new JRadioButton("Player vs Player");
		rdbtnPvP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBTN_GRP_START();
			}
		});
		BTN_GRP_MATCH_TYPE.add(rdbtnPvP);
		rdbtnPvP.setBounds(32, 117, 141, 23);
		contentPane.add(rdbtnPvP);
		if (gameSettings.getMatchType() == GameSettings.matchTypes.PvP)
			rdbtnPvP.setSelected(true);
		
		rdbtnCvC = new JRadioButton("Computer vs Computer");
		rdbtnCvC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBTN_GRP_START();
			}
		});
		BTN_GRP_MATCH_TYPE.add(rdbtnCvC);
		rdbtnCvC.setBounds(32, 152, 178, 23);
		contentPane.add(rdbtnCvC);
		if (gameSettings.getMatchType() == GameSettings.matchTypes.CvC)
			rdbtnCvC.setSelected(true);
		
		//Dimensions of the board
		dimensionsSlider = new JSlider();
		dimensionsSlider.setPaintTicks(true);
		dimensionsSlider.setSnapToTicks(true);
		dimensionsSlider.setToolTipText("The length and width of the field is set here.");
		dimensionsSlider.setValue(gameSettings.getDimensions() / 2);
		dimensionsSlider.setMinimum(2); //x2
		dimensionsSlider.setMaximum(6); //x2
		dimensionsSlider.setBounds(243, 82, 127, 31);
		contentPane.add(dimensionsSlider);
		
		JLabel lblDimensions = new JLabel("Board Dimensions");
		lblDimensions.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblDimensions.setBounds(243, 55, 165, 16);
		contentPane.add(lblDimensions);
		
		lblDimensionsValue = new JLabel(Integer.toString(dimensionsSlider.getValue() * 2));
		lblDimensionsValue.setLabelFor(dimensionsSlider);
		lblDimensionsValue.setBounds(382, 86, 26, 16);
		contentPane.add(lblDimensionsValue);
		
		dimensionsSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblDimensionsValue.setText(Integer.toString(dimensionsSlider.getValue() * 2));
			}
		});
		
		//The buttons to choose who starts as black
		JLabel lblStartingP = new JLabel("Choose who starts");
		lblStartingP.setBounds(243, 121, 165, 16);
		contentPane.add(lblStartingP);
		
		rdbtnP1Starts = new JRadioButton("Player");
		BTN_GRP_START.add(rdbtnP1Starts);
		rdbtnP1Starts.setBounds(243, 140, 141, 23);
		contentPane.add(rdbtnP1Starts);
		
		rdbtnP2Starts = new JRadioButton("Computer");
		BTN_GRP_START.add(rdbtnP2Starts);
		rdbtnP2Starts.setBounds(243, 175, 141, 23);
		contentPane.add(rdbtnP2Starts);
		
		if (gameSettings.getBlackPlayer() == gameSettings.getPlayer1())
			rdbtnP1Starts.setSelected(true);
		else
			rdbtnP2Starts.setSelected(true);
		
		updateBTN_GRP_START();
		
		//Back button
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame homeFrame = new HomeFrame();
				homeFrame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(19, 229, 89, 29);
		contentPane.add(btnBack);
		
		//Next page button
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSettings();
				JFrame nextFrame;
				if (rdbtnPvP.isSelected()) //Skip computer difficulty setup
					nextFrame = new GameFrame(gameSettings);
				else  //Set computer difficulty
					nextFrame = new GameSettupFrame2(gameSettings);
				nextFrame.setVisible(true);
				dispose();
			}
		});
		btnNext.setBounds(338, 232, 89, 23);
		contentPane.add(btnNext);
		
	}
	
	private void updateSettings() {
		//Set match type
		if (rdbtnPvP.isSelected()) 
			gameSettings.setMatchType(GameSettings.matchTypes.PvP);
		else if (rdbtnPvC.isSelected()) 
			gameSettings.setMatchType(GameSettings.matchTypes.PvC);
		else 
			gameSettings.setMatchType(GameSettings.matchTypes.CvC);
		
		//Set dimensions
		gameSettings.setDimensions(dimensionsSlider.getValue() * 2);
		
		//Set starting player
		if (rdbtnP1Starts.isSelected())
			gameSettings.setBlackPlayer(gameSettings.getPlayer1());
		else if (rdbtnP2Starts.isSelected())
			gameSettings.setBlackPlayer(gameSettings.getPlayer2());
	}
	
	private void updateBTN_GRP_START() {
		if (rdbtnPvC.isSelected()) {
			rdbtnP1Starts.setText("Player");
			rdbtnP2Starts.setText("Computer");
		}
		else if (rdbtnPvP.isSelected()) {
			rdbtnP1Starts.setText("Player 1");
			rdbtnP2Starts.setText("Player 2");
		}			
		else {
			rdbtnP1Starts.setText("Computer 1");
			rdbtnP2Starts.setText("Computer 2");
		}
	}
	
}



















