package ConnectFourGame;

public class Board {
	private int[][] matrix;
	private int gameCount;
	private int row;
	private int col;

	/**
	* The game board
	* Contains a matrix with numbers that the players change while playing
	* @param row number of row in the matrix
	* @param col number of column in the matrix
	*/
	public Board(int row, int col) {
		matrix = new int[row][col];
		gameCount = 1;
		this.row = row;
		this.col = col;
	}

	
//	public void printBoard() {
//		for (int r = 0; r < row; r++) {
//			for (int c = 0; c < col; c++) {
//				System.out.print(matrix[r][c] + " ");
//
//			}
//			System.out.println();
//		}
//	}

	/**
	* Restarts the game by adding zeros in the matrix
	*/
	public void cleanBoard() {
		for (int r = 0; r< row; r++) {
			for (int c = 0; c < col; c++) {
				matrix[r][c] = 0;
				gameCount = 1;
			}
		}
	}

	
	/**
	* Checks if the chosen place to drop the marker is free
	* @param r is the row in the matrix
	* @param c is the column in the matrix
	* @return if the place is free
	*/
	private boolean isEmpty(int r, int c) {
		int n = matrix[r][c];
		if (n == 0) {
			return true;
		}
		return false;
	}

	/**
	* Checks whos turn it is
	* @return the number of the player
	*/
	public int getTurn() {
		int turn = (gameCount % 2);
		if (turn == 0) {
			return 2;
		}else{
		return 1;
		}

	}
	
	/**
	* Places the marker
	* @param c is the column in the matrix
	* @return the row to place the marker
	*/
	public int setNbr(int c) {
		for (int i = (row -1); i >= 0; i--) {
			if (isEmpty(i, c)) {
				
				matrix[i][c] = getTurn();
				gameCount++;
				return i;
			}

		}
		return 10;
	}
	
	/**
	* Scans the board matrix for a winner
	* A winner is when a players number is found connected four times
	* @return the winning players number
	*/
	public int scanForWinner() {
		int tie = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				int n = matrix[r][c];
				
				if (n != 0) {
					tie++;
					if ((r + 3) < row && n == matrix[r + 1][c]
							&& n == matrix[r + 2][c]
							&& n == matrix[r + 3][c]) {
						return n;

					} else if ((c + 3) < col) {

						if (n == matrix[r][c + 1]
								&& n == matrix[r][c + 2]
								&& n == matrix[r][c + 3]) {
							return n;

						} else if ((r + 3) < row
								&& n == matrix[r + 1][c + 1]
								&& n == matrix[r + 2][c + 2]
								&& n == matrix[r + 3][c + 3]) {
							return n;
									

						} else if ((r - 3) >= 0
								&& n == matrix[r - 1][c + 1]
								&& n == matrix[r - 2][c + 2]
								&& n == matrix[r - 3][c + 3]) {
							return n;

						}

					}
				}
				if(tie == row*col){
					System.out.println(tie);
					return 10;
				}

			}
		}

		return 0;

	}
}
