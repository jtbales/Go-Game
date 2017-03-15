import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HomeFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HomeFrame() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame gameSettupFrame = new GameSettupFrame(new GameSettings());
				gameSettupFrame.setVisible(true);
				dispose();
			}
		});
		btnStart.setBackground(Color.WHITE);
		btnStart.setFont(new Font("Lucida Grande", Font.BOLD, 34));
		btnStart.setBounds(124, 139, 203, 91);
		getContentPane().add(btnStart);
		
		JLabel lblBoardGameProject = new JLabel("Board Game Project");
		lblBoardGameProject.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoardGameProject.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblBoardGameProject.setBounds(86, 37, 284, 39);
		getContentPane().add(lblBoardGameProject);
		
		JLabel lblByJohnBales = new JLabel("by John Bales");
		lblByJohnBales.setHorizontalAlignment(SwingConstants.CENTER);
		lblByJohnBales.setBounds(164, 88, 119, 16);
		getContentPane().add(lblByJohnBales);
	}

}
