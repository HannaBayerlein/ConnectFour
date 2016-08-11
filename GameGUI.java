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
import java.util.ArrayList;
import java.util.List;

public class GameGUI extends JFrame {
	private JTextField[][] gameMatrix;
	private JLabel messageLabel;
	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private List<String> gameActivity;
	private AuditLog auditLog;
	private Highscore theBest;
	private int row;
	private int col;

	/**
	 * The visual game board Contains two players, one board, one audit log, one
	 * activity list and one matrix of squares
	 * 
	 * @param nameOne
	 *            name for player1
	 * @param nameTwo
	 *            name for player2
	 * @param row
	 *            number of rows
	 * @param col
	 *            number of columns
	 */
	public GameGUI(String namePlayerOne, String namePlayerTwo, int row, int col) {

		gameMatrix = new JTextField[row][col];
		board = new Board(row, col);
		playerOne = new Player(1, namePlayerOne, Color.RED);
		playerTwo = new Player(2, namePlayerTwo, Color.BLUE);
		messageLabel = new JLabel(playerOne.getName() + "s turn to play.");
		gameActivity = new ArrayList<String>();
		auditLog = new AuditLog();
		theBest = new Highscore();
		this.row = row;
		this.col = col;

		JFrame frame = new JFrame("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel gridPanel = new JPanel(new GridLayout(row, col));

		if (col == 7) {
			gridPanel.setPreferredSize(new Dimension(570, 480));
		} else if (col == 8) {
			gridPanel.setPreferredSize(new Dimension(650, 490));
		} else {
			gridPanel.setPreferredSize(new Dimension(720, 500));
		}
		frame.add(gridPanel);

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				JTextField square = new JTextField();
				square.setEditable(false);
				gameMatrix[r][c] = square;
				gridPanel.add(square);
			}
		}

		JPanel dropPanel = new JPanel();
		JPanel messagePanel = new JPanel();
		dropPanel.setPreferredSize(new Dimension(40, 40));
		messagePanel.setPreferredSize(new Dimension(60, 60));
		frame.add(dropPanel, BorderLayout.NORTH);
		frame.add(messagePanel, BorderLayout.SOUTH);
		for (int i = 0; i < col; i++) {
			dropPanel.add(new DropButton(i));
		}
		messagePanel.add(messageLabel);
		messagePanel.add(new RestartButton());
		messagePanel.add(new LogButton());
		messagePanel.add(new HighScoreButton());
		frame.setVisible(true);
		frame.pack();

	}

	/**
	 * Places the players marker by changing the squares background color and
	 * scans the board for a winner, if yes the winner gets a win
	 */
	class DropButton extends JButton implements ActionListener {
		private int colu;

		public DropButton(int n) {
			super("Drop");
			colu = n;
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			int rows = board.setNbr(colu);

			if (rows != 10) {
				int turn = board.getTurn();
				if (playerOne.getNbr() != turn) {
					messageLabel.setText(playerTwo.getName()
							+ "s turn to play.");
					gameMatrix[rows][colu].setBackground(playerOne.getColor());
					gameActivity.add(playerOne.getName()
							+ " placed his mark on " + rows + ", " + colu);
				} else {
					messageLabel.setText(playerOne.getName()
							+ "s turn to play.");
					gameMatrix[rows][colu].setBackground(playerTwo.getColor());
					gameActivity.add(playerTwo.getName()
							+ " placed his mark on " + rows + ", " + colu);
				}
				int winningPlayer = board.scanForWinner();
				String winner;

				if (winningPlayer != 0) {
					if (winningPlayer == 10) {
						winner = "It was a tie! ";
					} else if (winningPlayer == playerOne.getNbr()) {
						winner = playerOne.getName() + " won the game!";
						theBest.writeHighscore(playerOne.getName());
					} else {
						winner = playerTwo.getName() + " won the game!";
						theBest.writeHighscore(playerTwo.getName());
					}
					gameActivity.add(winner);
					auditLog.writeLog(gameActivity);
					
					
					Object[] op = { "No", "Yes" };

					int n = JOptionPane.showOptionDialog(null, winner
							+ " Do you want to play again?", "Victory",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, op, op[0]);

					if (n == 1) {
						board.cleanBoard();
						cleanGUI();
						messageLabel.setText(playerOne.getName()
								+ "s turn to play.");

					} else if (n == 0) {
						System.exit(0);

					}

				}
			} else {

				JOptionPane.showMessageDialog(new JFrame(),
						"This column is full, try another one.");
			}
		}
	}

	
	
	public void removeActivity(){
		int i = gameActivity.size();
		while(!gameActivity.isEmpty()){
			gameActivity.remove(i);
			i--;
		}
	}
	
	
    /**
    * Sets the background color of the matrix to white
	*/
	public void cleanGUI() {
		for (int r = 0; r < row; r++) {
			for (int column = 0; column < col; column++) {
				gameMatrix[r][column].setBackground(Color.WHITE);
			}
		}
	}

    /**
    * Cancels the game and starts a new one
    */
	class RestartButton extends JButton implements ActionListener {

		public RestartButton() {
			super("Restart");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			gameActivity.add("The game was canceled.");
			auditLog.writeLog(gameActivity);
			board.cleanBoard();
			cleanGUI();

		}

	}

   /**
   * Opens the activity file from auditLog
   */
	class LogButton extends JButton implements ActionListener {

		public LogButton() {
			super("Historic");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			auditLog.open();

		}

	}

	/**
	 * Shows the player that has won the most times
	 *
	 */
	class HighScoreButton extends JButton implements ActionListener {

		public HighScoreButton() {
			super("Highscore");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			JOptionPane
					.showMessageDialog(new JFrame(), theBest.getHighscore());

		}

	}

}
