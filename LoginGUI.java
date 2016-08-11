package ConnectFourGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

	private JLabel messageLabel;
	JTextField nameOne;
	JTextField nameTwo;

	/**
	 * The visual log in side Contains one message label, two text fields for
	 * the players nick names and a button panel.
	 */
	public LoginGUI() {

		nameOne = new JTextField(20);
		nameTwo = new JTextField(20);
		messageLabel = new JLabel(
				"Please pick your nicknames and size of the board.");

		JFrame frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel gridPanel = new JPanel(new GridLayout(15, 10));
		gridPanel.setPreferredSize(new Dimension(300, 300));
		frame.add(gridPanel);
		JPanel namePanel = new JPanel();
		JLabel playerOne = new JLabel("Player one: ");
		JLabel playerTwo = new JLabel("Player two: ");
		namePanel.add(playerOne);
		namePanel.add(nameOne);
		namePanel.add(playerTwo);
		namePanel.add(nameTwo);
		JPanel messagePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		messagePanel.setPreferredSize(new Dimension(100, 60));
		buttonPanel.setPreferredSize(new Dimension(50, 50));
		namePanel.setPreferredSize(new Dimension(400, 200));
		frame.add(namePanel, BorderLayout.CENTER);
		frame.add(messagePanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		messagePanel.add(messageLabel);
		buttonPanel.add(new SizeButton("Small"));
		buttonPanel.add(new SizeButton("Medium"));
		buttonPanel.add(new SizeButton("Big"));
		frame.setVisible(true);
		frame.pack();
	}

	/**
	 * Creates and sets the size on the GameGUI
	 */
	class SizeButton extends JButton implements ActionListener {
		private int row;
		private int col;
		private String size;

		public SizeButton(String size) {
			super(size);
			this.size = size;
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if (size.equals("Small")) {
				row = 6;
				col = 7;
			} else if (size.equals("Medium")) {
				row = 7;
				col = 8;

			} else {
				row = 8;
				col = 9;
			}
			String nickNameOne = nameOne.getText();
			String nickNameTwo = nameTwo.getText();
			if (nickNameOne.isEmpty() || nickNameTwo.isEmpty()) {
				JOptionPane
						.showMessageDialog(new JFrame(), "Chose a nickname.");

			} else {
				GameGUI gui = new GameGUI(nickNameOne, nickNameTwo, row, col);

			}

		}

	}

	/**
	 * The main method that starts the game and creates a LoginGUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LoginGUI GUI = new LoginGUI();

	}
}
