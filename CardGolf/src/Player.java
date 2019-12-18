// -------------------------------------------------------
// Assignment #4
// Written by: Laila Chamma'a, ID: 40107470
// For COMP 248 Section W – Winter 2019
// --------------------------------------------------------
public class Player {
	//attributes:
	private String name;
	private char[][] board;
	private boolean[][] turned;
	
	//default constructor
	public Player() {
		board = new char[3][3];
		turned = new boolean[3][3];
		name = "";

	}
	public Player(String name) {
		this.name = name;
		board = new char[3][3];
		turned = new boolean[3][3];
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}
	
	//returns the card in location[r][c] in a player’s 3x3 grid. 
	public char cardAt(int r, int c) {
		return board[r][c];
	}
	
	public boolean flip(int r, int c) {
		if (turned[r][c] == true) 
			return false;
		else {
			turned[r][c] = true;
			return true;
		}
	}
	// sets the card in board at the specified location to be the passed card. 
	public void setTo(int r, int c, char card) {
		board[r][c] = card;
	}
	
	// returns true if the card is already flipped and false otherwise
	public boolean isTurned(int r, int c) {
		return (turned[r][c]);
	}
	
	//flips over the card at the specified location
	public void turn(int r, int c) {
		turned[r][c] = !turned[r][c];
	}
	
	//returns true if all of a players cards are turned over or 
	//false if there are still cards to turn over.
	public boolean allTurned() {
		for (int i = 0; i < turned.length; i++) {
			for (int j = 0; j < turned[i].length; j++) {
				if (turned[i][j] == false)
					return false;
			}
		}
		return true;
	}
	
	public int calculatePts() {
		//give all the cards their values
		//what happens if they're the same
		int score = 0;
		char[][] boardCopy = (char[][]) this.board.clone();
		
		char[] cards = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', '?'};
		int[] points = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 0, -5};
		
		//if they're identical
		for (int i = 0; i < 3; i++) {
			//column
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				boardCopy[0][i] = 'K';
				boardCopy[1][i] = 'K';
				boardCopy[2][i] = 'K';
			}
			//row
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				boardCopy[i][0] = 'K';
				boardCopy[i][1] = 'K';
				boardCopy[i][2] = 'K';
			}
			//diagonal
			if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
				boardCopy[0][0] = 'K';
				boardCopy[1][1] = 'K';
				boardCopy[2][2] = 'K';
			}
			//other diagonal
			if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
				boardCopy[0][2] = 'K';
				boardCopy[1][1] = 'K';
				boardCopy[2][0] = 'K';
			}
		}
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				for (int i = 0; i < cards.length; i++)  {
						if (boardCopy[r][c] == cards[i]) {
							score += points[i];
						}
					}
				}
			}
		return score;
	}
	
	//displaying the board
	public void displayBoard() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				System.out.print(board[r][c] + "  ");
				if ((r == 0 || r == 1) && c == 2) System.out.println();
			}
		}
	}
	
}
