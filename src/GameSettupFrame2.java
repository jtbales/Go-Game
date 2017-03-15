import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

//Frame for selecting computer difficulty
public class GameSettupFrame2 extends JFrame {

	private JPanel contentPane;
	private GameSettings gameSettings;
	private final ButtonGroup BTN_GRP_COMP1 = new ButtonGroup();
	private final ButtonGroup BTN_GRP_COMP2 = new ButtonGroup();
	private JRadioButton rdbtnEasy1, rdbtnNormal1;
	private JRadioButton rdbtnEasy2, rdbtnNormal2;
	
	/**
	 * Create the frame.
	 */
	public GameSettupFrame2() {
		new GameSettupFrame2(new GameSettings()); //Default is PvC
	}
	public GameSettupFrame2(GameSettings gs) {
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
		
		JLabel lblDescr = new JLabel("Select the difficulty level.\n");
		lblDescr.setBounds(44, 57, 176, 16);
		contentPane.add(lblDescr);
		
		//Computer 1 settings
		JLabel lblComp1 = new JLabel("Computer:");
		lblComp1.setBounds(44, 88, 102, 16);
		contentPane.add(lblComp1);
		
		rdbtnEasy1 = new JRadioButton("Easy");
		BTN_GRP_COMP1.add(rdbtnEasy1);
		rdbtnEasy1.setBounds(44, 116, 102, 23);
		contentPane.add(rdbtnEasy1);
		if (gameSettings.getPlayer1().getDifficulty() == Player.diffLevels.EASY)
			rdbtnEasy1.setSelected(true);
		
		rdbtnNormal1 = new JRadioButton("Normal");
		BTN_GRP_COMP1.add(rdbtnNormal1);
		rdbtnNormal1.setBounds(44, 151, 141, 23);
		contentPane.add(rdbtnNormal1);
		if (gameSettings.getPlayer1().getDifficulty() == Player.diffLevels.NORMAL)
			rdbtnNormal1.setSelected(true);
		
		//Computer 2 settings
		JLabel lblComp2 = new JLabel("Computer 2:");
		lblComp2.setBounds(197, 88, 102, 16);
		contentPane.add(lblComp2);
		
		rdbtnEasy2 = new JRadioButton("Easy");
		BTN_GRP_COMP2.add(rdbtnEasy2);
		rdbtnEasy2.setBounds(197, 116, 141, 23);
		contentPane.add(rdbtnEasy2);
		if (gameSettings.getPlayer2().getDifficulty() == Player.diffLevels.EASY)
			rdbtnEasy2.setSelected(true);
		
		rdbtnNormal2 = new JRadioButton("Normal");
		BTN_GRP_COMP2.add(rdbtnNormal2);
		rdbtnNormal2.setBounds(197, 151, 141, 23);
		contentPane.add(rdbtnNormal2);
		if (gameSettings.getPlayer2().getDifficulty() == Player.diffLevels.NORMAL)
			rdbtnNormal2.setSelected(true);
		
		if (gameSettings.getMatchType() == GameSettings.matchTypes.PvC) //Only show computer two settings if CvC
		{
			lblComp2.setVisible(false);
			rdbtnEasy2.setVisible(false);
			rdbtnNormal2.setVisible(false);
		}
		

		
		//Back button
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSettings();
				JFrame backFrame = new GameSettupFrame(gameSettings);
				backFrame.setVisible(true);
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
				JFrame nextFrame = new GameFrame(gameSettings);
				nextFrame.setVisible(true);
				dispose();
			}
		});
		btnNext.setBounds(338, 232, 89, 23);
		contentPane.add(btnNext);
		
	}
	
	private void updateSettings() {
		if (rdbtnEasy1.isSelected())
			gameSettings.getPlayer1().setDifficulty(Player.diffLevels.EASY);
		else if (rdbtnNormal1.isSelected())
			gameSettings.getPlayer1().setDifficulty(Player.diffLevels.NORMAL);
		if (rdbtnEasy2.isSelected())
			gameSettings.getPlayer2().setDifficulty(Player.diffLevels.EASY);
		else if (rdbtnNormal2.isSelected())
			gameSettings.getPlayer2().setDifficulty(Player.diffLevels.NORMAL);
	}

}

















